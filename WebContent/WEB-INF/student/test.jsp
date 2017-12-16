<%@page import="domain.User"%>
<%@page import="domain.Text"%>
<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>考试</title>
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
					<li class="active"><a href="#"><span
							class="glyphicon glyphicon-list-alt"></span> 考试</a></li>
					<li><a href="Gread"><span
							class="glyphicon glyphicon-paperclip"></span> 考试结果</a></li>
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
		String many1 = (String) request.getAttribute("many");
		int many = Integer.parseInt(many1);
		LinkedList<Text> list = (LinkedList<Text>) request.getAttribute("texts");
		User user = (User) session.getAttribute("user");
	%>
	<div class="container" style="padding: 40px 10px 100px;">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="panel-group" id="panel-506929">
					<%
						//循环加载试卷
						for (int i = 0; i < many; i++) {
					%>
					<div class="panel panel-default">
						<div class="panel-heading">
							<a class="panel-title collapsed" data-toggle="collapse"
								data-parent="#panel-506929" href="#panel-element-439728<%=i%>">试卷<%=i + 1%></a>
						</div>
						<div id="panel-element-439728<%=i%>"
							class="panel-collapse collapse">
							<div class="panel-body">
							<% Text text = list.get(i);%>
								<form method="post" action="Submit?Text=<%=i + 1%>">
									<%
										//循环加载试卷中的题目
											
											for (int j = 0; j < text.getList().size(); j++) {
									%>

									<p><%=j + 1%>.<%=text.getList().get(j).getPro()%>
									<p>
										<input type="radio" name="<%=i%>pro<%=j%>" value="A">A.<%=text.getList().get(j).getOne()%>
									<p>
										<input type="radio" name="<%=i%>pro<%=j%>" value="B">B.<%=text.getList().get(j).getTwo()%>
									<p>
										<input type="radio" name="<%=i%>pro<%=j%>" value="C">C.<%=text.getList().get(j).getThree()%>
									<p>
										<input type="radio" name="<%=i%>pro<%=j%>" value="D">D.<%=text.getList().get(j).getFour()%>
										<br>
										<%
											}
										%>
										<%
											if (user.getList().get(i) == null) {
										%>
										<br> <br> <input type="submit" name="submit"
											value="提交">
										<%
											}
										%>
									
								</form>
							</div>
						</div>

					</div>
					<%
						}
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>