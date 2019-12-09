package com.wangguansheng.cms.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
/**
 * 
 * @ClassName: GlobalExceptionHandle 
 * @Description: ȫ���쳣������
 * @author: charles
 * @date: 2019��11��20�� ����3:15:13
 */
@ControllerAdvice
public class GlobalExceptionHandle {
	
	/**
	 * 
	 * @Title: handleJson 
	 * @Description: ����ajax������쳣
	 * @param cmsJsonException
	 * @return
	 * @return: Result
	 */
	@ResponseBody
	@ExceptionHandler(CMSAjaxException.class)
	public Result handleJson(CMSAjaxException cmsJsonException) {
		return ResultUtil.error(cmsJsonException.getCode(), cmsJsonException.getMessage());
		
	}
	/**
	 * 
	 * @Title: handle 
	 * @Description: ������ͨ������쳣
	 * @param CMSException
	 * @param request
	 * @return
	 * @return: ModelAndView
	 */
	@ExceptionHandler(CMSException.class)
	public ModelAndView handle(CMSException exception,HttpServletRequest request) {
		ModelAndView mv  = new ModelAndView();
	  //���ȡ������Ϣ,���з�װ
		mv.addObject("message",exception.getMessage());
		//��ȡ��ǰ�����url
		 String url = request.getRequestURI();
		// System.out.println(url+"=================");
		mv.setViewName(url);//
		return mv;
		
	}
	/**
	 * 
	 * @Title: handle 
	 * @Description: ����ϵͳ�쳣
	 * @param exception
	 * @return
	 * @return: ModelAndView
	 */
	@ExceptionHandler(Exception.class)
	public String handle(Exception exception) {
		
		return "common/error";
		
	}
	
	

}