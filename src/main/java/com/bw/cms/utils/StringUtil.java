package com.bw.cms.utils;

import java.io.InputStream;
import java.net.URL;

public class StringUtil {
	
	//校验传入的参数是否为url
		public static boolean isHttpurl(String param) {
			 URL url;  
			 try {  
		         url = new URL(param);  
		         InputStream in = url.openStream();  
		         return true; 
		    } catch (Exception e1) {  
		         System.out.println("连接打不开!");  
		       
		    }  
			 return false;
		}
	
}
