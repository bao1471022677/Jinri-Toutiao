<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>文章详情</title>
<link rel="stylesheet" type="text/css" href="/resource/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/resource/css/cms.css" />
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.bundle.min.js"></script
</head>
<script type="text/javascript">
	function collect() {
		var text = '${article.title}';//收藏的标题
		var url = window.location.href;//收藏的地址

		$.post("/collect", {
			text : text,
			url : url
		}, function(flag) {
			if (flag.code == 0) {
				alert(flag.msg);
				$("#mc").html(
						"<span style='font-size: 20px;color: red'>★(已收藏)</span>")
			} else {
				alert(flag.msg);

			}
		})

	}
</script>	

<body>

			<div class="container" style="padding-top: 20px">
				<!-- 第一行 -->
				<div class="row" style="background-color: #5555;">
					
					<!-- 左边栏目 -->
					<div class="col-md-2" style="background-color: red;height: 300px">
						
						左边栏目
					</div>
					
					<!-- 中间新闻 -->	
					<div class="col-md-8" align="center" style="background-color: #5623;">
						<dl>
							<dt>
							  <h2>${article.title }</h2>
							</dt>
								<hr>
							<dd id="mc">
								<c:if test="${isCollect==1}">
								<span style="font-size: 20px; color: red">★ (已收藏)</span>
								</c:if>
								
								
								<c:if test="${isCollect!=1}">
									<span style="font-size: 20px; color: blue;"> <a
								    href="javascript:collect()">☆ (未收藏)</a> 
									</span>
								</c:if>
							</dd>
							<dd>
							 <button type="button" class="btn btn-info" onclick="close()">关闭</button> 
							</dd>
							<dd><fmt:formatDate value="${aritcle.updated }" pattern="yyyy-MM-dd HH:mm:ss"/> </dd>
							<dd>${article.content }</dd>
						</dl>
						<!-- 评论行 -->
						<div class="row" style="background-color: pink;height: 100px">
							<!-- 评论框 -->
							<div><jsp:include page="/WEB-INF/views/index/comment.jsp"></jsp:include></div>
							<!-- 评论 -->
							<div>
								<dl>
							      <c:forEach items="${info.list}" var="comment">
							      <dt>用户：${comment.user.username }</dt>
							      <dd>时间：<fmt:formatDate value="${comment.created }" pattern="yyyy-MM-dd HH:mm:ss"/></dd>
							      <dd>内容：${comment.content }</dd>
							      <hr>
							      </c:forEach>
							   </dl>
							</div>
					
						</div>
					</div>
					
					<!-- 右边栏目 -->
					<div class="col-md-2" style="background-color: blue;height: 300px">
						
						右边栏目
					</div>
					
				</div>
			</div>
			
			<div class="col-md-8">
				
			</div>
			
	
	<script type="text/javascript">
	 
	 function close(){
		this.close();
	 }
	
	</script>
	
	
</body>
</html>