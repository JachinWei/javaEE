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
	<div id="tip" style="position:fixed; boder:1px; right:50px; top:150px"></div>
	<jsp:include page="/login/index.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<div class="input-form-left">
				<button class="btn btn-default" style="float:left" id="search">查询</button>
				<div class="input-group" style="float:left">
					<span class="input-group-addon">选择课程查询</span> <select id="courseId"
						class="form-control">
						<option value="">空</option>
						<c:forEach items="${courseList}" var="obj">
							<option value="${obj.id}"
								<c:if  test="${obj.id  eq  courseId}">
    									selected="selected"
    						</c:if>>${obj.title}
							</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
		<c:choose>
			<c:when test="${status eq 'search'}">
				<div class="container">
					<div class="row">
						<div class="table-responsive">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th>学生姓名</th>
										<th>学号</th>
										<th>成绩</th>
										<th>功能</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${scList}" var="obj">
										<tr>
											<td>${obj.stuName}</td>
											<td>${obj.stuNum}</td>
											<td><input style="border:none" name="score"
												value="${obj.score}"></td>
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
		function lastPageAction() {
			var page = "<%=path%>/score?method=search&courseId=${courseId}&indexPage=${pageInfo.indexPage - 1}";
			$(location).attr('href', page);
		}
	
		function nextPageAction() {
			var page = "<%=path%>/score?method=search&courseId=${courseId}&indexPage=${pageInfo.indexPage + 1}";
			$(location).attr('href', page);
		}
	
		$(function() {
			$("#add").click(function() {
				var page = "<%=path%>/score?method=add&status=get";
				$(location).attr('href', page);
			});
	
			$("#search").click(function() {
				var page = "<%=path%>/score?method=search&courseId=";
				page += $("#courseId").val();
				$(location).attr('href', page);
			});
	
			$("button[name='delete']").click(function() {
				var page = "<%=path%>/score?method=delete&courseId=${courseId}&indexPage=${pageInfo.indexPage}&searchStr=${searchStr}&id=";
				page += $(this).val();
				$(location).attr('href', page);
			});
	
			$("button[name='update']").click(function() {
				var page = "<%=path%>/score?method=update&id=";
				page += $(this).val() + "&score=";
				page += $(this).parents("tr").find("input[name='score']").val()
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
