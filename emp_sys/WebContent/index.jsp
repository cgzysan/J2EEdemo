<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 引入jstl核心标签库 -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 引入头部文件 -->
	<jsp:include page="/public/head.jsp"></jsp:include>
	<!-- 列表展示数据 -->
	<table align="center" border="1" width="80%" cellpadding="3" cellspacing="0">
		<tr>
			<th>序号</th>
			<th>编号</th>
			<th>员工名称</th>
			<th>部门名称</th>
		</tr>
		<c:choose>
			<c:when test="${not empty requestScope.pageBean.pageData }">
				<c:forEach var="emp" items="${requestScope.pageBean.pageData }" varStatus="vs">
				<tr>
				<td>${vs.count }</td>
				<td>${emp.empId }</td>
				<td>${emp.empName }</td>
				<td>${emp.deptName }</td>
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="4">对不起，没有你要找的数据</td>
				</tr>
			</c:otherwise>
		</c:choose>
		<tr>
			<td colspan="4" align="center">
				当前${requestScope.pageBean.currentPage }/${requestScope.pageBean.totalPage }页	&nbsp;&nbsp;
				<a href="${pageContext.request.contextPath }/index?currentPage=1">首页</a>	
				<a href="${pageContext.request.contextPath }/index?currentPage=${requestScope.pageBean.currentPage-1}">上一页</a>
				<a href="${pageContext.request.contextPath }/index?currentPage=${requestScope.pageBean.currentPage+1}">下一页</a>
				<a href="${pageContext.request.contextPath }/index?currentPage=${requestScope.pageBean.totalPage}">末页</a>
			</td>
		</tr>
	</table>
</body>
</html>