<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CMS后台中心</title>
<link rel="stylesheet" type="text/css" href="/resource/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/resource/css/cms.css" />
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.bundle.min.js"></script


</head>
<body>
	
	<div class="container-fluid">
		
		<div class="row" style="margin-top: 10px;min-height: 50px">
			<div class="col-md-12" style="background-color: #5555;">
				<img alt="" src="/images/tu.jpg" class="rounded-circle">
				<a class="navbar-brand mr-1" href="index.html">CMS后台中心</a>
				
				<c:choose>
					<%-- 登录显示用户菜单 --%>
					<c:when test="${sessionScope.admin != null}">
						<div class="btn-group dropleft"
							style="float: right; padding-top: 20px">
							<button type="button" class="btn btn-secondary dropdown-toggle"
								data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">${sessionScope.admin.username}</button>
							<div class="dropdown-menu">
								<ul class="nav" style="left: -88px">
									<li class="nav-item"><a class="nav-link" href="/passport/reg">注销</a></li>
								</ul>
								<ul class="nav" style="left: -88px">
									<li class="nav-item"><a class="nav-link" href="/index">首页</a></li>
								</ul>
							</div>
						</div>
					</c:when>

				</c:choose>
				
			</div>
		</div>
		
		
		<div class="row" style="margin-top:10px;min-height: 500px">
		
			<div class="col-md-2" style="">
				<ul class="nav flex-column">
					<li class="nav-item"><a class="nav-link" href="#" data="/user/queryUser">用户管理</a></li>
					<li class="nav-item"><a class="nav-link" href="#" data="/article/queryArticle">文章管理</a></li>
					<li class="nav-item"><a class="nav-link" href="#" data="/admin/queryLinks">友情连接</a></li>
					<li class="nav-item"><a class="nav-link" href="#">分类管理</a></li>
					<li class="nav-item"><a class="nav-link " href="#">系统设置</a></li>
				</ul>
			</div>
			
			<div class="col-md-10" style="" id="conter">
				<!-- 主页图片 -->
				<img alt="" src="/images/xing.gif">
				
			</div>
			
		</div>
	</div>
	
	<script type="text/javascript">
		
		//文章管理
	
		$(".nav-link").click(function () {
			 var li = $("ul li a");
			//先移除所有的list-group-item-info样式
			li.removeClass("list-group-item-danger");
			//为左侧菜单添加动态点击样式 
			$(this).addClass("list-group-item-danger");
			 //获取点击URL 
			var url = $(this).attr("data");
			$("#conter").load(url);	
		})
	</script>
	
</body>
</html>