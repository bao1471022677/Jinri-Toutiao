<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>文章列表</title>
</head>
<body>
	
	<script type="text/javascript">
	$(function(){
		 $("#status").val('${article.status}'); 
	  })
		function query() {
			//在中间区域加载用户页面
			var url ="/article/queryArticle?title=" + $("[name='title']").val()+"&status="+$("[name='status']").val();
			$("#conter").load(url);
		}
	</script>
	
	<div style="text-align: center;">
		<div class="form-group form-inline">
			<label for="title"> 标题:</label> <input id="title" type="text"
				class="form-control form-control-sm" name="title"
				value="${article.title }">&nbsp; 文章状态: <select
				class="form-control form-control-sm" name="status" id="status">
				<option value="0">待审</option>
				<option value="1">已审</option>
				<option value="-1">驳回</option>
				<option value="">全部</option>

			</select> &nbsp;

			<button type="button" class="btn btn-success btn-sm"
				onclick="query()">查询</button>
		</div>
	<div>
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
						<td>
							<c:if test="${list.deleted==0 }">
								<button type="button" class="btn btn-warning"
									onclick="del(this,${list.id})">正常</button>
							</c:if> 
							<c:if test="${list.deleted==1 }">
								<button type="button" class="btn btn-danger"
									onclick="del(this,${list.id})">已删除</button>

							</c:if>
						</td>
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
	
	</div>
	<script type="text/javascript">
	
		//详情
		function detail(id) {
			$("#conter").load("/admin/article?id="+id);
		}
		/* 分页 */
		function goPage(pagination) {
			var url = "/article/queryArticle?pageNum=" + pagination + "&title="
					+ $("[name='title']").val()
			$("#conter").load(url);
		}
		
		//修改状态
		function update(obj,id){
			  //0:正常 1:停用
			  //如果当前状态为正常,则改为停用.如果是停用则改为正常
			
			  var hot =$(obj).text()=="否"?"1":"0";
			 
			  $.post("/article/update",{id:id,hot:hot},function(flag){
		        if(flag){
		        //	alert("操作成功");
		        	$(obj).text(hot==0?"否":"是");//先改变按钮内容
		        	$(obj).attr("class",hot=="1"?"btn btn-success":"btn btn-info")//改变按钮颜色
		        }else{
		        	alert("操作失败")
		        }		  
			  })
			  
		  }
		
		//删除文章
		function del(obj,id){
			  //0:正常 1:已删除
			  //如果当前状态为正常,则改为停用.如果是停用则改为正常
			  var deleted =$(obj).text()=="正常"?"1":"0";
			 
			  $.post("/article/update",{id:id,deleted:deleted},function(flag){
		        if(flag==true){
		        	alert("操作成功");
		        	$(obj).text(deleted==0?"正常":"已删除");//先改变按钮内容
		        	$(obj).attr("class",deleted=="1"?"btn btn-danger":"btn btn-warning")//改变按钮颜色
		        }else{
		        	alert(flag.msg);
		        }	  
			  })
			  
		  }
		
		
	</script>
	
</body>
</html>