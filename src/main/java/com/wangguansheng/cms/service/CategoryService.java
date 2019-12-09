package com.wangguansheng.cms.service;

import java.util.List;

import com.wangguansheng.cms.domain.Category;

public interface CategoryService {
	
	//栏目类型  第二次 类型
	List<Category> selectsByChannelId(Integer channelId);
	
}
