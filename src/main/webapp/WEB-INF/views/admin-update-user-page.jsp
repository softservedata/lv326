<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.1.1/lumen/bootstrap.min.css">
</head>
<body>

<jsp:include page="_header.jsp"></jsp:include>

	<form action="${ rootURL }/userslist" method="post">
		<fieldset style="width: 40%; margin: 0 auto;">


			<div class="form-group">
				<label>Role : can be User or Admin</label> <input type="text"
					class="form-control" name="newRole" value="${ userRoleAttr }">
			</div>
			<button type="submit" class="btn btn-primary">Change user
				role</button>
		</fieldset>
	</form>


</body>
</html>

