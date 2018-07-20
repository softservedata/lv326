<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.passwordsaver.dto.*" %>
<%@ page import="com.passwordsaver.controllers.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://bootswatch.com/4/lux/bootstrap.css">
<title>edit key</title>
</head>
<body>
	<%
		KeyDto keyDto = (KeyDto)request.getAttribute("keyDto");
	%>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		 <a class="navbar-brand" href="#">Edit key</a>
	</nav>
			
	<div class="container">
		<form action="<%=request.getContextPath()%><%=ControllerUrls.EDIT_KEY_SERVLET%>" method="POST">
		  <fieldset>
		    <legend>Edit your key</legend>
		    <input type="hidden" name="<%=ParameterNames.ID_KEY %>" value="<%=keyDto.getIdKey()%>">
		    <div class="form-group">
			  <label class="col-form-label" for="Service">Service</label>
			  <input type="text" class="form-control" placeholder="Service" id="Service" name="<%=ParameterNames.NEW_KEY_SERVICE %>" value="<%=keyDto.getService()%>" >
			</div>
		    <div class="form-group">
			  <label class="col-form-label" for="Password">Password</label>
			  <input type="text" class="form-control" placeholder="Password" id="Password" name="<%=ParameterNames.NEW_KEY_PASSWORD%>" value="<%=keyDto.getServPassword()%>" pattern="^([a-zA-Z0-9]{5,30})$">
			</div>
		    <button type="submit" class="btn btn-primary">Add</button>
		  </fieldset>
		</form>
		<br><br>
		<form  method="get"  action="<%=request.getContextPath()%><%=ControllerUrls.PERSONAL_CABINET_SERVLET%>">
			<input type="submit" class="btn btn-primary" value="cancle">
		</form>
	</div>
</body>
</html>