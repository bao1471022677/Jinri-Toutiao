package com.wangguansheng.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.wangguansheng.cms.dao.ArticleRepository;
import com.wangguansheng.cms.domain.Article;
import com.wangguansheng.cms.domain.ArticleWithBLOBs;
import com.wangguansheng.cms.service.ArticleService;
import com.wangguansheng.cms.utils.HLUtils;

@RequestMapping("article")
@Controller
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;//文章
	
	@Autowired
	private KafkaTemplate kafkaTemplate; //kafka
	
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate; //es
	
	@Autowired
	private ArticleRepository articleRepository;//es
	
	//高量
	@GetMapping("queryKey")
	public String queryKey(String key,@RequestParam(defaultValue = "1")Integer pageNum,Model model) {
		
		PageInfo<Article> findByHighLight = (PageInfo<Article>) HLUtils.findByHighLight(elasticsearchTemplate, Article.class, pageNum, 5, new String[] {"title"}, "id", key);
		//作用于
		model.addAttribute("info", findByHighLight);
		model.addAttribute("key", key);
		return "index/index";
	}
	
	//查询文章
	@RequestMapping("queryArticle")
	public String queryArticle(Model model,@RequestParam(defaultValue = "1")Integer pageNum,Article article) {
		
		PageInfo<Article> queryArticle = articleService.queryArticle(article, pageNum, 5);
		model.addAttribute("articleList", queryArticle);
		model.addAttribute("article", article);
		
		return "admin/article/article";
	}
	
	//修改热门  同意
	@RequestMapping("update")
	@ResponseBody
	public boolean update(ArticleWithBLOBs article) {
		
		//通过   文章id 查询文章详情  
		Integer id = article.getId();
		ArticleWithBLOBs selectByPrimaryKey = articleService.selectByPrimaryKey(id);
		//json  文章 转成json
		String jsonString = JSON.toJSONString(selectByPrimaryKey);
		//传入kafka   最后  es
		kafkaTemplate.send("1708D", "add"+jsonString);
		
		//最后  es 同步数据
		articleRepository.save(selectByPrimaryKey);
		
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
