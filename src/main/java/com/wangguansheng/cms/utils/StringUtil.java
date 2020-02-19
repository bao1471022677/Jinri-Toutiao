package com.wangguansheng.cms.utils;

import java.io.InputStream;
import java.net.URL;

public class StringUtil {
	
	public static boolean isHttpUrl(String param) {
		 URL url;  
		 try {  
	         url = new URL(param);  
	         InputStream in = url.openStream();  
	         return true; 
	    } catch (Exception e1) {  
	         System.out.println("连接打不开");  
	       
	    }  
		 return false;
	}
	
}
