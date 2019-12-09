package com.wangguansheng.cms.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangguansheng.cms.dao.CollectMapper;
import com.wangguansheng.cms.domain.Collect;
import com.wangguansheng.cms.domain.User;
import com.wangguansheng.cms.service.CollectService;
import com.wangguansheng.cms.utils.CMSAjaxException;
import com.wangguansheng.cms.utils.StringUtil;

@Service
public class CollectServiceImpl implements CollectService {

	@Resource
	private CollectMapper collectMapper;

	@Override
	public boolean insert(Collect collect) {
		if(!StringUtil.isHttpurl(collect.getUrl()))
			throw new CMSAjaxException(1, "不是有效的URL");
		collect.setCreated(new Date());
		collectMapper.insert(collect);
		return true;
	}

	@Override
	public PageInfo<Collect> selects(Integer page,Integer pageSize ,User user) {
		PageHelper.startPage(page, pageSize);
		List<Collect> list = collectMapper.selects( user);
		return new PageInfo<Collect>(list);
	}

	@Override
	public boolean deleteById(Integer id) {
		collectMapper.deleteById(id);
		return true;
	}

	@Override
	public int selectByText(String text, User user) {
		return collectMapper.selectByText(text, user);
	}
	
}
