package com.wangguansheng.cms.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.wangguansheng.cms.domain.User;
import com.wangguansheng.cms.service.UserService;
@RequestMapping("user")
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//用户查询
	@RequestMapping("queryUser")
	public String listUser(HttpServletRequest request,User user,
			@RequestParam(defaultValue = "1")Integer pageNum,@RequestParam(defaultValue = "6")Integer pageSize) {
		
		PageInfo<User> info = userService.info(user, pageNum, pageSize);
		request.setAttribute("userList", info);
		request.setAttribute("userName", user);
		
		return "admin/user/users";
	}
	
	//修改状态 用户
	@RequestMapping("update")
	@ResponseBody
	public boolean update(User user) {
		
		return userService.update(user);
	}
	
	
}
