package com.wangguansheng.cms.service;

import com.github.pagehelper.PageInfo;
import com.wangguansheng.cms.domain.Article;
import com.wangguansheng.cms.domain.ArticleWithBLOBs;

public interface ArticleService {
	
	//查询文章
	PageInfo<Article> queryArticle(Article article,Integer pageNum,Integer pageSize);

	boolean update(ArticleWithBLOBs article);
	
	//文章详情
	 ArticleWithBLOBs selectByPrimaryKey(Integer id);

	 PageInfo<Article> selects(Article article, Integer pageNum, Integer pageSize);

	boolean insertSelective(ArticleWithBLOBs article);
	
	//浏览+1
	void upHits(Integer id);
	
}
