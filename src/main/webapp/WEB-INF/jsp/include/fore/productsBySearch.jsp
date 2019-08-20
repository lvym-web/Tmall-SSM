<!-- 模仿天猫整站ssm 教程 为how2j.cn 版权所有-->
<!-- 本教程仅用于学习使用，切勿用于非法用途，由此引起一切后果与本站无关-->
<!-- 供购买者学习，请勿私自传播，否则自行承担相关法律责任-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="searchProducts">

	<c:forEach items="${products}" var="p">
	
	<div class="productUnit" price="${p.promoteprice}">
		<a href="${pageContext.request.contextPath}/foreproduct?id=${p.id}">
		<c:forEach items="${p.productimage}" var="pp" end="0">
			<img class="productImage" src="img/productSingle/${pp.pid }.jpg">
			</c:forEach>
		</a>
		<span class="productPrice">¥<fmt:formatNumber type="number" value="${p.promoteprice}" minFractionDigits="2"/></span>
		<a class="productLink" href="foreproduct?id=${p.id}">
				${fn:substring(p.name, 0, 50)}
		</a>

		<a class="tmallLink" href="foreproduct?id=${p.id}">天猫专卖</a>
		<div class="show1 productInfo">
			<span class="monthDeal ">月成交 <span class="productDealNumber">笔</span></span>
			<span class="productReiew">评价<span class="productReviewNumber"></span></span>
			<span class="wangwang"><img src="img/site/wangwang.png"></span>
		</div>

	</div>
	</c:forEach>
	<c:if test="${empty products}">
	<div class="noMatch">没有满足条件的产品</div>
		</c:if>

		<div style="clear:both"></div>
	</div>