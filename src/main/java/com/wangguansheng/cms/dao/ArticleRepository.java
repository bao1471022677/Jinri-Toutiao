package com.wangguansheng.cms.dao;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.wangguansheng.cms.domain.Article;

//实现增删改查 功能
public interface ArticleRepository extends ElasticsearchRepository<Article, Integer> {
	
	//高亮显示  文章标题，文章内容
	List<Article> findBytitle();
	
}
