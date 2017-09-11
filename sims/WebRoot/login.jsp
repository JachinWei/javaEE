<%@page language="java" import="java.util.*" pageEncoding="UTF-8"
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
<meta charset="UTF-8">
<title>登录-学生信息管理系统</title>
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
	<div class="container">
		<div class="row">
			<form action="<%=path%>/user?method=login" method="post" role="form"
				onsubmit="return submiting()">
				<h2>欢迎登录</h2>
				<div class="input-group login-text-size">
					<span class="input-group-addon">@</span> <input name="username"
						type="text" class="form-control" placeholder="用户名"
						value="${username}">
				</div>
				<br />
				<div class="input-group login-text-size">
					<span class="input-group-addon">@</span> <input name="password"
						type="password" class="form-control" placeholder="密码"
						value="${password}">
				</div>
				<br />
				<button type="submit" class="btn btn-default">登录</button>
				<div class="btn btn-default">
					<a href="<%=path%>/register.jsp">注册</a>
				</div>
				<br /> <br />
				<p class="text-danger" id="info">${resultStr eq null?"":resultStr}</p>
			</form>
		</div>
	</div>
	<script>
		function valUsername() {
			var str = $("input[name='username']").val();
			var pattern = /^[a-zA-Z][a-zA-Z0-9_]{3,9}$/;
			$("p[id='info']").html("");
			if (str == null || str == "") {
				$("p[id='info']").html("用户名不能为空!");
				return false;
			}
			if (!pattern.exec(str)) {
				$("p[id='info']").html("用户名格式错误:字母开头+3~9位字母或数字!");
				return false;
			}
			return true;
		}
	
		function valPassword() {
			var str = $("input[name='password']").val();
			var pattern = /^[a-zA-Z][a-zA-Z0-9_]{5,9}$/;
			$("p[id='info']").html("");
			if (str == null || str == "") {
				$("p[id='info']").html("密码不能为空!");
				return false;
			}
			if (!pattern.exec(str)) {
				$("p[id='info']").html("密码格式错误:字母开头+5~9位字母或数字!");
				return false;
			}
			return true;
		}
	
		function submiting() {
			if (valUsername() && valPassword()) {
				return true;
			} else {
				return false;
			}
		}
	
		$(function() {
			//用户名检查
			$("input[name='username']").focusout(function() {
				valUsername();
			});
			//密码检查
			$("input[name='password']").focusout(function() {
				valPassword();
			});
	
			$("button[type='reset']").click(function() {
				$("p[id='info']").html("");
			});
	
		})
	</script>
</body>
</html>