package com.wangguansheng.cms.service;

import com.github.pagehelper.PageInfo;
import com.wangguansheng.cms.domain.Article;
import com.wangguansheng.cms.domain.ArticleWithBLOBs;

public interface ArticleService {
	
	//��ѯ����
	PageInfo<Article> queryArticle(Article article,Integer pageNum,Integer pageSize);

	boolean update(ArticleWithBLOBs article);
	
	//��������
	 ArticleWithBLOBs selectByPrimaryKey(Integer id);

	 PageInfo<Article> selects(Article article, Integer pageNum, Integer pageSize);

	boolean insertSelective(ArticleWithBLOBs article);
	
	//���+1
	void upHits(Integer id);
	
}
