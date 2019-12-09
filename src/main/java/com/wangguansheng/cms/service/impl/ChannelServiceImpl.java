package com.wangguansheng.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangguansheng.cms.dao.ChannelMapper;
import com.wangguansheng.cms.domain.Channel;
import com.wangguansheng.cms.service.ChannelService;
@Service
public class ChannelServiceImpl implements ChannelService {
	
	@Autowired
	private ChannelMapper channelMapper;
	//��Ŀ.�����������ĸ�ֵ
	@Override
	public List<Channel> selects() {
		// TODO Auto-generated method stub
		return channelMapper.selects();
	}
	
	
	
	
}
