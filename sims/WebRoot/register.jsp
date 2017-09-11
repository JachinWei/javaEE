<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
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
<title>注册-学生信息管理系统</title>
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
			<form action="<%=path%>//user?method=register" method="post"
				role="form" onsubmit="return submiting()">
				<h2>欢迎注册</h2>
				<div class="input-group login-text-size">
					<span class="input-group-addon">@</span> <input name="stuNumber"
						type="text" class="form-control" placeholder="绑定学号"
						value="${param.stuNumber}">
				</div>
				<br />
				<div class="input-group login-text-size">
					<span class="input-group-addon">@</span> <input name="username"
						type="text" class="form-control" placeholder="用户名"
						value="${param.username}">
				</div>
				<br />
				<div class="input-group login-text-size">
					<span class="input-group-addon">@</span> <input name="password"
						type="password" class="form-control" placeholder="密码"
						value="${param.password}">
				</div>
				<br />
				<div class="btn btn-default">
					<a href="<%=path%>/login.jsp">登录</a>
				</div>
				<button type="submit" class="btn btn-default">注册</button>
				<br /> <br />
				<p class="text-danger" id="info">${resultStr eq null?"":resultStr}
				</p>
			</form>
		</div>
	</div>
	<script>
		function valStuNumber() {
			var str = $("input[name='stuNumber']").val();
			var pattern = /^[0-9]{12}$/;
			$("p[id='info']").html("");
			if (str == null || str == "") {
				$("p[id='info']").html("绑定的学号不能为空!");
				return false;
			}
			if (!pattern.exec(str)) {
				$("p[id='info']").html("学号格式错误:12位数字!");
				return false;
			}
			return true;
	
		}
	
		function valUserame() {
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
			if (valStuNumber() && valUserame() && valPassword()) {
				return true;
			} else {
				return false;
			}
		}
	
		$(function() {
			//学号检查
			$("input[name='stuNumber']").focusout(function() {
				valStuNumber();
			});
	
			//用户名检查
			$("input[name='username']").focusout(function() {
				valUserame();
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
