<%@page import="java.util.LinkedList"%>
<%@page import="domain.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>成绩</title>
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
					<li><a href="Text"><span
							class="glyphicon glyphicon-list-alt"></span> 考试</a></li>
					<li class="active"><a href="#"><span
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
		User user = (User) request.getAttribute("user");
		LinkedList<String> greads = user.getList();
	%>
	<div class="container" style="padding: 40px 10px 100px;">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="panel-group" id="panel-506929">
					<%
						//循环加载试卷
						for (int i = 0; i < greads.size(); i++) {
					      String thisgread=greads.get(i);
					      if(thisgread==null){
					    	  thisgread="***";
					      }
					%>
					<div class="panel panel-default">
						<div class="panel-heading">
							<a class="panel-title collapsed" data-toggle="collapse"
								data-parent="#panel-506929" href="#panel-element-439728<%=i%>">试卷<%=i + 1%></a>
						</div>
						<div id="panel-element-439728<%=i%>"
							class="panel-collapse collapse">
							<div class="panel-body">



								<table class="table table-striped">
									<thead>
										<tr>
											<th>学号</th>
											<th>姓名</th>
											<th>成绩</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><%=user.getUsername()%></td>
											<td><%=user.getRealname()%></td>
											<td><%=thisgread%></td>
										</tr>
									</tbody>
								</table>



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