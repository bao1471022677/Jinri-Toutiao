package com.wangguansheng.cms.dao;

import java.util.List;

import com.wangguansheng.cms.domain.Comment;

public interface CommentMapper {
	
	//��ѯ����
	List<Comment> selects(Comment comment);
	
	//�������
	int insert(Comment comment);
	
}
