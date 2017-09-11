<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ page import="bean.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>首页-学生信息管理系统</title>
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
	<h2>学生信息管理系统</h2>
	<div class="container">
		<div class="row well">
			<div class="input-form">
				<button class="btn btn-default" name="userManager">账号设置</button>
				<c:choose>
					<c:when test="${powerLevel eq 1}">
						<button class="btn btn-default" name="stuManager">学生管理</button>
						<button class="btn btn-default" name="scoreManager">成绩管理</button>
						<button class="btn btn-default" name="courseManager">课程管理</button>
						<button class="btn btn-default" name="classManager">班级管理</button>
						<button class="btn btn-default" name="statistics">统计</button>
					</c:when>
					<c:otherwise>
						<button class="btn btn-default" name="scoreQuery">成绩查询</button>
					</c:otherwise>
				</c:choose>
				<button class="btn btn-default" name="logout">注销</button>
				<label class="btn-change">欢迎登录, ${username}</label>
			</div>
		</div>
	</div>
	<script>
		$(function() {
			$("button[name='userManager']").click(function() {
				var page = "<%=path%>/user?method=manager&status=get";
				$(location).attr('href', page);
			});
	
			$("button[name='stuManager']").click(function() {
				var page = "<%=path%>/stu?method=stuMain";
				$(location).attr('href', page);
			});

			$("button[name='scoreManager']").click(function() {
				var page = "<%=path%>/score?method=scoreMain";
				$(location).attr('href', page);
			});
			$("button[name='courseManager']").click(function() {
				var page = "<%=path%>/course?method=courseMain";
				$(location).attr('href', page);
			});
	
			$("button[name='classManager']").click(function() {
				var page = "<%=path%>/myclass?method=classMain";
				$(location).attr('href', page);
			});
	
			$("button[name='statistics']").click(function() {
				var page = "<%=path%>/statistics";
				$(location).attr('href', page);
			});
			
			$("button[name='scoreQuery']").click(function() {
				var page = "<%=path%>/scoreQuery";
				$(location).attr('href', page);
			});
	
			$("button[name='logout']").click(function() {
				var page = "<%=path%>/user?method=logout";
				$(location).attr('href', page);
			});
		})
	</script>
</body>
</html>
