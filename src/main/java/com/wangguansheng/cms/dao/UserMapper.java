package com.wangguansheng.cms.dao;

import java.util.List;

import com.wangguansheng.cms.domain.User;

public interface UserMapper {
	
	//��ѯ
	List<User> listUser(User user);
	
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    //��ѯ�û����Ƿ����
	User selectByName(String username);
}