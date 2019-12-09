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
	
	//�û���ѯ
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
			throw new RuntimeException("����ʧ��");
		}
	}

	//ע��
	@Override
	public boolean insertSelective(UserVO userVO) {
		// TODO Auto-generated method stub
		//�ж�ע����Ϣ�Ƿ�����Ҫ��
		if(!StringUtil.hasText(userVO.getUsername()))
		 throw new CMSException("�û�������Ϊ��");	
		if(!StringUtil.hasText(userVO.getPassword()))
		 throw new CMSException("���벻��Ϊ��");	
		if(!StringUtil.hasText(userVO.getRepassword()))
			 throw new CMSException("ȷ�����벻��Ϊ��");
		if(!userVO.getPassword().equals(userVO.getRepassword()))
			 throw new CMSException("�������벻һ��");
		//�û��������ظ�ע��
		User u = userMapper.selectByName(userVO.getUsername());
		if(null!=u)
			 throw new CMSException("�û��������ظ�ע��");
		
		//ִ��ע��
		//��������м��ܱ���
		userVO.setPassword(Md5Util.md5Encoding(userVO.getPassword()));
		
		//�û�ע�����������Ĭ��ֵ
		userVO.setCreated(new Date());//ע��ʱ��
		userVO.setNickname(userVO.getUsername());//�ǳ�
		
		return userMapper.insertSelective(userVO)>0;
	
	}
	//��¼
	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		//�жϵ�¼ע����Ϣ�Ƿ�����Ҫ��
			if(!StringUtil.hasText(user.getUsername()))
			 throw new CMSException("�û�������Ϊ��");	
			if(!StringUtil.hasText(user.getPassword()))
			 throw new CMSException("���벻��Ϊ��");	
			//��ѯ�û����Ƿ����
			User u = userMapper.selectByName(user.getUsername());
			if(null==u)
				 throw new CMSException("�û���������");
			else if(u.getLocked()==1) {
				throw new CMSException("�˻�������!");
			}
			else if(!Md5Util.md5Encoding(user.getPassword()).equals(u.getPassword())) {
			throw new CMSException("�������!");	
			}
			return u;
		}

	
}
