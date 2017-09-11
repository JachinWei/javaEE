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
<link href="<%=path%>/lib/css/my.css" rel="stylesheet">
<link href="<%=path%>/lib/css/bootstrap-multiselect.css"
	rel="stylesheet">
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="<%=path%>/lib/js/jquery-2.1.3.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="<%=path%>/lib/js/bootstrap-3.3.2.min.js"></script>
<script src="<%=path%>/lib/js/prettify.js"></script>
<script src="<%=path%>/lib/js/bootstrap-multiselect.js"></script>

</head>
<body>
	
							<table class="table table-bordered">
								<thead>
									<tr>
										<th>课程名</th>
										<th>学分</th>
										<th>添加学生</th>
										<th>功能</th>
									</tr>
								</thead>
								<tbody>
										<tr>
											<td><input style="border:none" name="title"
												value="${obj.title}"></td>
											<td><input style="border:none" name="credit"
												value="${obj.credit}"></td>
											<td>
												<div class="input-group" style="float:left">
													<select id="courseId" class="multiselect" multiple="multiple">
															<option value="${obj.id}">asdas</option>
															<option value="${obj.id}">ads}</option>
															<option value="${obj.id}">asd</option>
													</select>
													<button name="confirm" class="btn btn-default"
														value="${obj.id}">修改</button>
												</div>
											</td>
											<td>
												<div class="input-form">
													<button name="delete" class="btn btn-default"
														value="${obj.id}">删除</button>
													<button name="update" class="btn btn-default"
														value="${obj.id}">修改</button>
												</div>
											</td>
										</tr>
								</tbody>
							</table>
							
	</div>


	<script>
		
		$(function() {
			
	
			$('.multiselect').multiselect({
		        includeSelectAllOption: false,
		        selectAllValue:'select-all-value',
		        nonSelectedText:'请选择',
		        nSelectedText:'个城市',
		        allSelectedText:'全部',
		        selectAllText:"全部城市",
		        maxHeight: 300
    		});
		});
	</script>



</body>
</html>
