<%@page import="domain.Problem"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>更改试题</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

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
	<%
		String text = (String) request.getAttribute("text");
		Problem porblem = (Problem) request.getAttribute("pro");
		String index = (String) request.getAttribute("index");
	%>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<form role="form" action="Datainput?text=<%=text%>&index=<%=index%>"
					method="post">
					<div class="form-group">
						<label for="exampleInputEmail1">题目</label><input type="text"
							name="pro" class="form-control" value="<%=porblem.getPro() %>" />
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">A</label><input type="text"
							name="one" class="form-control" value="<%=porblem.getOne()%>" />
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">B</label><input type="text"
							name="two" class="form-control" value="<%=porblem.getTwo()%>" />
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">C</label><input type="text"
							name="three" class="form-control" value="<%=porblem.getThree()%>" />
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">D</label><input type="text"
							name="four" class="form-control" value="<%=porblem.getFour()%>" />
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">答案</label> <input type="text"
							name="result" class="form-control"
							value="<%=porblem.getResult()%>" />
					</div>
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