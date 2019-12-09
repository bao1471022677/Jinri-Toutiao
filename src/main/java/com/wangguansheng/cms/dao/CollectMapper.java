package com.wangguansheng.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangguansheng.cms.domain.Collect;
import com.wangguansheng.cms.domain.User;
//�ղ�
public interface CollectMapper {
	// ����
	int insert(Collect Collect);

	//�б�
	List<Collect> selects(User user);

	//���ݵ�¼�˺����±����ѯ�Ƿ��ղ�
	int selectByText(@Param("text") String text, @Param("user") User user);

	//ɾ���ղ�
	int deleteById(Integer id);

}	
	
