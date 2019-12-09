package com.wangguansheng.cms.utils;
/**
 * 
 * @ClassName: CMSException 
 * @Description: �Զ����쳣��2
 *        .���controller����ֵΪjson�����׳����쳣
 * @author: charles
 * @date: 2019��11��20�� ����3:15:26
 */
public class CMSAjaxException extends RuntimeException {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private Integer code;
	
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public CMSAjaxException() {
		
	}
	public CMSAjaxException(Integer code,String message) {
		super(message);
		this.code=code;
		this.message =message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
