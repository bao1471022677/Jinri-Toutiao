package com.bw.cms.utils;

import java.io.InputStream;
import java.net.URL;

public class StringUtil {
	
	//У�鴫��Ĳ����Ƿ�Ϊurl
		public static boolean isHttpurl(String param) {
			 URL url;  
			 try {  
		         url = new URL(param);  
		         InputStream in = url.openStream();  
		         return true; 
		    } catch (Exception e1) {  
		         System.out.println("���Ӵ򲻿�!");  
		       
		    }  
			 return false;
		}
	
}
