package com.wangguansheng.cms.service;

import java.util.List;

import com.wangguansheng.cms.domain.Category;

public interface CategoryService {
	
	//��Ŀ����  �ڶ��� ����
	List<Category> selectsByChannelId(Integer channelId);
	
}
