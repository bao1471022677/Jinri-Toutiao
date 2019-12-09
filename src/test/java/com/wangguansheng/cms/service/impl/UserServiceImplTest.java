package com.wangguansheng.cms.service.impl;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageInfo;
import com.wangguansheng.cms.domain.User;
import com.wangguansheng.cms.service.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class UserServiceImplTest {

	@Autowired
	private UserService userServiceImpl;
	
	@Test
	public void testInfo() {
		
		PageInfo<User> info = userServiceImpl.info(null, 1, 3);
			System.out.println("---"+info);
		
		
	}

}
