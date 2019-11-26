package com.bw.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bw.cms.dao.ArticleMapper;
import com.bw.cms.domain.Article;
import com.bw.cms.domain.ArticleWithBLOBs;
import com.bw.cms.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleMapper articleMapper;
	
	@Override
	public PageInfo<Article> queryArticle(Article article,Integer pageNum,Integer pageSize) {
		//��ҳ ����
		PageHelper.startPage(pageNum, pageSize);
		List<Article> queryArticle = articleMapper.queryArticle(article);
		PageInfo<Article> inif = new PageInfo<Article>(queryArticle);
		return inif;
	}

	//�Ƿ�����
	@Override
	public boolean update(ArticleWithBLOBs article) {
		try {
			return articleMapper.updateByPrimaryKeySelective(article)>0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException("�޸�ʧ��");
		}
	}

	//��������
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

	//�������
	@Override
	public boolean insertSelective(ArticleWithBLOBs record) {
		try {
			return articleMapper.insertSelective(record)>0;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("����ʧ��");
		}
	}
	
	

}
