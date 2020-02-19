<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户表</title>
<link rel="stylesheet" type="text/css"
	href="/resource/css/bootstrap.min.css" />
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/css/bootstrap.min.js"></script>
</head>
<body>

	<div style="text-align: center;">
	<div class="form-group form-inline">
			<label for="username"> 用户名:</label> <input id="username" type="text"
				class="form-control form-control-sm" name="username"
				value="${user.username }">&nbsp;
			<button type="button" class="btn btn-success btn-sm"
				id="cha">查询</button>
		</div>
	<table class="table table-hover">
		<thead>
			<tr>
				<th scope="col">编号</th>
				<th scope="col">名字</th>
				<th scope="col">昵称</th>
				<th scope="col">性别</th>
				<th scope="col">生日</th>
				<th scope="col">注册时间</th>
				<th scope="col">更新时间</th>
				<th scope="col">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${userList.list }" var="list" varStatus="i">
				<tr>
					<td scope="row">${i.index+1 }</td>
					<td>${list.username }</td>
					<td>${list.nickname }</td>
					<td>${list.gender==0?"男":"女" }</td>
					<td><fmt:formatDate value="${list.birthday }" pattern="yyyy-MM-dd"/></td>
					<td><fmt:formatDate value="${list.created }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td><fmt:formatDate value="${list.updated }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>
						<c:if test="${list.locked==0 }">
							<button class="btn btn-success" onclick="update(this,${list.id})">正常</button>
						</c:if>
						<c:if test="${list.locked==1 }">
							<button class="btn btn-danger" onclick="update(this,${list.id})">停用</button>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
		<!-- 分页  方法一 -->
		<%-- <jsp:include page="/WEB-INF/views/common/pages.jsp"/> --%>
		<!-- 分页  方法二 -->
		<div>
		<nav aria-label="Page navigation example">
		<ul class="pagination">
			<li class="page-item"><a class="page-link" href="javascript:goPage(${userList.prePage==0?1:userList.prePage })"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
			</a></li>
			<c:forEach items="${userList.navigatepageNums}" var="n">
				<li class="page-item"><a class="page-link" href="javascript:goPage(${n})">${n}</a></li>
			</c:forEach>

			<li class="page-item"><a class="page-link" href="javascript:goPage(${userList.nextPage ==0?userList.pages:userList.nextPage})"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>
		</ul>
		</nav>
		</div>
	</div>
	
	<script type="text/javascript">
		
		function update(obj,id) {
			//0：是正常，1：是停用  如果当前状态为正常,则改为停用.如果是停用则改为正常
			var locked = $(obj).text()=="正常"?"1":"0";
			$.ajax({
				url:"/user/update",
				type:"post",
				dataType:"json",
				data:{id:id,locked:locked},
				success:function(res){
					if(res){
						$(obj).text(locked==1?"停用":"正常");//先改变按钮内容
						$(obj).attr("class",locked=="0"?"btn btn-success":"btn btn-danger")//改变按钮颜色
					}else{
						alert("失败");
					}
				}
			})
			
		}		
		
		 function goPage(pagination){
			 var url ="/user/queryUser?pageNum="+pagination+"&username="+$("[name='username']").val();
			 //在中间区域加载分页页面
			 $("#conter").load(url);
			 
		 }
		$("#cha").click(function () {
			var username = $("#username").val();
			//在中间区域加载用户页面
			$("#conter").load("/user/queryUser?username="+username);
		})
		
	
	</script>
	
</body>
</html>