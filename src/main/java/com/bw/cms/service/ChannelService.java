package com.bw.cms.service;

import java.util.List;

import com.bw.cms.domain.Channel;

public interface ChannelService {
	
	//栏目.分类的下拉框的赋值
	List<Channel> selects();
	
}
