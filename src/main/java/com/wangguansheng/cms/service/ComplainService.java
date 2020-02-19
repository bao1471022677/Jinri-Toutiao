package com.wangguansheng.cms.service;

import com.github.pagehelper.PageInfo;
import com.wangguansheng.cms.domain.Complain;
import com.wangguansheng.cms.vo.ComplainVO;

public interface ComplainService {
	//举报
		boolean insert(Complain complain);
		
	//查询举报
	PageInfo<Complain> selects(ComplainVO complainVO,Integer page,Integer pageSize);
}
