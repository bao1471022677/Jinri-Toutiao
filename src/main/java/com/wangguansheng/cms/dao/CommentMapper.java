package com.wangguansheng.cms.dao;

import java.util.List;

import com.wangguansheng.cms.domain.Comment;

public interface CommentMapper {
	
	//查询评论
	List<Comment> selects(Comment comment);
	
	//添加评论
	int insert(Comment comment);
	
}
