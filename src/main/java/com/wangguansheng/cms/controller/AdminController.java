package com.wangguansheng.cms.controller;

import javax.annotation.Resource;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.wangguansheng.cms.domain.Article;
import com.wangguansheng.cms.domain.Links;
import com.wangguansheng.cms.service.ArticleService;
import com.wangguansheng.cms.service.LinksService;
import com.wangguansheng.cms.utils.Result;
import com.wangguansheng.cms.utils.ResultUtil;

@RequestMapping("admin")
@Controller
public class AdminController {

	@Resource
	private ArticleService articleService;
	
	@Resource
	private LinksService linksService;

	@RequestMapping("index")
	public String IndexController() {
		// 页面跳转
		return "admin/index";
	}

	// 查询文章详情
	@RequestMapping("article")
	public String detail(Model model, Integer id) {
		Article articleDetails = articleService.selectByPrimaryKey(id);
		model.addAttribute("articleDetails", articleDetails);
		return "admin/article/articleDetails";
	}
	
	//友情链接 查询 列表
	@GetMapping("queryLinks")
	public String selects(Model model,@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "5") Integer pageSize) {
		PageInfo<Links> info = linksService.selects(page, pageSize);	
		model.addAttribute("info", info);
		return "admin/links/links";
	}
	
	//跳转 添加 页面  友情连接添加
	@GetMapping("addLinks")
	public String add() {
		return "admin/links/addLinks";
		
	}
	
	//添加  友情连接
	@SuppressWarnings("unchecked")
	@ResponseBody
	@PostMapping("links/add")
	public Result<Links> add(Links links){
		linksService.insert(links);
		return ResultUtil.success();
	}
	

}
