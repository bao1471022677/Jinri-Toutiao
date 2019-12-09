package com.wangguansheng.cms.service.impl;

import java.util.Date;


import java.util.List;

import org.springframework.stereotype.Service;

import com.bw.utils.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangguansheng.cms.dao.UserMapper;
import com.wangguansheng.cms.domain.User;
import com.wangguansheng.cms.service.UserService;
import com.wangguansheng.cms.utils.CMSException;
import com.wangguansheng.cms.utils.Md5Util;
import com.wangguansheng.cms.vo.UserVO;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	//用户查询
	public PageInfo<User> info(User user,Integer pageNum,Integer pageSize) {
		
		PageHelper.startPage(pageNum, pageSize);
		List<User> listUser = userMapper.listUser(user);
		PageInfo<User> info = new PageInfo<User>(listUser);
		return  info;
	}

	@Override
	public boolean update(User user) {
		try {
			userMapper.updateByPrimaryKeySelective(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("操作失败");
		}
	}

	//注册
	@Override
	public boolean insertSelective(UserVO userVO) {
		// TODO Auto-generated method stub
		//判断注册信息是否满足要求
		if(!StringUtil.hasText(userVO.getUsername()))
		 throw new CMSException("用户名不能为空");	
		if(!StringUtil.hasText(userVO.getPassword()))
		 throw new CMSException("密码不能为空");	
		if(!StringUtil.hasText(userVO.getRepassword()))
			 throw new CMSException("确认密码不能为空");
		if(!userVO.getPassword().equals(userVO.getRepassword()))
			 throw new CMSException("两次密码不一致");
		//用户名不能重复注册
		User u = userMapper.selectByName(userVO.getUsername());
		if(null!=u)
			 throw new CMSException("用户名不能重复注册");
		
		//执行注册
		//对密码进行加密保存
		userVO.setPassword(Md5Util.md5Encoding(userVO.getPassword()));
		
		//用户注册的其他属性默认值
		userVO.setCreated(new Date());//注册时间
		userVO.setNickname(userVO.getUsername());//昵称
		
		return userMapper.insertSelective(userVO)>0;
	
	}
	//登录
	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		//判断登录注册信息是否满足要求
			if(!StringUtil.hasText(user.getUsername()))
			 throw new CMSException("用户名不能为空");	
			if(!StringUtil.hasText(user.getPassword()))
			 throw new CMSException("密码不能为空");	
			//查询用户名是否存在
			User u = userMapper.selectByName(user.getUsername());
			if(null==u)
				 throw new CMSException("用户名不存在");
			else if(u.getLocked()==1) {
				throw new CMSException("账户被禁用!");
			}
			else if(!Md5Util.md5Encoding(user.getPassword()).equals(u.getPassword())) {
			throw new CMSException("密码错误!");	
			}
			return u;
		}

	
}
