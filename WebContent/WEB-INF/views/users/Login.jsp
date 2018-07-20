<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.passwordsaver.controllers.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://bootswatch.com/4/lux/bootstrap.css">
<title>Log in</title>
</head>
<body>
<%
	if(request.getAttribute(AttributeNames.ERROR.toString())!=null){	
%>
	<div class="alert alert-dismissible alert-danger">
	  <button type="button" class="close" data-dismiss="alert">&times;</button>
	  <strong>Wrong login or password</strong>
	</div>
<%
	}
%>

		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		  <a class="navbar-brand" href="#">Password saver</a>
		</nav>
		
		<div class="container">
				<form action="<%=request.getContextPath()%><%=ControllerUrls.LOGIN_SERVLET%>" method="POST">
				  <fieldset>
				    <legend>Enter your data:</legend>
				    <div class="form-group">
					  <label class="col-form-label" for="Login">Login</label>
					  <input type="text" class="form-control" placeholder="Login" id="Login" name="<%=ParameterNames.USER_LOGIN%>">
					</div>
				    <div class="form-group">
				      <label for="exampleInputPassword1">Password</label>
				      <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" name="<%=ParameterNames.USER_PASSWORD %>">
				    </div>
				    <button type="submit" class="btn btn-primary">Log In</button>
				  </fieldset>
				</form>
				<a class="nav-link" href="<%=request.getContextPath()%><%=ControllerUrls.REGISTRATION_SERVLET%>">Create account</a>
		</div>	
</body>
</html>