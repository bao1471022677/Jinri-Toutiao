package com.bw.cms.utils;
/**
 * 
 * @ClassName: CMSException 
 * @Description: �Զ����쳣��
 * @author: charles
 * @date: 2019��11��20�� ����3:15:26
 */
public class CMSException extends RuntimeException {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
	public CMSException() {
		
	}
	public CMSException(String message) {
		super(message);
		this.message =message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
