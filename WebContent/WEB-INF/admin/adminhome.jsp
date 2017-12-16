<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>首页</title>
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
					<li class="active"><a href="#"><span
							class="glyphicon glyphicon-home"></span> 首页</a></li>
					<li><a href="AdminGreads"><span
							class="glyphicon glyphicon-paperclip"></span> 考试结果</a></li>
				    <li><a href="AdminText"><span
							class="glyphicon glyphicon-paperclip"></span> 修改试题</a></li>
					<li><a href="AddItem"><span
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

	<div class="container" style="padding: 40px 10px 100px;">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="jumbotron">
					<h1>welcome</h1>
					<p>This is a template for a simple marketing or informational
						website. It includes a large callout called the hero unit and
						three supporting pieces of content. Use it as a starting point to
						create something more unique.</p>
					<p>
						<a class="btn btn-primary btn-large" href="">Learn more</a>
					</p>
				</div>
			</div>
		</div>
	</div>
	<div class="footer">
		<div class="container">
			<div class="row clearfix">
				<div class="col-md-12 column">
					<blockquote class="pull-right">
						<p>这是一个在线考试系统</p>
						<small>MAKE BY <cite>张晓晨</cite></small>
					</blockquote>
				</div>
			</div>
		</div>
</body>
</html>