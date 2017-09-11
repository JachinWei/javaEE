<%@ page language="java" import="java.util.*,bean.*"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<title>账户管理-学生管理系统</title>
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
	<jsp:include page="/login/index.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<div class="input-form-left">
				<button class="btn btn-default" style="float:left" id="add">添加</button>
				<button class="btn btn-default" style="float:left" id="search">查询</button>
				<div class="input-group" style="float:left">
					<span class="input-group-addon">选择班级查询</span> 
					<select id="classSearch" class="form-control">
						<option value="">空</option>
						<c:forEach items="${classList}" var="obj">
							<option value="${obj.id}"
							<c:if  test="${obj.id  eq  classSearch}">
    									selected="selected"
    						</c:if>>${obj.title}
    						</option>
						</c:forEach>
					</select>
				</div>
				<input class="form-control login-text-size" style="float:left" value="${stuSearch}"
					id="stuSearch" placeholder="按学号查询" />
				<form action="<%=path%>/stu?method=upload" method="post" enctype="multipart/form-data" style="float:right">
					<div class="form-input">
						<input type="submit" value="批量上传" class="btn btn-default" style="float:left" id="addAll">
						<input type="file" name="myfile" class="btn">
					</div>
				</form>
			</div>
		</div>
		<c:choose>
			<c:when test="${status eq 'get'}">
				<div class="container">
					<div class="row">
						<form action="<%=path%>/stu?method=add&status=save" method="post"
							role="form">
							<div class="input-group text-size">
								<span class="input-group-addon">姓名</span> <input name="name"
									type="text" class="form-control" value="${stu.name}" />
							</div>
							<div class="input-group text-size">
								<span class="input-group-addon">学号</span> <input
									name="stuNumber" type="text" class="form-control"
									value="${stu.stuNumber}" />
							</div>
							<div class="input-group text-size">
								<span class="input-group-addon">班级</span> <select name="classId"
									class="form-control">
									<c:forEach items="${classList}" var="obj">
										<option value="${obj.id}"
											<c:if  test="${obj.id  eq  stu.classId}">
    									selected="selected"
    								</c:if>>${obj.title}</option>
									</c:forEach>
								</select>
							</div>
							<div class="input-group text-size">
								<span class="input-group-addon">邮箱</span> <input name="email"
									type="text" value="${stu.email}" class="form-control" />
							</div>
							<div class="input-group text-size">
								<span class="input-group-addon">手机号</span> <input name="phone"
									type="text" value="${stu.phone}" class="form-control" />
							</div>
							<input name="id" type="hidden" value="${id}" class="form-control" />
							<button type="submit" class="btn btn-default">保存</button>
							<br />
							<p class="text-danger" id="info">${resultStr eq null?"":resultStr}
							</p>
						</form>
					</div>
				</div>
			</c:when>
			<c:when test="${status eq 'search'}">
				<div class="container">
					<div class="row">
						<div class="table-responsive">
							<table class="table table-striped table-bordered">
								<thead>
									<tr>
										<th>姓名</th>
										<th>学号</th>
										<th>班级</th>
										<th>邮箱</th>
										<th>手机号</th>
										<th>功能</th>
									</tr>
								</thead>
								<tbody>
									<c:choose>
										<c:when test="${not empty stu}">
											<tr>
												<td>${stu.name}</td>
												<td>${stu.stuNumber}</td>
												<td>${className}</td>
												<td>${stu.email}</td>
												<td>${stu.phone}</td>
												<td>
													<div class="input-form">
														<button name="delete" class="btn btn-default"
															value="${stu.id}">删除</button>
														<button name="update" class="btn btn-default"
															value="${stu.id}">修改</button>
													</div>
												</td>
											</tr>
										</c:when>
										<c:otherwise>
											<c:forEach items="${stuList}" var="obj">
												<tr>
													<td>${obj.name}</td>
													<td>${obj.stuNumber}</td>
													<td>${className}</td>
													<td>${obj.email}</td>
													<td>${obj.phone}</td>
													<td>
														<div class="input-form">
															<button name="delete" class="btn btn-default"
																value="${obj.id}">删除</button>
															<button name="update" class="btn btn-default"
																value="${obj.id}">修改</button>
														</div>
													</td>
												</tr>
											</c:forEach>
											<div class="input-form-left">
												<!--分页导航开始-->
												<div class="btn btn-default" onclick="lastPageAction()">上一页</div>
												第${pageInfo.indexPage}页/共${pageInfo.totalPages}页
												<div class="btn btn-default" onclick="nextPageAction()">下一页</div>
												<!--分页导航结束-->
											</div>
											<p class="text-danger">${resultStr eq null?"":resultStr}</p>
										</c:otherwise>

									</c:choose>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>
	</div>
	<script>
		function valName() {
			var str = $("input[name='username']").val();
			$("p[id='info']").html("");
			if (str == null || str == "") {
				return true;
			}
			if (length(str) <= 7) {
				$("p[id='info']").html("姓名不能高于7位!");
				return false;
			}
			return true;
		}
	
		function valEmail() {
			var str = $("input[name='email']").val();
			var pattern = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
			if (str == null || str == "") {
				return true;
			}
			if (!pattern.exec(str)) {
				$("p[id='info']").html("无效邮箱地址!");
				return false;
			}
			return true;
		}
	
		function valPhone() {
			var str = $("input[name='phone']").val();
			var pattern = /^[0-9]{6,11}$/;
			$("p[id='info']").html("");
			if (str == null || str == "") {
				return true;
			}
			if (!pattern.exec(str)) {
				$("p[id='info']").html("无效号码!");
				return false;
			}
			return true;
		}
	
		function lastPageAction() {
			var page = "<%=path%>/stu?method=search&classSearch=${classSearch}&stuSearch=${stuSearch}&indexPage=${pageInfo.indexPage - 1}";
			$(location).attr('href', page);
		}
	
		function nextPageAction() {
			var page = "<%=path%>/stu?method=search&classSearch=${classSearch}&stuSearch=${stuSearch}&indexPage=${pageInfo.indexPage + 1}";
			$(location).attr('href', page);
		}
	
		$(function() {
			$("#add").click(function() {
				var page = "<%=path%>/stu?method=add&status=get";
				$(location).attr('href', page);
			});
	
			$("#search").click(function() {
				var page = "<%=path%>/stu?method=search&classSearch=";
				page += $("#classSearch").val() + "&stuSearch=";
				page += $("#stuSearch").val();
				$(location).attr('href', page);
			});
	
			$("button[name='delete']").click(function() {
				var page = "<%=path%>/stu?method=delete&indexPage=${pageInfo.indexPage}&classSearch=${classSearch}&stuSearch=${stuSearch}&id=";
				page += $(this).val();
				$(location).attr('href', page);
			});
	
			$("button[name='update']").click(function() {
				var page = "<%=path%>/stu?method=update&id=";
				page += $(this).val();
				$(location).attr('href', page);
			});
	
		});
	</script>
</body>
</html>
