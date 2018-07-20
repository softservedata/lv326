<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Login</h1>
	<c:if test="${error ne null}">
		<p>
			<font color="red">${error}</font>
		</p>
	</c:if>
	<br><br>
	<form action="${pageContext.request.contextPath}/login" method="post">
		Login: <input type="text" name="login">
		<br><br>
		Password: <input type="password" name="password">
		<br><br>
		<input type="submit" name="submit" value="Signin"> <br>
	</form>

</body>
</html>
