<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div>
		<dl>
			<dt>
				<h2>${articleDetails.title }</h2>
			</dt>
			<br/>
			<dd>
				<button type="button" onclick="update(${articleDetails.id},1)">同意</button>
				<button type="button" onclick="update(${articleDetails.id},-1)">驳回</button>
				<button type="button" onclick="back()">返回文章列表</button>
			</dd>
			<dd><fmt:formatDate value="${articleDetails.updated }" pattern="yyyy-MM-dd HH:mm:ss"/></dd>
			<dd>${articleDetails.content }</dd>
		</dl>
	</div>
	
	<script type="text/javascript">
		//页面嵌入
		function name() {
			 $("#conter").load("/article/queryArticle?title=" + $("[name='title']").val());
		}
		//修改
		function update(id,status) {
			$.ajax({
				url:"/article/update",
				type:"post",
				data:{id:id,status:status},
				success:function(res){
					if(res){
						alert("操作成功");
						$("#conter").load("/article/queryArticle");
					}else{
						alert("失败");
					}
				}
			})
		}
		//返回
		 function back(){
			 $("#conter").load("/article/queryArticle")
		 }
		
	</script>
	
</body>
</html>