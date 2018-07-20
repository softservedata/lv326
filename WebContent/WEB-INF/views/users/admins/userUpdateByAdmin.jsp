<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.passwordsaver.dto.*" %>
<%@ page import="com.passwordsaver.controllers.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://bootswatch.com/4/united/bootstrap.css">
<title>User Update</title>
</head>
<body>
	<%
		UserDto updatedUserDto = (UserDto)request.getAttribute(AttributeNames.UPDATED_USER_DTO.toString());
	%>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		  <a class="navbar-brand" href="#">User Updating</a>
		</nav>
		<div class="container">
			<form action="<%=request.getContextPath()%><%=ControllerUrls.UPDATE_USER_BY_ADMIN_SERVLET%>" method="POST">
				  <fieldset>
				    <legend>User data</legend>
				    <input type="hidden" name="<%=ParameterNames.OLD_LOGIN%>" value="<%=updatedUserDto.getLogin()%>">
				    <div class="form-group">
					  <label class="col-form-label" for="Login">Login</label>
					  <input type="text" class="form-control" placeholder="Login" id="Login" name="<%=ParameterNames.NEW_LOGIN%>" pattern="^([a-zA-Z0-9]{5,30})$" value="<%=updatedUserDto.getLogin()%>">
					</div>
				    <div class="form-group">
				      <label for="exampleInputPassword1">Password</label>
				      <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" name="<%=ParameterNames.NEW_PASSWORD%>" pattern="^([a-zA-Z0-9]{5,30})$" value="<%=updatedUserDto.getPassword()%>">
				    </div>
				    <div class="form-group">
				      <label for="exampleSelect1">Role</label>
				      <select class="form-control" id="exampleSelect1" name="<%=ParameterNames.NEW_ROLE%>">
				        <option value="<%=RoleNames.USER %>">User</option>
						<option value="<%=RoleNames.ADMINISTRATOR%>">Administrator</option>
				      </select>
				    </div>
				    <button type="submit" class="btn btn-primary">update</button>
				  </fieldset>
				</form><br>
				<form action="<%=request.getContextPath()%><%=ControllerUrls.PERSONAL_CABINET_SERVLET%>" method="get">
					<input type="submit" class="btn btn-primary" value="cancle">
				</form>
			</div>	
</body>
</html>