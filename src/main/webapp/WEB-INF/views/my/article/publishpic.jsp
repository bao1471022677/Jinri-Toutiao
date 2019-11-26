<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<meta charset="UTF-8">
<title>图片集上传</title>
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
</head>
<body>
	<br><br>
	<div class="container">
		<form id="form1">
		
			<input type="button" onclick="addCard()" value="添加Card">
			<input type="button" onclick="publishPic()" value="发布图片">  
				<br><br>
			<div class="form-group">
				<label for="title">标题</label> 
				<input type="text" class="form-control" name="title" id="title">
			</div>
				<hr>
			
			<div id="mdiv" style="border: 1px solid pink">
				<div id="card1" style="float: left">
					<div class="card" style="width: 15rem;">
						<div class="card-header">
							<label for="title">请选择图片:	</label>
							<input type="file" name="files" id="file" class="form-control">
						</div>
						<div class="card-body">
							图片描述:
							<textarea rows="5" cols="" name="descr" style="width: 13rem;"></textarea>
						</div>
					</div>
				</div>
				
			</div>
			
		</form>
	</div>
	
	<script type="text/javascript">
		
		function addCard() {
			$("#mdiv").append($("#card1").html());
		}
		//发布图片
		function publishPic() {
			
			var formData = new FormData($( "#form1" )[0]);
			$.ajax({
				url : "/my/publishpic",
				type : "post",
				data : formData,
				//告诉jQuery 不要处理 发送 的数据
				processData : false,
				// 告诉jQuery 不要设置 Content-Type请求
				contentType : false,
				success:function(falg){
					if(falg){
						alert("成功发布")
						location.href="/my/index";
					}else{
						alert("失败发布，尝试重新发布！")
					}
				}
				
			})
			
		}
		
	</script>
	
</body>
</html>