package com.bw.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bw.cms.domain.Article;
import com.bw.cms.domain.ArticleWithBLOBs;
import com.bw.cms.service.ArticleService;
import com.github.pagehelper.PageInfo;

@RequestMapping("article")
@Controller
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	//查询文章
	@RequestMapping("queryArticle")
	public String queryArticle(Model model,@RequestParam(defaultValue = "1")Integer pageNum,Article article) {
		
		PageInfo<Article> queryArticle = articleService.queryArticle(article, pageNum, 5);
		model.addAttribute("articleList", queryArticle);
		model.addAttribute("article", article);
		
		return "admin/article/article";
	}
	
	//修改热门
	@RequestMapping("update")
	@ResponseBody
	public boolean update(ArticleWithBLOBs article) {
		return articleService.update(article);
	}
	
	//文章详情
	@RequestMapping("articleDetails")
	public String articleDetails(Model model,Integer id) {
		
		Article selectByPrimaryKey = articleService.selectByPrimaryKey(id);
		model.addAttribute("articleDetails", selectByPrimaryKey);
		
		return "admin/article/articleDetails";
	}
	
}
