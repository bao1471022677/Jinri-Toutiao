package com.wangguansheng.cms.utils;



import org.apache.commons.codec.digest.DigestUtils;
/**
 *
 * @ClassName: Md5Util 
 * @Description: TODO
 * @author: bobo
 * @date: 2019��5��12�� ����8:53:18
 */
public class Md5Util {
	/**
	 * ֱ�Ӷ��������ɢ�У���ô�ڿͿ��Զ�ͨ������������ɢ��ֵ��
	 * Ȼ��ͨ����ɢ��ֵ�ֵ䣨����MD5�����ƽ���վ�����õ�ĳ�û������롣
	 * ��Salt����һ���̶��Ͻ���������
	 */
	//����ֵ :
	private static String salt="1a2b3c4d";
	
	public static String md5Encoding(String password) {
		
		return  DigestUtils.md5Hex(password+salt);
	}

	
	public static void main(String[] args) {
		
		
		System.out.println(Md5Util.md5Encoding("1"));
		System.out.println(Md5Util.md5Encoding("123"));
		
	}
}
