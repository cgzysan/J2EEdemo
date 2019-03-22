<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FTP文件系统</title>
</head>
<body>
	<a href="${pageContext.request.contextPath }/upload.jsp">文件上传</a>	&nbsp;&nbsp;&nbsp;
	<a href="${pageContext.request.contextPath }/fileServlet?method=downList">文件下载</a>
</body>
</html>