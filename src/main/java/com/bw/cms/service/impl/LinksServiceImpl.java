package com.bw.cms.service.impl;

import java.util.Date;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bw.cms.dao.LinksMapper;
import com.bw.cms.domain.Links;

import com.bw.cms.service.LinksService;
import com.bw.cms.utils.CMSAjaxException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bw.cms.utils.StringUtil;

@Service
public class LinksServiceImpl implements LinksService {

	@Resource
	private LinksMapper linksMapper;

	@Override
	public boolean insert(Links links) {
		// ���ù������ж��Ƿ�����ЧURL
		if (!StringUtil.isHttpurl(links.getUrl()))
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
