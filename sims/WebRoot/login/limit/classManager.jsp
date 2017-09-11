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
<link href="<%=path%>/lib/css/bootstrap-multiselect.css"
	rel="stylesheet">
<script src="<%=path%>/lib/js/bootstrap-multiselect.js"></script>
</head>
<body>
	<div id="tip" style="position:fixed; boder:1px; right:50px; top:150px"></div>
	<jsp:include page="/login/index.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<div class="input-form-left">
				<button class="btn btn-default" style="float:left" id="add">添加</button>
				<button class="btn btn-default" style="float:left" id="search">查询</button>
				<input class="form-control login-text-size" style="float:left"
					id="searchStr" placeholder="按班级名查询" />
				<form action="<%=path%>/myclass?method=upload" method="post" enctype="multipart/form-data" style="float:right">
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
						<form action="<%=path%>/myclass?method=add&status=save"
							method="post" role="form">
							<div class="input-group text-size">
								<span class="input-group-addon">班级名</span> <input name="title"
									type="text" class="form-control" value="${myClass.title}" />
							</div>
							<div class="input-group text-size">
								<span class="input-group-addon">年级</span> <input name="grade"
									type="text" class="form-control" value="${myClass.grade}" />
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
							<table class="table table-bordered">
								<thead>
									<tr>
										<th>班名</th>
										<th>年级</th>
										<th>功能</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${classList}" var="obj">
										<tr>
											<td><input style="border:none" name="title"
												value="${obj.title}"></td>
											<td><input style="border:none" name="grade"
												value="${obj.grade}"></td>
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
								</tbody>
							</table>
							<div class="input-form-left">
								<!--分页导航开始-->
								<div class="btn btn-default" onclick="lastPageAction()">上一页</div>
								第${pageInfo.indexPage}页/共${pageInfo.totalPages}页
								<div class="btn btn-default" onclick="nextPageAction()">下一页</div>
								<!--分页导航结束-->
							</div>
							<p class="text-danger">${resultStr eq null?"":resultStr}</p>
						</div>
					</div>
				</div>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>
	</div>


	<script>
		function valTitle() {
			var str = $("input[name='username']").val();
			$("p[id='info']").html("");
			if (str == null || str == "") {
				$("p[id='info']").html("班名不能为空!");
				return false;
			}
			return true;
		}
	
		function valGrade() {
			var str = $("input[name='email']").val();
			var pattern = /^[0-9]{4}$/;
			if (str == null || str == "") {
				$("p[id='info']").html("年级不能为空!");
				return false;
			}
			if (!pattern.exec(str)) {
				$("p[id='info']").html("无效年级!");
				return false;
			}
			return true;
		}
	
		function lastPageAction() {
			var page = "<%=path%>/myclass?method=search&searchStr=${searchStr}&indexPage=${pageInfo.indexPage - 1}";
			$(location).attr('href', page);
		}
	
		function nextPageAction() {
			var page = "<%=path%>/myclass?method=search&searchStr=${searchStr}&indexPage=${pageInfo.indexPage + 1}";
			$(location).attr('href', page);
		}
	
		$(function() {
			$("#add").click(function() {
				var page = "<%=path%>/myclass?method=add&status=get";
				$(location).attr('href', page);
			});
	
			$("#search").click(function() {
				var page = "<%=path%>/myclass?method=search&searchStr=";
				page += $("#searchStr").val();
				$(location).attr('href', page);
			});
	
			$("button[name='delete']").click(function() {
				var page = "<%=path%>/myclass?method=delete&indexPage=${pageInfo.indexPage}&searchStr=${searchStr}&id=";
				page += $(this).val();
				$(location).attr('href', page);
			});
	
			$("button[name='update']").click(function() {
				var page = "<%=path%>/myclass?method=update&id=";
				page += $(this).val() + "&title=";
				page += $(this).parents("tr").find("input[name='title']").val() + "&grade="
				page += $(this).parents("tr").find("input[name='grade']").val();
				$.ajax({
					url : page,
					async : true,
					success : function(result) {
						$("#tip").html("修改成功").show(300).delay(3000).hide(300);
						; // 这个是渐渐消失 
					}
				});
			});
	
		});
	</script>
</body>
</html>
