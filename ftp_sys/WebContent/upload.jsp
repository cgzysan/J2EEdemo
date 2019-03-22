<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/fileServlet?method=upload" method="post" enctype="multipart/form-data">
		用户名：<input type="text" name="userName">
		<br/>
		文件：<input type="file" name="file_img">
		<br/>
		<input type="submit" value="提交">
	</form>
</body>
</html>