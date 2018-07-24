<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.1.1/lumen/bootstrap.min.css">
<link rel="stylesheet"
	href="${rootURL }/resources/css/bootstrap.min.css">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>


	<div class="row col-md-6 col-md-offset-2 custyle">
		<table class="table table-striped custab">
			<thead>
				<tr>
					<th>ID</th>
					<th>Film Name</th>
					<th>Descriptiton</th>
					<th>Age limit</th>
					<th>year</th>
					
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ movieAttr }" var="movie">
					<tr>
						<td>${ movie.movieId }</td>
						<td>${ movie.filmName }</td>
						<td>${ movie.description }</td>
						<td>${ movie.ageLimit }</td>
						<td>${ movie.year }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>


</body>
</html>