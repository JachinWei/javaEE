<%@page contentType="text/html;charset=utf-8" isELIgnored="false"%>
<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="entity.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>当当图书 – 全球最大的中文网上书店</title>
		<link href="<%=request.getContextPath()%>/css/book.css" rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/css/second.css" rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/css/secBook_Show.css" rel="stylesheet" type="text/css" />
	
	</head>
	<body>
		&nbsp;
		<!-- 头部开始 -->
		<%@include file="/common/head.jsp"%>
		<!-- 头部结束 -->
		<div style="width: 962px; margin: auto;">
			<a href="#" target="_blank"><img
					src="<%=request.getContextPath()%>/images/default/book_banner_081203.jpg" border="0" /> </a>
		</div>

		<div class="book">

			<!--左栏开始-->
			<div id="left" class="book_left">
			  
			   <div class="book_l_border1" id="__FenLeiLiuLan">
	<div class="book_sort_tushu">
		<h2>分类浏览</h2>

		<!--1级分类开始-->
			<c:forEach items="${cateArray}" var="parent">
				<div class="bg_old" onmouseover="this.className = 'bg_white';"
				onmouseout="this.className = 'bg_old';">
				<h3>
					[<a href='#'>${parent.parent_name}</a>]
				</h3>
       			<ul class="ul_left_list">
				<c:set value="${parent.hashmap}" var="child" />
				<c:forEach items="${child}" var="obj">  
					<li><a href='<%=request.getContextPath()%>/bookList?category_id=${obj.value}'>${obj.key}</a></li>
				</c:forEach>
				</ul>
				<div class="empty_left"></div>
			</div>
			<div class="more2"></div>
			</c:forEach>
			
			
			<div class="bg_old">
				<h3>&nbsp;</h3>
			</div>
	</div>
</div>
			   
			   
			   
			   
			   
			   
			</div>
			<!--左栏结束-->

			<!--中栏开始-->
			<div class="book_center">

				<!--推荐图书开始-->
				<div class=second_c_border1 id="recommend">
				   <%@include file="/main/recommend.jsp" %>
				</div>

				<!--推荐图书结束-->

				<!--热销图书开始-->
				<div class="book_c_border2" id="hot">
				<%@include file="/main/hot.jsp" %>
				</div>
				<!--热销图书结束-->

				<!--最新上架图书开始-->
				<div class="book_c_border2" id="new">
				<%@include file="/main/new.jsp" %>
				</div>

				<!--最新上架图书结束-->

				<div class="clear">
				</div>
			</div>
			<!--中栏结束-->



			<!--右栏开始-->
			<div class="book_right">
				<div class="book_r_border2" id="__XinShuReMai">
					<div class="book_r_b2_1x" id="new_bang">
						<h2 class="t_xsrm">
							新书热卖榜
						</h2>
						<div id="NewProduct_1_o_t" onmouseover="">
							<h3 class="second">
								<span class="dot_r"> </span><a href="#" target="_blank">更多&gt;&gt;</a>
							</h3>
						</div>
					</div>
				</div>
			</div>
			<!--右栏结束-->
			<div class="clear"></div>
		</div>

		<!--页尾开始 -->
		<%@include file="/common/foot.jsp"%>
		<!--页尾结束 -->
	</body>
</html>