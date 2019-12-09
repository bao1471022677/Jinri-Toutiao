package com.wangguansheng.cms.service;


import com.github.pagehelper.PageInfo;
import com.wangguansheng.cms.domain.User;
import com.wangguansheng.cms.vo.UserVO;

public interface UserService {
	
	
	 PageInfo<User> info(User user,Integer pageNum,Integer pageSize);

	  boolean update(User user);
	  //ע��
	  boolean  insertSelective(UserVO userVO);
	  //��¼
	  User login(User user);
	  
	
}
