package com.bw.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bw.cms.dao.ChannelMapper;
import com.bw.cms.domain.Channel;
import com.bw.cms.service.ChannelService;
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
