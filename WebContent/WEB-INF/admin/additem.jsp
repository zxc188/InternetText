<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加试题</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link rel="stylesheet"
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<style>
body {
	padding-top: 50px;
	padding-left: 50px;
}

.footer {
	position: absolute;
	bottom: 0;
	right: 0;
}
</style>
</head>
<body>
	<div class="navbar navbar-fixed-top navbar-inverse" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="">在线考试</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="Login"><span class="glyphicon glyphicon-home"></span>
							首页</a></li>
					<li><a href="AdminGreads"><span
							class="glyphicon glyphicon-paperclip"></span> 考试结果</a></li>
					<li><a href="AdminText"><span
							class="glyphicon glyphicon-paperclip"></span> 修改试题</a></li>
					<li class="active"><a href="#"><span
							class="glyphicon glyphicon-paperclip"></span> 添加试题</a></li>
					<li><a href="AddText"><span
							class="glyphicon glyphicon-paperclip"></span> 添加试卷</a></li>
					<li><a href="Logout"><span class="glyphicon glyphicon-off"></span>
							注销</a></li>
				</ul>
			</div>
		</div>
	</div>
	<script
		src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script
		src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<%
		String many1 = (String) request.getAttribute("howmany");
		int many = Integer.parseInt(many1);
	%>
	<div class="container" style="padding: 40px 10px 100px;">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<form role="form" action="AlteText" method="get">
					<div class="form-group">
						<label for="exampleInputEmail1">题目</label><input type="text"
							name="pro" class="form-control" />
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">A</label><input type="text"
							name="one" class="form-control" />
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">B</label><input type="text"
							name="two" class="form-control" />
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">C</label><input type="text"
							name="three" class="form-control" />
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">D</label><input type="text"
							name="four" class="form-control" />
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">答案</label> <input type="text"
							name="result" class="form-control" />
					</div>
					<div class="form-group">
					<label for="exampleInputPassword1">选择试卷</label>
					<br>
							<%
								for (int i = 0; i < many; i++) {
							%>
							<input type="checkbox"
							name="which" value="<%=i+1 %>" />试卷<%=i+1 %>
							<%
								}
							%>
					</div>
					<br>
					<br>
					<div class="form-group">
						<input type="submit" class="btn btn-default" name="submit"
							value="提交">
					</div>
					<div class="form-group">
						<input type="submit" class="btn btn-default" name="submit"
							value="取消">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>