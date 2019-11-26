<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>文章详情</title>
</head>
<body>

	<div style="text-align: center">
		<dl>
			<dt>
				<h2>${articleDetails.title }</h2>
			</dt>
			<br/>
			<dd>
				<button type="button" onclick="back()">返回文章列表</button>
			</dd>
			<dd><fmt:formatDate value="${articleDetails.updated }" pattern="yyyy-MM-dd HH:mm:ss"/></dd>
			<dd>${articleDetails.content }</dd>
		</dl>
	</div>
	
	<script type="text/javascript">
		//返回
		 function back(){
			 $("#center").load("/my/selectByUser")
		 }
		
	</script>
	
</body>
</html>