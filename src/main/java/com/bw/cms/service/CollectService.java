package com.bw.cms.service;

import org.apache.ibatis.annotations.Param;

import com.bw.cms.domain.Collect;
import com.bw.cms.domain.User;
import com.github.pagehelper.PageInfo;

public interface CollectService {
	
	/**
	 * 
	 * @Title: insert 
	 * @Description: ����
	 * @param links
	 * @return
	 * @return: int
	 */
	boolean insert(Collect Collect);
	/**
	 * 
	 * @Title: selects 
	 * @Description: �б�
	 * @return
	 * @return: List<Links>
	 */
   PageInfo<Collect> selects(Integer page,Integer pageSize,User user);
   
   
   /**
    * 
    * @Title: selectByText 
    * @Description: ���ݵ�¼�˺����±����ѯ�Ƿ��ղ�
    * @param text
    * @return
    * @return: int
    */
   int selectByText(@Param("text")String text ,@Param("user")User user);
    /**
     * 
     * @Title: deleteById 
     * @Description: ɾ���ղ�
     * @param id
     * @return
     * @return: int
     */
    boolean deleteById(Integer id);
	
}
