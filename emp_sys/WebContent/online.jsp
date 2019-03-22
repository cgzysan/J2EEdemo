<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>在线用户列表</title>
</head>
<body>
	<!-- 引入头部页面 -->
	<jsp:include page="/public/head.jsp"></jsp:include>

	<!-- 在线用户 -->
	<table align="center" border="1" width="80%" cellpadding="3"
		cellspacing="0">
		<tr>
			<td colspan="2" align="center"><h3>在线用户</h3></td>
		</tr>
		<tr>
			<th>编号</th>
			<th>员工名称</th>
		</tr>
		<c:if test="${not empty applicationScope.onlineList }">
			<c:forEach var="admin" items="${applicationScope.onlineList }">
				<tr>
					<td>${admin.id }</td>
					<td>${admin.userName }</td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
</body>
</body>
</html>