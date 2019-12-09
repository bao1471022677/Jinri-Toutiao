package com.wangguansheng.cms.utils;

/**
 * 
 * @ClassName: ArticleEnum 
 * @Description: ���µ�ö����
 * @author: charles
 * @date: 2019��10��24�� ����3:13:00
 */
public enum ArticleEnum  {
	
	
	HTML(0,"html"),IMAGE(1,"image");
	
	ArticleEnum(Integer code,String name) {
		this.code =code;
		this.name =name;
	}
	
	
	private Integer code;
	private String name;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

	public static void main(String[] args) {
		System.out.println(HTML.getCode());
		System.out.println(HTML.getName());
		//IMAGE.getCode();
	}
}