<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<!------ Include the above in your HEAD tag ---------->
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.1.1/lumen/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>


	<c:choose>
		<c:when test="${ userRoleId == 2 }">
			<h2>Hello You Are Admin ${ nameAttr }</h2>
			<ul class="list-group">
				<li class="list-group-item"><a
					href="${ rootURL }/listusersmovies?page=0">Список всіх фільмів</a>
					<span class="badge">You have ${ countAttr } movies</span></li>
			</ul>
			<ul class="list-group">
				<li class="list-group-item"><a href="${ rootURL }/userslist">Список
						всіх Користувачів</a></li>
			</ul>
		</c:when>
		<c:otherwise>
			<div class="main-content">
				<div class="container">
					<h3 class="site-title" style="color: red">My Profile Page</h3>
					<div class="col-md-5">
						<img class="img-responsive"
							src="http://www.pvhc.net/img240/uyttxprhsqycyximpzjb.png"
							style="width: 30%;"> 
						<div class=" clearfix">
							<h3>Hello ${ nameAttr }</h3>
							<h4>You are a Free Member</h4>
							<button type="button" class="btn btn-success btn-md pull-center">Upgrade
								now</button>
							<hr>
						</div>
					</div>
					<ul class="list-group">
						<li class="list-group-item"><a
							href="${ rootURL }/listusersmovies?page=0">Список фільмів</a> <span
							class="badge">You have ${ countAttr } movies</span></li>
					</ul>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>