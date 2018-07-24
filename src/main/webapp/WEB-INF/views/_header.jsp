<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file = "/WEB-INF/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<title>+</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="${ rootURL }/">Go to Login Page</a>

		<div class="collapse navbar-collapse" id="navbarColor02">
			<ul class="navbar-nav mr-auto" style="justify-content: space-around;">
				<li class="nav-item active"><a class="nav-link" href="${ rootURL }/userpage">
					Home </a></li>
			
			<c:if test="${ nameAttr != null }">	<li class="nav-item active"><a class="nav-link" href="${ rootURL }/logout">
					Logout</a></li>
			</c:if>
			</ul>
		</div>
	</nav>
</body>
</html>