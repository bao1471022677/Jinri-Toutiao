package com.wangguansheng.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangguansheng.cms.domain.Collect;
import com.wangguansheng.cms.domain.User;
//收藏
public interface CollectMapper {
	// 增加
	int insert(Collect Collect);

	//列表
	List<Collect> selects(User user);

	//根据登录人和文章标题查询是否收藏
	int selectByText(@Param("text") String text, @Param("user") User user);

	//删除收藏
	int deleteById(Integer id);

}	
	
