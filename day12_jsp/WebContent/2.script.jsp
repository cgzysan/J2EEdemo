<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String name = "aa";
		int a = 10;
		int b = 20;
	%>
	<%=name%>
	<br />
	<%="a + b = " + (a + b)%>
	<hr />
	<%
		Random random = new Random();
		float f = random.nextFloat();
	%>
	随机小数：<%=f%>
	<hr />
	<%
		for (int i = 1; i <= 6; i++) {
	%>
	<h <%=i%>>标题<%=i%></h<%=i%>>
	<br />
	<%
		}
	%>
	<hr />
	<%
		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j <= i; j++) {
	%>
	<%=i%>
	x
	<%=j%>
	=
	<%=(i * j)%>&nbsp;
	<%
		}
	%>
	<br />
	<%
		}
	%>
</body>
</html>