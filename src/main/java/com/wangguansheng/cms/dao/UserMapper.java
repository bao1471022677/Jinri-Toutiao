package com.wangguansheng.cms.dao;

import java.util.List;

import com.wangguansheng.cms.domain.User;

public interface UserMapper {
	
	//查询
	List<User> listUser(User user);
	
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    //查询用户名是佛存在
	User selectByName(String username);
}