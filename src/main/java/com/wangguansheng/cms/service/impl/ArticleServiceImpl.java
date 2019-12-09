package com.wangguansheng.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangguansheng.cms.dao.ArticleMapper;
import com.wangguansheng.cms.domain.Article;
import com.wangguansheng.cms.domain.ArticleWithBLOBs;
import com.wangguansheng.cms.service.ArticleService;
@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleMapper articleMapper;
	
	@Override
	public PageInfo<Article> queryArticle(Article article,Integer pageNum,Integer pageSize) {
		//分页 文章
		PageHelper.startPage(pageNum, pageSize);
		List<Article> queryArticle = articleMapper.queryArticle(article);
		PageInfo<Article> inif = new PageInfo<Article>(queryArticle);
		return inif;
	}

	//是否热门
	@Override
	public boolean update(ArticleWithBLOBs article) {
		try {
			return articleMapper.updateByPrimaryKeySelective(article)>0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException("修改失败");
		}
	}

	//文章详情
	@Override
	public ArticleWithBLOBs selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return articleMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageInfo<Article> selects(Article article, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		List<Article> articles = articleMapper.queryArticle(article);
		
		return new PageInfo<Article>(articles);
	}

	//文章添加
	@Override
	public boolean insertSelective(ArticleWithBLOBs record) {
		try {
			return articleMapper.insertSelective(record)>0;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("发布失败");
		}
	}
	
	

}
