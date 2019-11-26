package com.bw.cms.service;


import com.bw.cms.domain.User;

import com.bw.cms.vo.UserVO;
import com.github.pagehelper.PageInfo;

public interface UserService {
	
	
	 PageInfo<User> info(User user,Integer pageNum,Integer pageSize);

	  boolean update(User user);
	  //×¢²á
	  boolean  insertSelective(UserVO userVO);
   //µÇÂ¼
	  User login(User user);
	  
	
}
