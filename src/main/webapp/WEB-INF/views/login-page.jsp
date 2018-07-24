<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.1.1/lumen/bootstrap.min.css">
<link rel="stylesheet"
	href="${ rootURL }/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="${ rootURL }/resources/css/style.css">
<script>
	
</script>
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>

	Hello it is login page

	<div class="container">
		<form class="form-horizontal" style="margin-left: 550px"
			action="${ rootURL }/" method="post">
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label">Enter
					Login</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="login"
						placeholder="Login">
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword3" class="col-sm-2 control-label">Enter
					Password</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" name="password"
						placeholder="Password">

				</div>
			</div>

			<p>
				<font color="red">${error}</font>
			</p>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">Sign in</button>
				</div>
				<a href="${ rootURL }/registration">Register</a>
			</div>
		</form>
	</div>


</body>
</html>