package com.wangguansheng.cms.dao;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.wangguansheng.cms.domain.Article;

//ʵ����ɾ�Ĳ� ����
public interface ArticleRepository extends ElasticsearchRepository<Article, Integer> {
	
	//������ʾ  ���±��⣬��������
	List<Article> findBytitle();
	
}
