package com.bw.cms.service;

import com.bw.cms.domain.Links;
import com.github.pagehelper.PageInfo;

public interface LinksService {

	/**
	 * 
	 * @Title: insert 
	 * @Description: 增加友情连接
	 * @param links
	 * @return
	 * @return: int
	 */
	boolean insert(Links links);
	/**
	 * 
	 * @Title: selects 
	 * @Description: 列表友情连接
	 * @return
	 * @return: List<Links>
	 */
   PageInfo<Links> selects(Integer page,Integer pageSize);
}

