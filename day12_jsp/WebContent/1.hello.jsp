<%@page import="java.text.*, java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>第一个jsp页面</title>
</head>
<body>
	<%
	// 写java代码
	// 获取当前时间
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String curDate = format.format(new Date());
	out.write("当前时间：" + curDate);
	%>
</body>
</html>