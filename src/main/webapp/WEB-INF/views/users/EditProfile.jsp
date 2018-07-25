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
  <a class="active" href="${pageContext.request.contextPath}/orders">Orders</a>
  <c:if test="${idRoleSession eq 1}">
  <a href="${pageContext.request.contextPath}/usersordersadmin">Users Orders</a>
  </c:if>
  <a href="${pageContext.request.contextPath}/profileedit">Edit Profile</a>
  <c:if test="${idRoleSession eq 1}">
  <a href="${pageContext.request.contextPath}/usersprofileseditadmin">Edit Profiles Users</a>
  </c:if>
  <div class="topnav-right">    
    <a href="${pageContext.request.contextPath}/logout">Log out</a>
  </div>
</div>



<div class="container">
 <h1>Edit Profile</h1>  
  <c:if test="${error ne null}">
		<p>
			<font color="red">${error}</font>
		</p>
  </c:if> 
  <c:if test="${changesSavedSuccessfully ne null}">
		<p>
			<font color="green">${changesSavedSuccessfully}</font>
		</p>
  </c:if>  	  
</div>

<div class="container">

<fieldset>
  <legend>Edit Login</legend>
  <form action="${pageContext.request.contextPath}/profileedit" method="post">
  
    <label for="login"><b>Login</b></label>
    <input type="email" name="login" value="${login}" readonly >

    <label for="new_login"><b>New Login</b></label>
    <input type="email" name="new_login" value="${new_login}" required>
    <hr>

    <button type="submit" class="registerbtn">Submit</button>    
</form>
</fieldset>
<br><br>

<fieldset>
  <legend>Edit Password</legend>
<form action="${pageContext.request.contextPath}/profileedit" method="post">
  
    <label for="previous_password"><b>Previous password</b></label>
    <input type="password" name="previous_password" value="${previous_password}" required>
    
    <label for="new_password"><b>New password</b></label>
    <input type="password" name="new_password" value="${new_password}" required>

    <label for="repeat_new_password"><b>Repeat new password</b></label>
    <input type="password" name="repeat_new_password" value="${repeat_new_password}" required>    
    
    <label for="role"><b>Role</b></label>
    <input type="text" name="role" value="${idRoleSession}"readonly >
    <hr>

    <button type="submit" class="registerbtn">Submit</button>    
</form>
</fieldset>
</div>
<br><br>
<hr>

<div class="navbar">
  <p>@Created by Roman Mozil</p>
</div>

</body>
</html>
