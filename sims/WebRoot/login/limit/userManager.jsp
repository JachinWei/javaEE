<%@ page language="java" import="java.util.*,bean.*"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();//取得绝对路径
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<title>账户管理-学生管理系统</title> <link href="<%=path%>/lib/css/bootstrap-3.3.2.min.css" rel="stylesheet">

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
	<jsp:include page="/login/index.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<form action="<%=path%>/user?method=manager&status=save"
				method="post" role="form" onsubmit="return submiting()">
				<div class="input-group text-size">
					<span class="input-group-addon">用户名</span> <input name="username"
						type="text" class="form-control" value="${user.username}" disabled />
				</div>
				<div class="input-group text-size">
					<span class="input-group-addon">新密码</span> <input name="password"
						type="password" class="form-control" placeholder="空则不修改密码" />
				</div>
				<div class="input-group text-size">
					<span class="input-group-addon">身份</span>
					<c:choose>
						<c:when test="${powerLevel eq 1}">
							<input name="powerLevel" type="text" class="form-control"
								value="管理员" disabled />
						</c:when>
						<c:otherwise>
							<input name="powerLevel" type="text" class="form-control"
								value="学生" disabled />
						</c:otherwise>
					</c:choose>
				</div>
				<c:choose>
					<c:when test="${powerLevel eq 3}">
						<div class="input-group text-size">
							<span class="input-group-addon">姓名</span> <input name="name"
								type="text" class="form-control" value="${stu.name}" />
						</div>
						<div class="input-group text-size">
							<span class="input-group-addon">性别</span>
							<div class="form-control checkbox-inline">
								· <input type="radio" name="sex" value="0" checked> 保密 <input
									type="radio" name="sex" value="1"> 男 <input
									type="radio" name="sex" value="2"> 女
							</div>
						</div>
						<div class="input-group text-size">
							<span class="input-group-addon">学号</span> <input name="stuNumber"
								type="text" class="form-control" value="${stu.stuNumber}"
								disabled />
						</div>
						<div class="input-group text-size">
							<span class="input-group-addon">生日</span> <input name="birthday"
								type="text" value="${stu.birthday}" class="form-control" />
						</div>
						<div class="input-group text-size">
							<span class="input-group-addon">邮箱</span> <input name="email"
								type="text" value="${stu.email}" class="form-control" />
						</div>
						<div class="input-group text-size">
							<span class="input-group-addon">手机号</span> <input name="phone"
								type="text" value="${stu.phone}" class="form-control" />
						</div>
					</c:when>
				</c:choose>
				<button type="submit" class="btn btn-default">保存</button>
				<br />
				<p class="text-danger" id="info">${resultStr eq null?"":resultStr}
				</p>
			</form>
		</div>
	</div>
	<script>
	
	   function valUserame(){
			var str = $("input[name='username']").val();
			var pattern = /^[a-zA-Z][a-zA-Z0-9_]{3,9}$/;
			$("p[id='info']").html("");
			if(str == null || str == ""){
				$("p[id='info']").html("用户名不能为空!");
				return false;
			}
			if(!pattern.exec(str)){
        		$("p[id='info']").html("用户名格式错误:字母开头+3~9位字母或数字!");
        		return false;
      		}
      		return true;
		}
		
		function valPassword(){
			var str = $("input[name='password']").val();
			var pattern = /^[a-zA-Z][a-zA-Z0-9_]{5,9}$/;
			$("p[id='info']").html("");
			if(str == null || str == ""){
				return true;
			}
			if(!pattern.exec(str)){
        		$("p[id='info']").html("密码格式错误:字母开头+5~9位字母或数字!");
        		return false;
      		}
      		return true;
		}
		
		function valName(){
			var str = $("input[name='username']").val();
			$("p[id='info']").html("");
			if(str == null || str == ""){
				return true;
			}
			if(length(str)<=7){
        		$("p[id='info']").html("姓名不能高于7位!");
        		return false;
      		}
      		return true;
		}
		
		function valBirthday(){
			var str = $("input[name='birthday']").val();
			var pattern = /^[0-9]{4}-[0-9]{2}-[0-9]{2}$/;
			if(str == null || str == ""){
				return true;
			}
			if(!pattern.exec(str)){
        		$("p[id='info']").html("日期格式错误:yyyy-mm-dd!");
        		return false;
      		}
			return true;
		}
		
		function valEmail(){
			var str = $("input[name='email']").val();
			var pattern = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
			if(str == null || str == ""){
				return true;
			}
			if(!pattern.exec(str)){
        		$("p[id='info']").html("无效邮箱地址!");
        		return false;
      		}
			return true;
		}
		
		function valPhone(){
			var str = $("input[name='phone']").val();
			var pattern = /^[0-9]{6,11}$/;
			$("p[id='info']").html("");
			if(str == null || str == ""){
				return true;
			}
			if(!pattern.exec(str)){
        		$("p[id='info']").html("无效号码!");
        		return false;
      		}
			return true;
		}
		
		function submiting(){
			var boolVal = valUserame()&&valPassword()&&valName()&&valBirthday()&&valEmail()&&valPhone();
			return boolVal
		}
		
	$(function () {
		//绑定默认值
		$("input[name='sex']").ready(function(){
			switch(${stu.sex}){
				case 1:
					$("input[name='sex', value='1']").css("checked", "checked");
				case 2:
					$("input[name='sex', value='2']").css("checked", "checked");
				default:
				$("input[name='sex', value='0']").css("checked", "checked");
			}
		
		});
	
		//用户名检查
		$("input[name='username']").focusout(function(){
			valUserame();
		});
		//密码检查
		$("input[name='password']").focusout(function(){
			valPassword();
		});
		//姓名检查
		$("input[name='name']").focusout(function(){
			valName();
		});
		//生日检查
		$("input[name='birthday']").focusout(function(){
			valBirthday();
		});
		//邮箱检查
		$("input[name='email']").focusout(function(){
			valEmail();
		});
		//号码检查
		$("input[name='phone']").focusout(function(){
			valPhone();
		});
		
	})
	</script>
</body>
</html>
