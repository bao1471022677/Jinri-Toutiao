package com.wangguansheng.cms.vo;

import com.wangguansheng.cms.domain.Complain;

public class ComplainVO extends Complain{
	
	private Integer startNum;
	private Integer endNum;
	private String startTime;
	private String endTime;
	
	
	
	
	public Integer getStartNum() {
		return startNum;
	}
	public void setStartNum(Integer startNum) {
		this.startNum = startNum;
	}
	public Integer getEndNum() {
		return endNum;
	}
	public void setEndNum(Integer endNum) {
		this.endNum = endNum;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	

}

