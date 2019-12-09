package com.wangguansheng.cms.dao;

import java.util.List;

import com.wangguansheng.cms.domain.Channel;

public interface ChannelMapper {
	
	/**
	 * ËùÓÐÀ¸Ä¿
	 * @Title: selects 
	 * @Description: TODO
	 * @return
	 * @return: List<Channel>
	 */
	List<Channel> selects();
	
    int deleteByPrimaryKey(Integer id);

    int insert(Channel record);

    int insertSelective(Channel record);

    Channel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Channel record);

    int updateByPrimaryKey(Channel record);
}