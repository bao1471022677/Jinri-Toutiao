package com.wangguansheng.cms.service;

import com.github.pagehelper.PageInfo;
import com.wangguansheng.cms.domain.Complain;
import com.wangguansheng.cms.vo.ComplainVO;

public interface ComplainService {
	//�ٱ�
		boolean insert(Complain complain);
		
	//��ѯ�ٱ�
	PageInfo<Complain> selects(ComplainVO complainVO,Integer page,Integer pageSize);
}
