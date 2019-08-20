

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
	
	
<div class="row">
 <div class="clo-md-6">
 当前${pageInfo.pageNum }页,总${pageInfo.pages }页，总${pageInfo.total }条记录
 </div>
 <div >
 <nav >
  <ul class="pagination">
   <li><a href="${pageContext.request.contextPath}/admin_order_list?pn=1">首页</a></li>
   <c:if test="${pageInfo.hasPreviousPage}">
    <li>
      <a href="${pageContext.request.contextPath}/admin_order_list?pn=${pageInfo.pageNum-1}" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    </c:if>
    <c:forEach items="${pageInfo.navigatepageNums }" var="pagenum">
    <c:if test="${pagenum==pageInfo.pageNum }">
     <li class="active"><a href="#">${pagenum }</a></li>
    </c:if>
     <c:if test="${pagenum!=pageInfo.pageNum }">
     <li><a href="${pageContext.request.contextPath}/admin_order_list?pn=${pagenum }">${pagenum }</a></li>
    </c:if>
    </c:forEach>
    <c:if test="${pageInfo.hasNextPage}">
    <li>
      <a href="${pageContext.request.contextPath}/admin_order_list?pn=${pageInfo.pageNum+1}" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
    </c:if>
     <li><a href="${pageContext.request.contextPath}/admin_order_list?pn=${pageInfo.pages }">尾页</a></li>
  </ul>
</nav>
 </div>
 </div>