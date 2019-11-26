package com.bw.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bw.cms.domain.Channel;
import com.bw.cms.service.ChannelService;

@RequestMapping("channel")
@Controller
public class ChannelController {
	
	@Autowired
	private ChannelService channelService;
	
	//������Ŀ//��Ŀ.�����������ĸ�ֵ
	@RequestMapping("channels")
	@ResponseBody
	public List<Channel> channels(){
		return channelService.selects();
	}
	
	
}
