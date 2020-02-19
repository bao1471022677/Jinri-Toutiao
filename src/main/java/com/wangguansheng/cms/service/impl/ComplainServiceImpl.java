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
			//校验举报的地址是否合法
			
			  boolean b = StringUtil.isHttpUrl(complain.getUrl());
			  if(!b) { 
				  throw new CMSException("url 不合法"); 
			  }
			 
			//举报
			complainMapper.insert(complain);
			//增加次数
			articleMapper.updateComplainnum(complain.getArticleId());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("举报失败");
			
		}
	
	}

	@Override
	public PageInfo<Complain> selects(ComplainVO complainVO, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<Complain> list = complainMapper.selects(complainVO);
		return new PageInfo<Complain>(list);
	}

}