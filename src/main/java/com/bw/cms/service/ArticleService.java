package com.bw.cms.service;

import com.bw.cms.domain.Article;
import com.bw.cms.domain.ArticleWithBLOBs;
import com.github.pagehelper.PageInfo;

public interface ArticleService {
	
	//查询文章
	PageInfo<Article> queryArticle(Article article,Integer pageNum,Integer pageSize);

	boolean update(ArticleWithBLOBs article);
	
	//文章详情
	 ArticleWithBLOBs selectByPrimaryKey(Integer id);

	 PageInfo<Article> selects(Article article, Integer pageNum, Integer pageSize);

	boolean insertSelective(ArticleWithBLOBs article);
	
}
