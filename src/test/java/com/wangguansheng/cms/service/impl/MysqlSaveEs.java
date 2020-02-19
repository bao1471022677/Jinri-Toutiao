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
	ArticleService articleService;//���
	
	@Test
	public void addArticle() {
		
		//��ѯ
		Article article = new Article();
		article.setStatus(1);//״̬
		PageInfo<Article> selects = articleService.selects(article, 1, 100);
		
		//��ӵ�es���ݿ�
		articleRepository.saveAll(selects.getList());
		
		
	}
	
	
	
}
