package com.bw.cms.service.impl;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bw.cms.domain.Article;
import com.bw.cms.service.ArticleService;
import com.github.pagehelper.PageInfo;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class ArticleServiceImplTest {
	
	@Autowired
	private ArticleService articleService;

	@Test
	public void testQueryArticle() {
		
		PageInfo<Article> queryArticle = articleService.queryArticle(null, 1, 10);
		System.out.println(queryArticle);
		
	}
	
	@Test
	public void selectByPrimaryKey() {
		Article selectByPrimaryKey = articleService.selectByPrimaryKey(7);
		System.out.println(selectByPrimaryKey.toString());
	}

}
