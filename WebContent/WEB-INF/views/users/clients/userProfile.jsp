<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.passwordsaver.dto.*" %>
<%@ page import="com.passwordsaver.controllers.*" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://bootswatch.com/4/lux/bootstrap.css">
<title>UserProfile</title>
</head>
<body>
	<%! final int pagenationCount = 5;%>
	
		<% 
			UserKeysDto userKeysDto =(UserKeysDto)request.getAttribute(AttributeNames.USER_KEYS_DTO.toString());
			int allUserKeysCount = userKeysDto.getAllUserKeysCount();
			int currentPage = (Integer)request.getAttribute(AttributeNames.INDEX_OF_CURRENT_PAGE.toString() );
			int pageOffset = userKeysDto.getPageOffset();
			int pageCount = userKeysDto.getPageCount();
			List<KeyDto> keys = userKeysDto.getKeys();
			int from = (Integer)request.getAttribute(AttributeNames.INDEX_OF_FIRST_ITEM_ON_PAGE.toString());
			int rightBorderPage = (Integer)request.getAttribute(AttributeNames.RIGHT_BOUNDARY_PAGE.toString());
			int leftBorderPage = (Integer)request.getAttribute(AttributeNames.LEFT_BOUNDARY_PAGE.toString());
			String addKeyUrl = request.getContextPath()+ControllerUrls.ADD_KEY_SERVLET;
		%>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		  <a class="navbar-brand" href="#"><%= userKeysDto.getUserLogin() %>'s profile</a>
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor02" aria-controls="navbarColor02" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		  </button>

		  <div class="collapse navbar-collapse" id="navbarColor02">
			<ul class="navbar-nav mr-auto">
			  <li class="nav-item active">
				<a class="nav-link" href="<%=request.getContextPath()%><%=ControllerUrls.UPDATE_PROFILE_SERVLET%>">update profile</a>
			  </li>
			  <li class="nav-item active">
				<a class="nav-link" href="<%=addKeyUrl%>"> add key</a>
			  </li>
			  <li class="nav-item active">
				<a class="nav-link" href="<%=request.getContextPath()%><%=ControllerUrls.LOGOUT_SERVLET%>">Log Out</a>
			  </li>
			</ul>
			<form class="form-inline my-2 my-lg-0" action="<%=request.getContextPath()%><%=ControllerUrls.PERSONAL_CABINET_SERVLET%>" method="get">
			  <input class="form-control mr-sm-2" type="text" name="<%=ParameterNames.SUBSTRING%>" value="<%=userKeysDto.getSubstring()%>" placeholder="Search">
			  <button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
			</form>
		  </div>
		</nav>
	<div class="container">	<br>
		<div class="page-header">
              <h1 id="tables">Keys</h1>
        </div>
        
        <table class="table table-hover">
		  <thead>
		    <tr>
		      <th scope="col">Number</th>
		      <th scope="col">Service</th>
		      <th scope="col">Password</th>
		      <th scope="col">edit</th>
		      <th scope="col">delete</th>
		    </tr>
		  </thead>
		  <tbody>
		  	<%for(int i=0;i<keys.size();i++){ %>
			<tr class="table-active">
				<th scope="row"><%=i+from%></th>
				<td><%=keys.get(i).getService()%></td>
				<td><%=keys.get(i).getServPassword()%></td>
				<td>
					<form method="get" action="<%=request.getContextPath()+ControllerUrls.EDIT_KEY_SERVLET%>">
						<input type="hidden" name="<%=ParameterNames.ID_KEY%>" value="<%=keys.get(i).getIdKey()%>">
						<input type="submit" value="edit">
					</form>
				</td>
				<td>
					<form method="post" action="<%=request.getContextPath()+ControllerUrls.DELETE_KEY_SERVLET.toString()%>">
						<input type="hidden" name="<%=ParameterNames.ID_KEY%>" value="<%=keys.get(i).getIdKey()%>">
						<input type="submit" value="delete">
					</form>
				</td>
			</tr>
			<%} %>
		
		  </tbody>
		</table> 
		
		<br>
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
			<input type="hidden" name="<%=ParameterNames.SUBSTRING%>" value="<%=userKeysDto.getSubstring()%>">
			<input type="range" min="1" max="<%=allUserKeysCount%>" step="1" name="<%=ParameterNames.VISIBLE_ITEMS_COUNT%>" value="<%=pageOffset%>">
			<input type="submit" value="change">
		</form>
	</div>
</body>
</html>