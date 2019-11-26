package com.bw.cms.controller;

import java.io.File;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bw.cms.domain.Article;
import com.bw.cms.domain.ArticleWithBLOBs;
import com.bw.cms.domain.Collect;
import com.bw.cms.domain.User;
import com.bw.cms.service.ArticleService;
import com.bw.cms.service.CollectService;
import com.bw.cms.utils.ArticleEnum;
import com.bw.cms.utils.Result;
import com.bw.cms.utils.ResultUtil;
import com.bw.cms.vo.ArticleVO;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;

@RequestMapping("my")
@Controller
public class MyController {
	
	@Resource
	private ArticleService articleService;
	
	@Resource
	private CollectService collectService;// �ҵ��ղ�
	
	//��������
	@RequestMapping("index")
	public String index() {
		return "my/index";
	}
	//��¼
	@RequestMapping("login")
	public String login() {
			return "passport/login";
	}
	//�� index ������ҳ ��ת�� �������ҳ��   index ��Ƕ�� �������ҳ��
	@GetMapping("publish")
	public String publish() {
		return "my/article/publish";
	}
	
	//�� index  ͼƬ����ҳ  ��ת��  ͼƬ�����ҳ��   index ��Ƕ�� ͼƬ�����ҳ��
		@GetMapping("publishpic")
		public String publishpic() {
			return "my/article/publishpic";
		}
		
		
	
	//�������ĵ���������
		@RequestMapping("myArticle")
		public String MyDetail(Model model,Integer id) {
			Article articleDetails = articleService.selectByPrimaryKey(id);
			model.addAttribute("articleDetails", articleDetails);
			return "my/article/articleDetail";
		}
	//�ҵ�����
	@GetMapping("selectByUser")
	public String selectByUser(HttpServletRequest request,Model model,@RequestParam(defaultValue = "1")Integer pageNum,@RequestParam(defaultValue = "4")Integer pageSize) {
		Article a = new Article();
		HttpSession session = request.getSession(false);
		  if(session==null) {
			  return "redirect:/passport/login";//session.���ܹ���
		  }
		  User user = (User) session.getAttribute("user");
		
		a.setUserId(user.getId());
		
		PageInfo<Article> selects = articleService.selects(a,pageNum,pageSize);
		model.addAttribute("articleList", selects);
		return "my/article/article";
	}
	
	//�������
	@PostMapping("publish")
	@ResponseBody
	public boolean publish(HttpServletRequest request,ArticleWithBLOBs article,MultipartFile file) {
		
		if(!file.isEmpty()) {
			//�ļ��ϴ� ·�� ���ļ��ŵ�  /resource/pic ��
			String path = request.getSession().getServletContext().getRealPath("/resource/pic");
			//��ֹ�����ظ�  UUID ����  �ϴ��ļ�
			String oldName = file.getOriginalFilename();
			//UUID�½��ļ���
			String newName = UUID.randomUUID()+oldName.substring(oldName.lastIndexOf("."));
			
			//�õ���ַ   ��  UUID����  �����ļ�
			File f = new File(path,newName);
			try {
				//д��Ӳ��  transferTo=����  �ƶ�
				file.transferTo(f);
				//���� ͼƬ
				article.setPicture(newName);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		  }
		//��ʼ������
		  article.setStatus(0);//�����
		  HttpSession session = request.getSession(false);
		  if(session==null) {
			  return false;
		  }
		  User user = (User) session.getAttribute("user");
		  article.setUserId(user.getId());//������
		  article.setHits(0);
		  article.setHot(0);
		  article.setDeleted(0);
		  article.setCreated(new Date());
		  article.setUpdated(new Date());
		
		return articleService.insertSelective(article);
	}
	
	
	//ͼƬ�����
	@PostMapping("publishpic")
	@ResponseBody
	public boolean publishpic(HttpServletRequest request,ArticleWithBLOBs article,MultipartFile[] files,String[] descr) {
			
			String newName = null;
			
			List<ArticleVO> list = new ArrayList<ArticleVO>();
			int i=0;
			for (MultipartFile file : files) {
				ArticleVO vo = new ArticleVO();
				
				if(!file.isEmpty()) {
					//�ļ��ϴ� ·�� ���ļ��ŵ�  /resource/pic ��
					String path = request.getSession().getServletContext().getRealPath("/resource/pic");
					//��ֹ�����ظ�  UUID ����  �ϴ��ļ�
					String oldName = file.getOriginalFilename();
					//UUID�½��ļ���
					newName = UUID.randomUUID()+oldName.substring(oldName.lastIndexOf("."));
					
					//�õ���ַ   ��  UUID����  �����ļ�
					File f = new File(path,newName);
					
					vo.setUrl(newName);
					vo.setDescr(descr[i]);
					i++;
					list.add(vo);
					
					try {
						//д��Ӳ��  transferTo=����  �ƶ�
						file.transferTo(f);
						
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				  }
			}
			
			//���� ͼƬ
			article.setPicture(newName);
			
			Gson gson = new Gson();// ������ת�� json �ַ��� 
			
			article.setContent(gson.toJson(list));//��json���ʹ洢
			
			//��ʼ������
			  article.setStatus(0);//�����
			  HttpSession session = request.getSession(false);
			  if(session==null) {
				  return false;
			  }
			  User user = (User) session.getAttribute("user");
			  article.setUserId(user.getId());//������
			  article.setHits(0);
			  article.setHot(0);
			  article.setDeleted(0);
			  article.setCreated(new Date());
			  article.setUpdated(new Date());
			  //ͼƬ��
			  article.setContentType(ArticleEnum.IMAGE.getCode());
			  
			return articleService.insertSelective(article);
		}
	
	//�ҵ��ղ�
	@GetMapping("collects")
	public String collects(HttpServletRequest request, Model model, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "3") Integer pageSize) {
		HttpSession session = request.getSession(false);

		User user = (User) session.getAttribute("user");

		PageInfo<Collect> info = collectService.selects(page, pageSize, user);

		model.addAttribute("info", info);
		return "/my/collect/collects";

	}
	
	//�Ƴ��ղ�
	@ResponseBody
	@PostMapping("deleteCollect")
	public Result<Collect> deleteCollect(Integer id){
		collectService.deleteById(id);
		return ResultUtil.success();
	}
	
}
