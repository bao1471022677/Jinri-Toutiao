package com.wangguansheng.cms.dao;

import java.util.List;

import com.wangguansheng.cms.domain.Category;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    //∂˛≤„¿‡–Õ 
	List<Category> selectsByChannelId(Integer channelId);
}