package com.wangguansheng.cms.vo;

import com.wangguansheng.cms.domain.User;

public class UserVO extends User {
	
	private String repassword;//ȷ������

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	
}
