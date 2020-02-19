package com.wangguansheng.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangguansheng.cms.domain.Article;
import com.wangguansheng.cms.domain.ArticleWithBLOBs;

public interface ArticleMapper {
	
	//查询文章
	List<Article> queryArticle(Article article);
	
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleWithBLOBs record);

    int insertSelective(ArticleWithBLOBs record);

    ArticleWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ArticleWithBLOBs record);

    int updateByPrimaryKey(Article record);

    //举报加1
	void updateComplainnum(Integer articleId);

	//添加浏览量
	void upHits(@Param("aid")Integer id);
    
}