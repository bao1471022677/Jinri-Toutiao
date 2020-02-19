package com.wangguansheng.cms.service.impl;

import java.util.List;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wangguansheng.cms.dao.ArticleMapper;
import com.wangguansheng.cms.dao.ComplainMapper;
import com.wangguansheng.cms.domain.Complain;
import com.wangguansheng.cms.service.ComplainService;
import com.wangguansheng.cms.utils.CMSException;
import com.wangguansheng.cms.vo.ComplainVO;
import com.bw.utils.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class ComplainServiceImpl implements ComplainService {
	@Resource
	private ComplainMapper complainMapper;
	@Resource
	private ArticleMapper articleMapper;

	@Override
	public boolean insert(Complain complain) {
		try {
			//У��ٱ��ĵ�ַ�Ƿ�Ϸ�
			
			  boolean b = StringUtil.isHttpUrl(complain.getUrl());
			  if(!b) { 
				  throw new CMSException("url ���Ϸ�"); 
			  }
			 
			//�ٱ�
			complainMapper.insert(complain);
			//���Ӵ���
			articleMapper.updateComplainnum(complain.getArticleId());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("�ٱ�ʧ��");
			
		}
	
	}

	@Override
	public PageInfo<Complain> selects(ComplainVO complainVO, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<Complain> list = complainMapper.selects(complainVO);
		return new PageInfo<Complain>(list);
	}

}