<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    buffer="1kb"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OUT</title>
</head>
<body>
	<%
	for(int i= 1; i <= 1023; i++) {
		out.write("a");
	}
	System.out.println();
	// 查看缓存区大小
	System.out.println("当前缓存区大小：" + out.getBufferSize());
	// 查看缓存区剩余大小
	System.out.println("缓存区剩余空间大小：" + out.getRemaining());
	
	response.getWriter().write("123");
	%>
</body>
</html>