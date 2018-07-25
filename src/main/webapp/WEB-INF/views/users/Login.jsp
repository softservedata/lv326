<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/orders.css" type="text/css"/>
</head>
<body>

<div class="topnav">
  <div class="topnav-right">    
    <a href="${pageContext.request.contextPath}/register">Register</a>
  </div>
</div>

<form action="${pageContext.request.contextPath}/login" method="post">
  <div class="container">
    <h1>Log in</h1>   
    <c:if test="${error ne null}">
		<p>
			<font color="red">${error}</font>
		</p>
	</c:if> 
	<c:if test="${accountSuccessfullyCreated ne null}">
		<p>
			<font color="green">${accountSuccessfullyCreated}</font>
		</p>
	</c:if> 
  </div>
  <hr>
  <div class="container" style="background-color:white">
    <input type="email" placeholder="Login" name="login" required>
    <input type="password" placeholder="Password" name="password" required>
  </div>  
  <hr>
   <div id="forget_password">
    <p >Forget your password? Click <a href="${pageContext.request.contextPath}/recoverpassword">Recover password</a>.</p>
    </div>

  <div class="container">
    <input type="submit" name="submit" value="Subscribe" >
  </div>
</form>
 <hr>

<div class="navbar">
  <p>@Created by Roman Mozil</p>
</div>


</body>
</html>
