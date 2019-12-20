<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>评论</title>
</head>
<body>

 	<div align="center">
	    <dl>
	      <c:forEach items="${info.list}" var="comment">
	       <dt style="font-size: 20px"><a href="/article?id=${comment.articleId }" target="_blank">${comment.article.title }</a></dt>
	       	 <dt>用户：${comment.user.username }</dt>
	       <dd>时间：<fmt:formatDate value="${comment.created }" pattern="yyyy-MM-dd HH:mm:ss"/> </dd>
	      <dd>内容：${comment.content }</dd>
	      <hr>
	      </c:forEach>
	   </dl>
	  </div>
</body>
</html>