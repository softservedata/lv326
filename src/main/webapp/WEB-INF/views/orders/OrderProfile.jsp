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
    <h1>Order</h1> 
    <h3>Add Order</h3> 
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

<form action="${pageContext.request.contextPath}/orderedit" method="post">
  <div class="container">      
    <hr>  
    <label for="idOrder"><b>idOrder:</b></label>
    <input type="text" name="idOrder" value="${orderDto.idOrder}"  readonly>
    
    <label for="shop"><b>Shop:</b></label>
    <input type="text" name="shop" value="${orderDto.shop}" required>
    
    <label for="address"><b>Address:</b></label>
    <input type="text" name="address"  value="${orderDto.address}"required >
    
    <label for="production"><b>Production:</b></label>
    <input type="text" name="production" value="${orderDto.production}" required>
    
    <label for="scope"><b>Scope:</b></label>
    <input type="number" name="scope"  value="${orderDto.scope}"  required>
    
    <label for="status"><b>Status:</b></label>
    <input type="text" name="status" value="${orderDto.status}"  required>

    <button type="submit" class="registerbtn">Add Order</button>     
    </div>
</form>
<hr>

<div class="navbar">
  <p>@ Created by Roman Mozil</p>
</div>

</body>
</html>
