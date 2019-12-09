package com.wangguansheng.cms.service;

import com.github.pagehelper.PageInfo;
import com.wangguansheng.cms.domain.Links;

public interface LinksService {

	/**
	 * 
	 * @Title: insert 
	 * @Description: ������������
	 * @param links
	 * @return
	 * @return: int
	 */
	boolean insert(Links links);
	/**
	 * 
	 * @Title: selects 
	 * @Description: �б���������
	 * @return
	 * @return: List<Links>
	 */
   PageInfo<Links> selects(Integer page,Integer pageSize);
}

