package com.bw.cms.service;

import java.util.List;

import com.bw.cms.domain.Category;

public interface CategoryService {
	
	//栏目类型  第二次 类型
	List<Category> selectsByChannelId(Integer channelId);
	
}
