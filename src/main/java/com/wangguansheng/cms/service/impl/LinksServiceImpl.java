package com.wangguansheng.cms.service.impl;

import java.util.Date;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangguansheng.cms.dao.LinksMapper;
import com.wangguansheng.cms.domain.Links;
import com.wangguansheng.cms.service.LinksService;
import com.wangguansheng.cms.utils.CMSAjaxException;
import com.wangguansheng.cms.utils.StringUtil;

@Service
public class LinksServiceImpl implements LinksService {

	@Resource
	private LinksMapper linksMapper;

	@Override
	public boolean insert(Links links) {
		// ���ù������ж��Ƿ�����ЧURL
		if (!StringUtil.isHttpUrl(links.getUrl()))
			throw new CMSAjaxException(1, "������Ч��url");
		links.setCreated(new Date());
		linksMapper.insert(links);
		return true;

	}

	//��ѯ ��������
	@Override
	public PageInfo<Links> selects(Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<Links> list = linksMapper.selects();
		return new PageInfo<Links>(list);
	}

}
