<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.1.1/lumen/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User list</title>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>

	<div class="container">

		<div class="row col-md-6 col-md-offset-2 custyle">
			<table class="table table-striped custab">
				<thead>
					<tr>
						<th>ID</th>
						<th>User Name</th>
						<th>Login</th>
						<th>password</th>
						<th>Role</th>
						<th class="text-center">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ userAttr }" var="user">
						<tr>
							<td>${ user.id }</td>
							<td>${ user.name }</td>
							<td>${ user.login }</td>
							<td>${ user.password }</td>
							<td>${ user.role }</td>

							<td class="text-center"><a class='btn btn-info btn-xs'
								href="${ rootURL }/updateuser/${ user.id}"><span
									class="glyphicon glyphicon-edit"></span> Edit</a> 
							
							<a onclick="checkDeleteItem('${ rootURL }/admin/delete/${ user.id }')"
								href="#" class="btn btn-danger btn-xs"><span
									class="glyphicon glyphicon-remove"></span> Del</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<script type="text/javascript">
		function checkDeleteItem(url) {
			if (confirm("Are you sure?")) {
				window.location.href = url;
			}
		}
	</script>
</body>
</html>