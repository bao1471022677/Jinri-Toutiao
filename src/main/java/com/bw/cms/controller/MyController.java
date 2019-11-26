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
	private CollectService collectService;// 我的收藏
	
	//个人中心
	@RequestMapping("index")
	public String index() {
		return "my/index";
	}
	//登录
	@RequestMapping("login")
	public String login() {
			return "passport/login";
	}
	//从 index 文章主页 跳转到 文章添加页面   index 中嵌入 文章添加页面
	@GetMapping("publish")
	public String publish() {
		return "my/article/publish";
	}
	
	//从 index  图片集主页  跳转到  图片集添加页面   index 中嵌入 图片集添加页面
		@GetMapping("publishpic")
		public String publishpic() {
			return "my/article/publishpic";
		}
		
		
	
	//个人中心的文章详情
		@RequestMapping("myArticle")
		public String MyDetail(Model model,Integer id) {
			Article articleDetails = articleService.selectByPrimaryKey(id);
			model.addAttribute("articleDetails", articleDetails);
			return "my/article/articleDetail";
		}
	//我的文章
	@GetMapping("selectByUser")
	public String selectByUser(HttpServletRequest request,Model model,@RequestParam(defaultValue = "1")Integer pageNum,@RequestParam(defaultValue = "4")Integer pageSize) {
		Article a = new Article();
		HttpSession session = request.getSession(false);
		  if(session==null) {
			  return "redirect:/passport/login";//session.可能过期
		  }
		  User user = (User) session.getAttribute("user");
		
		a.setUserId(user.getId());
		
		PageInfo<Article> selects = articleService.selects(a,pageNum,pageSize);
		model.addAttribute("articleList", selects);
		return "my/article/article";
	}
	
	//文章添加
	@PostMapping("publish")
	@ResponseBody
	public boolean publish(HttpServletRequest request,ArticleWithBLOBs article,MultipartFile file) {
		
		if(!file.isEmpty()) {
			//文件上传 路径 把文件放到  /resource/pic 下
			String path = request.getSession().getServletContext().getRealPath("/resource/pic");
			//防止名字重复  UUID 方法  上传文件
			String oldName = file.getOriginalFilename();
			//UUID新建文件名
			String newName = UUID.randomUUID()+oldName.substring(oldName.lastIndexOf("."));
			
			//得到地址   和  UUID名字  创建文件
			File f = new File(path,newName);
			try {
				//写入硬盘  transferTo=复制  移动
				file.transferTo(f);
				//标题 图片
				article.setPicture(newName);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		  }
		//初始化设置
		  article.setStatus(0);//待审核
		  HttpSession session = request.getSession(false);
		  if(session==null) {
			  return false;
		  }
		  User user = (User) session.getAttribute("user");
		  article.setUserId(user.getId());//发布人
		  article.setHits(0);
		  article.setHot(0);
		  article.setDeleted(0);
		  article.setCreated(new Date());
		  article.setUpdated(new Date());
		
		return articleService.insertSelective(article);
	}
	
	
	//图片集添加
	@PostMapping("publishpic")
	@ResponseBody
	public boolean publishpic(HttpServletRequest request,ArticleWithBLOBs article,MultipartFile[] files,String[] descr) {
			
			String newName = null;
			
			List<ArticleVO> list = new ArrayList<ArticleVO>();
			int i=0;
			for (MultipartFile file : files) {
				ArticleVO vo = new ArticleVO();
				
				if(!file.isEmpty()) {
					//文件上传 路径 把文件放到  /resource/pic 下
					String path = request.getSession().getServletContext().getRealPath("/resource/pic");
					//防止名字重复  UUID 方法  上传文件
					String oldName = file.getOriginalFilename();
					//UUID新建文件名
					newName = UUID.randomUUID()+oldName.substring(oldName.lastIndexOf("."));
					
					//得到地址   和  UUID名字  创建文件
					File f = new File(path,newName);
					
					vo.setUrl(newName);
					vo.setDescr(descr[i]);
					i++;
					list.add(vo);
					
					try {
						//写入硬盘  transferTo=复制  移动
						file.transferTo(f);
						
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				  }
			}
			
			//标题 图片
			article.setPicture(newName);
			
			Gson gson = new Gson();// 将集合转成 json 字符串 
			
			article.setContent(gson.toJson(list));//以json类型存储
			
			//初始化设置
			  article.setStatus(0);//待审核
			  HttpSession session = request.getSession(false);
			  if(session==null) {
				  return false;
			  }
			  User user = (User) session.getAttribute("user");
			  article.setUserId(user.getId());//发布人
			  article.setHits(0);
			  article.setHot(0);
			  article.setDeleted(0);
			  article.setCreated(new Date());
			  article.setUpdated(new Date());
			  //图片集
			  article.setContentType(ArticleEnum.IMAGE.getCode());
			  
			return articleService.insertSelective(article);
		}
	
	//我的收藏
	@GetMapping("collects")
	public String collects(HttpServletRequest request, Model model, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "3") Integer pageSize) {
		HttpSession session = request.getSession(false);

		User user = (User) session.getAttribute("user");

		PageInfo<Collect> info = collectService.selects(page, pageSize, user);

		model.addAttribute("info", info);
		return "/my/collect/collects";

	}
	
	//移除收藏
	@ResponseBody
	@PostMapping("deleteCollect")
	public Result<Collect> deleteCollect(Integer id){
		collectService.deleteById(id);
		return ResultUtil.success();
	}
	
}
