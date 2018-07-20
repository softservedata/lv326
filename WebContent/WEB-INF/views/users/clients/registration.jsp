<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.passwordsaver.controllers.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://bootswatch.com/4/lux/bootstrap.css">
<title>Registration page</title>
</head>
<body>
	<%
		String error = (String)request.getAttribute(AttributeNames.ERROR.toString());
	%>
	
	
		<% if(error!=null && error.equals("notAvailableLogin")){ %>
			<div class="alert alert-dismissible alert-danger">
			  <button type="button" class="close" data-dismiss="alert">&times;</button>
			  <strong>This login already exists</strong>
			</div>
		<%}%>
		
		<%if(error!=null && error.equals("wrongLoginFormat")){%>
			<div class="alert alert-dismissible alert-danger">
			  <button type="button" class="close" data-dismiss="alert">&times;</button>
			  <strong>Incorrect login.(correct length: 5-30 symbols, without spaces)</strong>
			</div>
		<%}%>
		<%if(error!=null && error.equals("wrongPasswordFormat")){%>
			<div class="alert alert-dismissible alert-danger">
	  			<button type="button" class="close" data-dismiss="alert">&times;</button>
			  	<strong>Incorrect password.(correct length: 5-30 symbols, without spaces)</strong>
			</div>
		<%}%>
		<%if(error!=null && error.equals("wrongConfirmation")){%>
			<div class="alert alert-dismissible alert-danger">
	  			<button type="button" class="close" data-dismiss="alert">&times;</button>
			  	<strong>Wrong password confirmation</strong>
			</div>
		<%}%>
		
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		  <a class="navbar-brand" href="#">Registration</a>
		</nav>
			
		<div class="container">
				<form action="<%=request.getContextPath()%><%=ControllerUrls.REGISTRATION_SERVLET%>" method="POST">
				  <fieldset>
				    <legend>Enter your data</legend>
				    <div class="form-group">
					  <label class="col-form-label" for="Login">Login</label>
					  <input type="text" class="form-control" placeholder="Login" id="Login" name="<%=ParameterNames.NEW_LOGIN%>" pattern="^([a-zA-Z0-9]{5,30})$">
					</div>
				    <div class="form-group">
				      <label for="exampleInputPassword1">Password</label>
				      <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" name="<%=ParameterNames.NEW_PASSWORD%>" pattern="^([a-zA-Z0-9]{5,30})$">
				    </div>
				    <div class="form-group">
				      <label for="exampleInputPassword1">Confirm your password:</label>
				      <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" name="<%=ParameterNames.PASSWORD_CONFIRMATION%>" pattern="^([a-zA-Z0-9]{5,30})$">
				    </div>
				    <button type="submit" class="btn btn-primary">Create</button>
				  </fieldset>
				</form><br>
		</div>
</body>
</html>