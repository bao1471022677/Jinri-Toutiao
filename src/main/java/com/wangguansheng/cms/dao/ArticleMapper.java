package com.wangguansheng.cms.dao;

import java.util.List;

import com.wangguansheng.cms.domain.Article;
import com.wangguansheng.cms.domain.ArticleWithBLOBs;

public interface ArticleMapper {
	
	//²éÑ¯ÎÄÕÂ
	List<Article> queryArticle(Article article);
	
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleWithBLOBs record);

    int insertSelective(ArticleWithBLOBs record);

    ArticleWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ArticleWithBLOBs record);

    int updateByPrimaryKey(Article record);
    
}