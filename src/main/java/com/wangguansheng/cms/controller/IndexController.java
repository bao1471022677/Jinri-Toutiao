package com.wangguansheng.cms.controller;

import java.util.ArrayList;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.wangguansheng.cms.domain.Article;
import com.wangguansheng.cms.domain.ArticleWithBLOBs;
import com.wangguansheng.cms.domain.Category;
import com.wangguansheng.cms.domain.Channel;
import com.wangguansheng.cms.domain.Collect;
import com.wangguansheng.cms.domain.Links;
import com.wangguansheng.cms.domain.User;
import com.wangguansheng.cms.service.ArticleService;
import com.wangguansheng.cms.service.CategoryService;
import com.wangguansheng.cms.service.ChannelService;
import com.wangguansheng.cms.service.CollectService;
import com.wangguansheng.cms.service.LinksService;
import com.wangguansheng.cms.utils.ArticleEnum;
import com.wangguansheng.cms.utils.Result;
import com.wangguansheng.cms.utils.ResultUtil;
import com.wangguansheng.cms.vo.ArticleVO;

@Controller
public class IndexController {
	@Resource
	private ChannelService channelService;//��Ŀ

	@Resource
	private ArticleService articleService;//����
	
	@Resource
	private CategoryService categoryService;//����
	
	@Resource 
	private LinksService linksService;//��������
	
	@Resource 
	private CollectService collectService;//�ղ�����

	@RequestMapping(value = { "", "/", "index" })
	public String index(Article article, Model model, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "5") Integer pageSize) {
		
		article.setStatus(1);// ��ʾ����˹�������
		article.setDeleted(0);//��ѯδɾ����
		
		//��Ŀ��ʼʱ��
		long s1 = System.currentTimeMillis();
		
		//�����߳� 
		Thread t1 = null;
		Thread t2 = null;
		Thread t3 = null;
		Thread t4 = null;
		Thread t5 = null;
		Thread t6 = null;
		
		// ��ѯ�������Ŀ
		t1 = new Thread(new Runnable() {
		@Override
		public void run() {
				List<Channel> channels = channelService.selects();
				model.addAttribute("channels", channels);

			}
		});
		
		t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				//�����ĿΪ����Ĭ����ʾ�ȵ�
				if (article.getChannelId() == null) {
					// ��ѯ�ȵ����µ��б�
					Article hot = new Article();
					hot.setStatus(1);// ��˹���
					hot.setHot(1);// �ȵ�����
					hot.setDeleted(0);// 
					PageInfo<Article> info = articleService.selects(hot, page, pageSize);
					model.addAttribute("info", info);
				}
			}
		});
		
		t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				//��ʾ��������
				if(article.getChannelId()!=null) {
					//1��ѯ������Ŀ�·���
					List<Category> categorys = categoryService.selectsByChannelId(article.getChannelId());
					model.addAttribute("categorys", categorys);
					//2.��ʾ�����µ�����
					PageInfo<Article> info = articleService.selects(article, page, pageSize);
					model.addAttribute("info", info);
				}
			}
		});
		
		
		t4 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				//�Ҳ������ʾ���µ�5������
				Article lastArticle = new Article();
				lastArticle.setStatus(1);//���ͨ����
				lastArticle.setDeleted(0);;//
				PageInfo<Article> lastInfo = articleService.selects(lastArticle, 1, 5);
				model.addAttribute("lastInfo", lastInfo);
			}
		});
		
		t5 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// ͼƬ��
				Article picarticle = new Article();
				picarticle.setStatus(1);
				picarticle.setDeleted(0);
				picarticle.setContentType(ArticleEnum.IMAGE.getCode());
				PageInfo<Article> picInfo = articleService.selects(picarticle, 1, 5);
				model.addAttribute("picInfo", picInfo);
			}
		});
		
	
		t6 = new Thread(new Runnable() {
			//�������� ��ѯ
			public void run() {
				PageInfo<Links> linksInfo = linksService.selects(1, 10);
				model.addAttribute("linksInfo", linksInfo);
			}
		});
		
		//��װ��ѯ����
		model.addAttribute("article", article);
		
		//��ʼ�߳�
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		
		try {
			//�߳� ִ�� ˳��    ��ִ��  4��С�߳�   ��� ִ�� ���߳� ����ҳ��
			t1.join();
			t2.join();
			t3.join();
			t4.join();
			t5.join();
			t6.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//����ʱ��
		long s2 = System.currentTimeMillis();
		
		System.out.println("��������ʱ�䣺"+(s2-s1));
		
		return "index/index";
	}
	/**
	 * 
	 * @Title: article 
	 * @Description: ��������
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("article")
	public String article(Model model, Integer id) {
		ArticleWithBLOBs article = articleService.selectByPrimaryKey(id);
		model.addAttribute("article", article);
		return "index/articleDetail";
	}
	
	/**
	 * 
	 * @Title: article
	 * @Description: ͼƬ������
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("articlepic")
	public String articlepic(Model model, Integer id) {

		ArticleWithBLOBs article = articleService.selectByPrimaryKey(id);

		String string = article.getContent();

		
		
		ArrayList<ArticleVO> list = new ArrayList<ArticleVO>();
		
		Gson gson = new Gson();
		JsonArray array = new JsonParser().parse(string).getAsJsonArray();
		for (JsonElement jsonElement : array) {
			//��jsonתΪjava
			ArticleVO vo = gson.fromJson(jsonElement, ArticleVO.class);
			list.add(vo);
		}
		model.addAttribute("title", article.getTitle());// ����
		model.addAttribute("list", list);// ��������� ͼƬ�ĵ�ַ������
		return "index/articlepic";
	}
	
	//�ղ�����
	@ResponseBody
	@PostMapping("/collect")
	public  Result<Collect> collect(Collect collect,HttpServletRequest request){
		
		HttpSession session = request.getSession(false);
		if(null==session) {
	    return ResultUtil.error(1, "�ղ�ʧ��,��¼���ܹ���");
		}
		User user = (User) session.getAttribute("user");
		if(null==user) {
		return	ResultUtil.error(1, "�ղ�ʧ��,��¼���ܹ���");
		}
		collect.setUser(user);
		collectService.insert(collect);
		return ResultUtil.success();
	}

}
