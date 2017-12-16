<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录</title>
<link rel="stylesheet"
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<style>
.footer {
	position: absolute;
	bottom: 0;
	right: 0;
}
</style>
<script
	src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script
	src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<%
		if (application.getAttribute("howmany") == null) {
			application.setAttribute("howmany", 5);
		}
		if (session.getAttribute("user") != null) {
			response.sendRedirect("Servlet/Login");
		}
	%>
	<div style="padding: 100px 500px 50px;">
		<form class="bs-example bs-example-form" role="form"
			action="Servlet/Login" method="post">
			<span class="input-group-addon glyphicon glyphicon-star ">考试系统</span>
			<br>
			<div class="input-group">
				<span class="input-group-addon ">用户名</span> <input type="text"
					class="form-control" placeholder="请输入用户名" name="username">
			</div>
			<br>
			<div class="input-group">
				<span class="input-group-addon">密码 &nbsp; &nbsp;</span> <input
					type="password" class="form-control" placeholder="请输入密码"
					name="password">
			</div>
			<br> <input type="submit" class="btn  btn-lg btn-block"
				value="登录" name="submit" /> <br>
			<%
				if (session.getAttribute("login") != null && session.getAttribute("login").equals("0")) {
			%>
			<div class="alert alert-danger">用户名或密码输入错误！！！</div>
			<%
				}
			%>
		</form>
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
	</div>
</body>
</html>