package com.wangguansheng.cms.service;

import com.github.pagehelper.PageInfo;
import com.wangguansheng.cms.domain.Comment;

public interface CommentService {
	/**
	 * ��������
	 * @Title: insert 
	 * @Description: TODO
	 * @param comment
	 * @return
	 * @return: int
	 */
	int insert(Comment comment);
	/**
	 * 
	 * @Title: selects 
	 * @Description: ��ѯ����
	 * @param comment
	 * @return
	 * @return: List<Comment>
	 */
	PageInfo<Comment> selects(Comment comment,Integer page,Integer pageSize);
}
