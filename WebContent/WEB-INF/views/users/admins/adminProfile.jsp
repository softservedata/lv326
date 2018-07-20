<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.passwordsaver.dto.*" %>
<%@ page import="java.util.List" %>
<%@ page import="com.passwordsaver.controllers.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://bootswatch.com/4/united/bootstrap.css">
<title>Admin Profile</title>
</head>
<body>
	<%! final int pagenationCount = 5;%>
		<% 
			AdminUsersDto adminUsersDto =(AdminUsersDto)request.getAttribute(AttributeNames.ADMIN_USERS_DTO.toString());
			int allAdminUsersCount = adminUsersDto.getAllAdminUsersCount();
			int currentPage = (Integer)request.getAttribute(AttributeNames.INDEX_OF_CURRENT_PAGE.toString());
			int pageOffset =adminUsersDto.getPageOffset();
			int pageCount = adminUsersDto.getPageCount();
			List<UserDto> users = adminUsersDto.getUserDtos();
			int from = (Integer)request.getAttribute(AttributeNames.INDEX_OF_FIRST_ITEM_ON_PAGE.toString());
			int rightBorderPage = (Integer)request.getAttribute(AttributeNames.RIGHT_BOUNDARY_PAGE.toString());
			int leftBorderPage = (Integer)request.getAttribute(AttributeNames.LEFT_BOUNDARY_PAGE.toString());
		%>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		  <a class="navbar-brand" href="#"><%=adminUsersDto.getAdminDto().getLogin() %>'s profile</a>
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor02" aria-controls="navbarColor02" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		  </button>

		  <div class="collapse navbar-collapse" id="navbarColor02">
			<ul class="navbar-nav mr-auto">
			  <li class="nav-item active">
				<a class="nav-link" href="<%=request.getContextPath()%><%=ControllerUrls.UPDATE_PROFILE_SERVLET%>">update profile</a>
			  </li>
			  <li class="nav-item active">
				<a class="nav-link" href="<%=request.getContextPath()%><%=ControllerUrls.LOGOUT_SERVLET%>">Log Out</a>
			  </li>
			</ul>
			<form class="form-inline my-2 my-lg-0" action="<%=request.getContextPath()%><%=ControllerUrls.PERSONAL_CABINET_SERVLET%>" method="get">
			  <input class="form-control mr-sm-2" type="text" name="<%=ParameterNames.SUBSTRING%>" value="<%=adminUsersDto.getSubstring()%>" placeholder="Search">
			  <button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
			</form>
		  </div>
		</nav>
	<div class="container">	<br>
		<div class="page-header">
              <h1 id="tables">Users</h1>
        </div>
        
        <table class="table table-hover">
		  <thead>
		    <tr>
		      <th scope="col">Number</th>
		      <th scope="col">Login</th>
		      <th scope="col">Password</th>
		      <th scope="col">Role</th>
		      <th scope="col">edit</th>
		      <th scope="col">delete</th>
		    </tr>
		  </thead>
		  <tbody>
		  	<%for(int i=0;i<users.size();i++){ %>
			<tr class="table-active">
				<th scope="row"><%=i+from%></th>
				<td><%=users.get(i).getLogin()%></td>
				<td><%=users.get(i).getPassword()%></td>
				<td><%=users.get(i).getRole() %></td>
				<td>
					<form method="get" action="<%=request.getContextPath()%><%=ControllerUrls.UPDATE_USER_BY_ADMIN_SERVLET%>">
						<input type="hidden" name="<%=ParameterNames.USER_LOGIN%>" value="<%=users.get(i).getLogin() %>">
						<input type="submit" value="edit">
					</form>
				</td>
				<td>
					<form method="get" action="<%=request.getContextPath()%><%=ControllerUrls.DELETE_USER_SERVLET%>">
						<input type="hidden" name="<%=ParameterNames.USER_LOGIN%>" value="<%=users.get(i).getLogin()%>">
						<input type="submit" value="delete">
					</form>
				</td>
			</tr>
			<%} %>
		
		  </tbody>
		</table> 
		pages:<br> 
		<form action="<%=request.getContextPath()%><%=ControllerUrls.PERSONAL_CABINET_SERVLET%>" method="post">
			<%if(leftBorderPage>1){%>
			<input class="btn btn-primary" type="submit" name="<%=ParameterNames.INDEX_OF_CURRENT_PAGE%>" value="1">
			<%} %>
			<%if(leftBorderPage>2){%>
			...
			<%}%>
			<%for(int i = leftBorderPage; i<=rightBorderPage; i++){%>
			<input class="btn btn-primary" type="submit" name="<%=ParameterNames.INDEX_OF_CURRENT_PAGE%>" value="<%=i%>">
			<%} %>
			<%if(rightBorderPage<=pageCount-2){%>
			...
			<%} %>
			<%if(rightBorderPage<=pageCount-1){%>
			<input class="btn btn-primary" type="submit" name="<%=ParameterNames.INDEX_OF_CURRENT_PAGE%>" value="<%=pageCount%>">
			<%} %>
			<br><br>
			<input type="hidden" name="<%=ParameterNames.SUBSTRING%>" value="<%=adminUsersDto.getSubstring()%>">
			<input type="range" min="1" max="<%=allAdminUsersCount%>" step="1" name="<%=ParameterNames.VISIBLE_ITEMS_COUNT%>" value="<%=pageOffset%>">
			<input type="submit" value="change">
		</form>
	</div>	
	
</body>
</html>