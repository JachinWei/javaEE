<%@page contentType="text/html;charset=utf-8" isELIgnored="false"%>
<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="entity.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>当当图书 – 全球最大的中文网上书店</title>
		<script type="text/javascript" src="../js/prototype-1.6.0.3.js">
		</script>
		<link href="<%=request.getContextPath()%>/css/book.css" rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/css/second.css" rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/css/secBook_Show.css" rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/css/list.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		&nbsp;

		<!-- 头部开始 -->
		<%@include file="/common/head.jsp"%>
		<!-- 头部结束 -->

		<div style="width: 962px; margin: auto;">
			<a href="#"><img src="<%=request.getContextPath()%>/images/default/book_banner_081203.jpg" border="0" /> </a>
		</div>
		<div class='your_position'>
			您现在的位置:&nbsp;
			<a href='<%=request.getContextPath()%>/index.jsp'>当当图书</a> &gt;&gt;
			<font style='color: #cc3300'><strong>小说</strong> </font>
		</div>

		<div class="book">

			<!--左栏开始-->
			<div id="left" class="book_left">
				<div id="__fenleiliulan">
					<div class=second_l_border2>
						<h2>
							分类浏览
						</h2>
						<ul>
							<li>
								<div>
									<div class=second_fenlei>
										&middot;全部&nbsp;(${totals eq null ? 0:totals})
									</div>
								</div>
							</li>
							<div class="clear"></div>
							
							<!--2级分类开始-->
							<c:forEach items="${cateArray}" var="obj">
      							<li>
								<div>
								<div class=second_fenlei>&middot;</div>
								<div class=second_fenlei>
								<a href="bookList?category_id=${obj.id}">${obj.name} &nbsp;(${obj.count})</a>
								</div>
								</div>
								</li>
   							<div class="clear"></div>
     						</c:forEach>
							<!--2级分类结束-->
							
							<li>
								<div></div>
							</li>
						</ul>
					</div>
				</div>
			</div>

			<!--左栏结束-->

			<!--中栏开始-->
			<div class="book_center">

				<!--图书列表开始-->
				<div id="divRight" class="list_right">

					<div id="book_list" class="list_r_title">
						<div class="list_r_title_text">
							排序方式
						</div>
						<select onchange='' name='select_order' size='1'
							class='list_r_title_ml'>
							<option value="">
								按上架时间 降序
							</option>
						</select>
						<div id="divTopPageNavi" class="list_r_title_text3">

							<!--分页导航开始-->
							
							<div class='list_r_title_text3a'>
								<a name=link_page_next
									href="bookList?pageIndex=1&category_id=${category_id}">
								<img src='<%=request.getContextPath()%>/images/page_up.gif' /> </a>
							</div>
	
							<div class='list_r_title_text3a'>
								<a name=link_page_next
									href="bookList?pageIndex=${pageIndex - 1}&category_id=${category_id}">
								<img src='<%=request.getContextPath()%>/images/page_up.gif' /> </a>
							</div>
				
							<div class='list_r_title_text3b'>
								第${pageInfo.pageIndex}页/共${pageInfo.totalPages}页
							</div>
							
							<div class='list_r_title_text3a'>
								<a name=link_page_next
									href="bookList?pageIndex=${pageIndex + 1}&category_id=${category_id}">
									<img src='<%=request.getContextPath()%>/images/page_down.gif' /> </a>
							</div>
							<div class='list_r_title_text3a'>
								<a name=link_page_next
									href="bookList?pageIndex=${pageInfo.totalPages}&category_id=${category_id}">
									<img src='<%=request.getContextPath()%>/images/page_down.gif' /> </a>
							</div>
							
							<!--分页导航结束-->
						</div>
					</div>
					
					<!--商品条目开始-->
					<div>
						<c:forEach items="${pageInfo.pageList}" var="obj">
						<div class="list_r_line"></div>
						<div class="clear"></div>
						<div class="list_r_list">
						<span class="list_r_list_book"><a name="link_prd_img" href='#'>
								<img src="<%=request.getContextPath()%>/productImages/${obj.product.product_pic}" /> </a> </span>
								<h2>
									<a name="link_prd_name" href='#'>${obj.product.product_name}</a>
								</h2>
								<h3>
								顾客评分：100
							</h3>
							<h4 class="list_r_list_h4">
								作 者:
								<a href='#' name='作者'>${obj.author}</a>
							</h4>
							<h4>
								出版社：
								<a href='#' name='出版社'>${obj.publishing}</a>
							</h4>
							<h4>
								出版时间：${obj.publish_time}
							</h4>
							<h5>
								${obj.catalogue}
							</h5>
							<div class="clear"></div>
							<h6>
								<span class="del">￥${obj.product.fixed_price}</span>
								<span class="red">￥${obj.product.dang_price}</span>
								节省：￥${obj.product.fixed_price - obj.product.dang_price}
							</h6>
							<span class="list_r_list_button">
							<a href="<%=request.getContextPath()%>/login/saveProduct?product_id=${obj.product.id}" onclick="javascript:window.alert('本商品将放入购物车')"> 
							<img src='<%=request.getContextPath()%>/images/buttom_goumai.gif' /> </a>
							<span id="cartinfo"></span>
							</div>
							<div class="clear"></div>	
						</c:forEach>
						</div>	

				<!--图书列表结束-->

			</div>
			<!--中栏结束-->
			<div class="clear"></div>
		</div>

		<!--页尾开始 -->
		<%@include file="/common/foot.jsp"%>
		<!--页尾结束 -->
	</body>
</html>
