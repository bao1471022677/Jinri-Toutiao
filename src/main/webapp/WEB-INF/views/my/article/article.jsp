<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户列表</title>
</head>
<body>
	
	</script>
	<div class="table table-hover  table-bordered">
		<table class="table table-hover  table-bordered">
			<thead>
				<tr>
					<th>序号</th>
					<th>文章标题</th>
					<th>作者</th>
					<th>是否热门</th>
					<th>所属栏目</th>
					<th>所属分类</th>
					<th>更新时间</th>
					<th>是否删除</th>
					<th>审核状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${articleList.list }" var="list" varStatus="i">
					<tr>
						<td>${(articleList.pageNum-1) * articleList.pageSize+i.index+1  }</td>
						<td>${list.title }</td>
						<td>${list.user.username }</td>
						<td>
						<c:if test="${list.hot==0 }">
							<button type="button" class="btn btn-info" onclick="update(this,${list.id})">否</button>
						</c:if>
						<c:if test="${list.hot==1 }">
							<button type="button" class="btn btn-success" onclick="update(this,${list.id})">是</button>
						</c:if>
						</td>
						<td>${list.channel.name }</td>
						<td>${list.category.name }</td>
						<td><fmt:formatDate value="${list.updated }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>${list.deleted==0?"正常":"管理员已删除" }</td>
						<td>${list.status==0?"待审":list.status==1?"已审":"驳回" }</td>
						<td><button type="button" onclick="detail(${list.id})">详情</button></td>
					</tr>
				</c:forEach>
			</tbody>
		</table><br>
			<!-- 分页  方法二 -->
		<div>
		<nav aria-label="Page navigation example">
		<ul class="pagination">
			<li class="page-item"><a class="page-link" href="javascript:goPage(${articleList.prePage==0?1:articleList.prePage })"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
			</a></li>
			<c:forEach items="${articleList.navigatepageNums}" var="n">
				<li class="page-item"><a class="page-link" href="javascript:goPage(${n})">${n}</a></li>
			</c:forEach>

			<li class="page-item"><a class="page-link" href="javascript:goPage(${articleList.nextPage ==0?articleList.pages:articleList.nextPage})"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>
		</ul>
		</nav>
		</div>		
	</div>
	
	<script type="text/javascript">
	
		//详情
		function detail(id) {
			var url = "/my/myArticle?id="+id;
			 $("#center").load(url);
		}
		
		function goPage(pagination){
			 var url ="/my/selectByUser?pageNum="+pagination;
			 //在中间区域加载分页页面
			 $("#center").load(url);
		 }
		
	</script>
	
</body>
</html>