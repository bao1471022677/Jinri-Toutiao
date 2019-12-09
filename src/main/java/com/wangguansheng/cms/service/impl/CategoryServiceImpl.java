package com.wangguansheng.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangguansheng.cms.dao.CategoryMapper;
import com.wangguansheng.cms.domain.Category;
import com.wangguansheng.cms.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryMapper categoryMapper;
	
	//¿∏ƒø  2≤„¿‡–Õ
	@Override
	public List<Category> selectsByChannelId(Integer channelId) {
		// TODO Auto-generated method stub
		return categoryMapper.selectsByChannelId(channelId);
	}

}
