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
				<button class="btn btn-default" style="float:left" id="search">查询</button>
				<div class="input-group" style="float:left">
					<span class="input-group-addon">选择学期</span> 
					<select id="semester" class="form-control">
						<option value="1" <c:if test="${semester eq 1}">selected="selected"</c:if>>大一上学期</option>
						<option value="2" <c:if test="${semester eq 2}">selected="selected"</c:if>>大一下学期</option>
						<option value="3" <c:if test="${semester eq 3}">selected="selected"</c:if>>大二上学期</option>
						<option value="4" <c:if test="${semester eq 4}">selected="selected"</c:if>>大二下学期</option>
						<option value="5" <c:if test="${semester eq 5}">selected="selected"</c:if>>大三上学期</option>
						<option value="6" <c:if test="${semester eq 6}">selected="selected"</c:if>>大三下学期</option>
						<option value="7" <c:if test="${semester eq 7}">selected="selected"</c:if>>大四上学期</option>
						<option value="8" <c:if test="${semester eq 8}">selected="selected"</c:if>>大四下学期</option>
					</select>
				</div>
			</div>
		</div>
		<c:choose>
			<c:when test="${not empty seqList}">
				<div class="container">
					<div class="row">
						<div class="table-responsive">
							<table class="table table-striped table-bordered">
								<thead>
									<tr>
										<th>课程名</th>
										<th>学分</th>
										<th>成绩</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${seqList}" var="obj">
										<tr>
											<td>${obj.title}</td>
											<td>${obj.credit}</td>
											<td>${obj.score}</td>
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
		</c:choose>
	</div>
	<script>
		function lastPageAction() {
			var page = "<%=path%>/scoreQuery?semester=${semester}&indexPage=${pageInfo.indexPage - 1}";
			$(location).attr('href', page);
		}
	
		function nextPageAction() {
			var page = "<%=path%>/scoreQuery?semester=${semester}&indexPage=${pageInfo.indexPage + 1}";
			$(location).attr('href', page);
		}
	
		$(function() {
			$("#search").click(function() {
				var page = "<%=path%>/scoreQuery?semester=";
				page += $("#semester").val();
				$(location).attr('href', page);
			});
		});
	</script>
</body>
</html>
