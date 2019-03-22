<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="forLogin" action="${pageContext.request.contextPath }/login" method="post">
		<table align="center" border="1">
			<tr>
				<td>用户名</td>
				<td><input type="text" name="userName"></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><input type="password" name="pwd"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="亲，点我登陆！"></td>
			</tr>
		</table>
	</form>
</body>
</html>