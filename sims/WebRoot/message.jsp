<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<meta http-equiv="refresh" content="3; url=<%=path%>/login/index.jsp" />
<meta charset="UTF-8">
<title>跳转-学生信息管理系统</title> 
<link href="<%=path%>/lib/css/bootstrap-3.3.2.min.css" rel="stylesheet">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="<%=path%>/lib/js/jquery-2.1.3.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="<%=path%>/lib/js/bootstrap-3.3.2.min.js"></script>
<script src="<%=path%>/lib/js/prettify.js"></script>
<link href="<%=path%>/lib/css/my.css" rel="stylesheet">
<link href="<%=path%>/lib/css/bootstrap-multiselect.css" rel="stylesheet">
<script src="<%=path%>/lib/js/bootstrap-multiselect.js"></script>
</head>
<body>
	<p class=""></p>
	<h2>${message}</h2>
</body>
</html>
