package com.bw.cms.service;

import java.util.List;

import com.bw.cms.domain.Category;

public interface CategoryService {
	
	//��Ŀ����  �ڶ��� ����
	List<Category> selectsByChannelId(Integer channelId);
	
}
