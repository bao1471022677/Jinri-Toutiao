<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户列表</title>
<!-- 引入样式 -->
<link href="/resource/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript">
 function goPage(page){
	 //location.href="/user/selects?page="+page
		var  url="/admin/article/complains?page="+page
		$("#center").load(url);	 //
			 
 }
 function query(){
	 var  url="/admin/article/complains?username="+$("[name='username']").val()
	 $("#center").load(url)
 }
 
 </script>
</head>
<body>
	<div class="container-fluid">
		<div class="form-group form-inline">
			<div class="form-group form-inline">
				举报类型:<select name="typename" class="form-control">
					<option>垃圾广告</option>
					<option>政治反动</option>
					<option>钓鱼网站</option>
				</select>
			</div>
			<button class="btn btn-info" type="button" onclick="query()">
				查询</button>

		</div>

		<table class="table table-bordered table-hover">
			<tr align="center">
				<td>序号</td>
				<td>文章标题</td>
				<td>文章url</td>
				<td>举报类型</td>
				<td>举报内容</td>
				<td>举报人</td>
				<td>举报时间</td>
				<td>举报数量</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${info.list}" var="u" varStatus="i">
				<tr align="center">
					<td>${i.count}</td>
					<td>${u.article.title }</td>
					<td>${u.url }</td>
					<td>${u.typename }</td>
					<td>${u.content }</td>
					<td>${u.user.username }</td>
					<td>${u.created }</td>
					<td>${u.article.complainNum }</td>
					<td>禁止</td>
				</tr>

			</c:forEach>
			<tr align="center">
				<td colspan="10"><jsp:include
						page="/WEB-INF/views/common/pages.jsp" /></td>

			</tr>

		</table>
	</div>
</body>
<script type="text/javascript">
//更新用户状态 locked   1:停用, 0:正常
function update(id,obj){
	//要改变为的状态
	var locked =$(obj).text()=="正常"?1:0;
	
	$.post("/admin/user/update",{id:id,locked:locked},function(flag){
		if(flag){
			//alert("操作成功");
			//改变内容
			$(obj).text(locked==1?"停用":"正常");
			//改变颜色
			$(obj).attr("class",locked==1?"btn btn-warning":"btn btn-success")
		}else{
			alert("操作失败")
		}
	})
	
}

</script>

</html>