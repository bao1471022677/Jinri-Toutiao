package com.bw.cms.service.impl;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bw.cms.domain.User;
import com.bw.cms.service.UserService;
import com.github.pagehelper.PageInfo;


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
