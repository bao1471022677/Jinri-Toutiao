package com.bw.cms.vo;

import com.bw.cms.domain.User;

public class UserVO extends User {
	
	private String repassword;//ȷ������

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	
}
