package com.wangguansheng.cms.utils;

public class PageUtil {

	/**
	 * 
	 * @param page ��ǰ�ڼ�ҳ
	 * @param pages ��ҳ��
	 * @param url ���õ�url  list?name=12&sex=��&page=3
	 * 	 * @param pageSize ��ʾ����ҳ
	 * @return
	 */
	  public static String page(int page, int pages, String url, int pageSize)
      {
          if (page< 1) page= 1;
          if (page> pages) page= pages;
          // ����url�Ƿ����,�ı�ƴ�ӵķ���
          String flag = url.indexOf("?") != -1 ? "&" : "?";
          
          
          StringBuilder sb = new StringBuilder();
          
          sb.append("<nav aria-label='Page navigation example'>");
          //String.format("%1$s%2$s", "abc","123");
          
          //'url?page=1'   ,url, flag,"page" ,page- 1 < 1 ? 1 : page- 1)
          
          
          sb.append(String.format("<ul class='pagination'><li class='page-item'><a class='page-link' "
          		+ " href='javascript:void(0)' "
          		+ "data='%1$s%2$s%3$s=%4$s' aria-label='Previous'> "
          		+ " <span aria-hidden='true'>&laquo;</span></a></li>" 
          		,url, flag,"page" ,page- 1 < 1 ? 1 : page- 1));
          //�迪ʼҳΪ1
          int beginPage = 1;
          //�м�ҳΪ pageSize/2
          int midPage = pageSize / 2;
          if (page> midPage)
          {
              beginPage = page- midPage;
          }
          for (int i = 0; i < pageSize; i++)
          {
        	  //��ǰҳ��������  data="url?username=lucy&page=2"
              sb.append(String.format("<li class='page-item'><a  class='page-link'  href='javascript:void(0)' data='%1$s%2$s%3$s=%4$s' >%4$s</a></li>" ,url, flag,"page" ,beginPage + i));
              //��������ҳ��������ҳ�����˳�ѭ��
              if (beginPage + i >= pages)
              {
                  break;
              }
          }
          sb.append(String.format("<li class='page-item'><a  class='page-link' href='javascript:void(0)' data='%1$s%2$s%3$s=%4$s' aria-label='Next' ><span aria-hidden='true'>&raquo;</span></a></li>" ,url, flag,"page" ,page+ 1 >= pages ? pages : page+ 1));
          sb.append("</ul></nav>");
          return sb.toString();
      }
}