package com.wangguansheng.cms.utils;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wangguansheng.cms.dao.UserMapper;
import com.wangguansheng.cms.domain.User;
public class UserInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// ���� : ����û��Ѿ���¼,������

		HttpSession session = request.getSession(false);
		if (null != session) {
			// ��session��ȡ��ͨ�û���user����
			Object object = session.getAttribute("user");
			if (null != object)// �����Ϊ��,�򷵻�true
				return true;// ����
		}

		// ����û���cookie��,�������ݿ���˻�����ƥ��������
		if(rememberAutoLogin(request,request.getSession()))
			return true;
			
		// ������Ҫ��,ת������¼ҳ��
		request.setAttribute("error", "Ȩ�޲�����,�����µ�¼");
		request.getRequestDispatcher("/WEB-INF/views/passport/login.jsp").forward(request, response);

		return false;

	}

	@Resource
	private UserMapper userMapper;

	private boolean rememberAutoLogin(HttpServletRequest request,HttpSession session) {
		Cookie cokUsername = CookieUtil.getCookieByName(request, "username");
		Cookie cokPassword = CookieUtil.getCookieByName(request, "password");
		// ��cookie��ȡ�û���������
		if (null != cokUsername && null != cokPassword && null != cokUsername.getValue()
				&& null != cokPassword.getValue()) {
			String username = cokUsername.getValue();
			String password = cokPassword.getValue();
			// �����ݿ��ȡ�û��������벢��cookie�ıȽϡ���һֱ�򷵻�true
			User user = userMapper.selectByName(username);
			if (null != user && username.equals(user.getUsername()) && password.equals(user.getPassword())) {
				///ϵͳ�����õ���session ,��Ҫ���´洢session
				session.setAttribute("user", user);
				
				return true;
			}
		}
		return false;

	}
}
