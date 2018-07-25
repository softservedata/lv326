<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="hostUrl">${pageContext.request.requestURL}</c:set>
<c:set var="hostUri">${pageContext.request.requestURI}</c:set>
<c:set var="hostContext" value="${fn:substring(hostUrl, 0, fn:length(hostUrl) - fn:length(hostUri))}${pageContext.request.contextPath}" />
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
 <h1>Edit Profiles User</h1> 
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
 <br>


  <select id="idNameOfUserSelect" class="comboBox" onchange="selectNameOfUser('${hostContext}/usersprofileseditadmin?nameOfUserSelect=')">
     <option value="Choose User" selected>Choose User</option>
    <c:forEach var="nameOfUser" items="${arrayNumberOfUser}">      
        <option value="${nameOfUser}"
                <c:if test="${nameOfUserSelect eq nameOfUser}">
					selected
				</c:if>>
        ${nameOfUser}</option>
   </c:forEach>
  </select>
</div>
<hr>

<c:if test="${nameOfUserSelect != null && nameOfUserSelect != 'Choose User' }">
<form action="${pageContext.request.contextPath}/usersprofileseditadmin" method="post">
  <div class="container">       
    <label for="name"><b>Name</b></label>
    <input type="text" name="name" value="${nameOfUserSelect}" readonly>
    
    <label for="currentRole"><b>Current Role</b></label>
    <input type="text" name="currentRole" value="${currentRole}" readonly>
    
    <label for="newRole"><b>New Role</b></label>
    <input type="text" name="newRole"  required>

    <button type="submit" class="registerbtn">Submit</button>    
    </div>
</form>
</c:if>
<hr>


<div class="navbar">
  <p>@Created by Roman Mozil</p>
</div>

<script type="text/javascript">		
		function selectNameOfUser(url) {
			var name = document.getElementById("idNameOfUserSelect");
			window.location.href = url
				+ name.options[name.selectedIndex].value;
		}
	</script>
</body>
</html>
