package com.wangguansheng.cms.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/**
 * 
 * @ClassName: AdminInterceptor 
 * @Description: ����Ա��̨������
 * @author: charles
 * @date: 2019��11��19�� ����10:02:25
 */
public class AdminInterceptor extends HandlerInterceptorAdapter {

	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//���ع���: 1.���sesssion�� ����Ա��session.�ͷ���.���������
		//getSession(false).false:���requestû��session����null.true:���û���򴴽�session.Ĭ��true
		HttpSession session = request.getSession(false);
		if(null!=session) {
			//��session ��ȡadmin�Ķ���.����������
			Object object = session.getAttribute("admin");
			if(null!=object)
				return true;//����
		}
		 request.setAttribute("message", "�����µ�¼������");
		 request.getRequestDispatcher("/WEB-INF/views/passport/login.jsp").forward(request, response);
		
		return false;
	}
}
