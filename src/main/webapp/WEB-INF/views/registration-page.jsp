<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file = "/WEB-INF/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.1.1/lumen/bootstrap.min.css">
<link rel="stylesheet"
	href="${ rootURL }/resources/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${ rootURL }/resources/css/style.css">
<title>Ragistration Page</title>
</head>
<body>

	<jsp:include page="_header.jsp"></jsp:include>

<form action="${ rootURL }/registration" method="post">
  <fieldset style="width: 30%; margin: 0 auto;">
    <legend>User Ragistration Page</legend>
    <div class="form-group row">
      <label for="staticEmail" class="col-sm-2 col-form-label">Login</label>
      <div class="col-sm-10">
        <input type="text" class="form-control-plaintext" value="login_example">
      </div>
    </div>
    
     <div class="form-group">
      <label>Name</label>
      <input type="text" class="form-control" name="name" placeholder="Enter Name" >
      <small class="form-text text-muted">Please enter your real name.</small>
    </div>
    
    <div class="form-group">
      <label>Login</label> 	<p><font color="red">${error}</font></p>
      <input type="text" class="form-control"  aria-describedby="emailHelp"name="login" placeholder="Enter Login">
       <small  class="form-text text-muted">login must contain 6 characters.</small>
    </div>
    
    <div class="form-group">
      <label>Password</label>
      <input type="password" class="form-control" name="password"  placeholder="Password">
      <small id="emailHelp" class="form-text text-muted">Password must contain 8 characters.</small>
    </div>
    
    <button type="submit" class="btn btn-primary" >Register</button>
  </fieldset>
</form>

</body>
</html>