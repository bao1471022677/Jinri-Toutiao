package com.wangguansheng.cms.dao;

import java.util.List;

import com.wangguansheng.cms.domain.Complain;
import com.wangguansheng.cms.vo.ComplainVO;

public interface ComplainMapper {

	
	int insert(Complain complain);
	
	//��ѯ�ٱ�
	List<Complain> selects(ComplainVO complainVO);

}
