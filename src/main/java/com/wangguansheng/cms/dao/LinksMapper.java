package com.wangguansheng.cms.dao;

import java.util.List;

import com.wangguansheng.cms.domain.Links;

public interface LinksMapper {
	
	/**
	 * 
	 * @Title: insert 
	 * @Description: ����
	 * @param links
	 * @return
	 * @return: int
	 */
	int insert(Links links);
	/**
	 * 
	 * @Title: selects 
	 * @Description: �б�
	 * @return
	 * @return: List<Links>
	 */
    List<Links> selects();
	
}
