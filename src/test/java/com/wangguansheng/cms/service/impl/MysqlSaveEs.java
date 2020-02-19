package com.wangguansheng.cms.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageInfo;
import com.wangguansheng.cms.dao.ArticleRepository;
import com.wangguansheng.cms.domain.Article;
import com.wangguansheng.cms.service.ArticleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class MysqlSaveEs {
	
	@Autowired
	ArticleRepository articleRepository;
	@Autowired
	ArticleService articleService;//添加
	
	@Test
	public void addArticle() {
		
		//查询
		Article article = new Article();
		article.setStatus(1);//状态
		PageInfo<Article> selects = articleService.selects(article, 1, 100);
		
		//添加到es数据库
		articleRepository.saveAll(selects.getList());
		
		
	}
	
	
	
}
