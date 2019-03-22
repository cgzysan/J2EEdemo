<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<h3 align="left">
 	欢迎你，${sessionScope.loginInfo.userName }; 
 	<a href="${pageContext.request.contextPath }/login?method=out">退出</a>
 		
 	<a href="${pageContext.request.contextPath }/online.jsp">在线列表展示</a>
</h3>