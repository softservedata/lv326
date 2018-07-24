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
	<div class="container">

		<form action="${ rootURL }/search" method="post"
			style="margin-left: 720px">
			<div>
				<input type="search" name="searchParam">
				<button>Search</button>
			</div>
		</form>
		<div class="row col-md-6 col-md-offset-2 custyle">
			<table class="table table-striped custab">
				<thead>
					<a href="${ rootURL }/createmovie"
						class="btn btn-primary btn-xs pull-right"><b>+</b> Add new
						movie</a>
					<tr>
						<th>ID</th>
						<th>Film Name</th>
						<th>Descriptiton</th>
						<th>Age limit</th>
						<th>year</th>
						<th class="text-center">Action</th>
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

							<td class="text-center"><a class='btn btn-info btn-xs'
								href="${ rootURL }/updatemovie/${ movie.movieId }"><span
									class="glyphicon glyphicon-edit"></span> Edit</a> <a
								onclick="checkDeleteItem('${ rootURL }/delete/${ movie.movieId }')"
								href="#" class="btn btn-danger btn-xs"><span
									class="glyphicon glyphicon-remove"></span> Del</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<!-- перехід на 1 і ост сторінку -->
			<c:url var="firstUrl" value="/listusersmovies?page=0" />
			<c:url var="lastUrl"
				value="/listusersmovies?page=${ numberOfAllPages - 1}" />
			<!-- перехід на наступну і попередню сторінку -->
			<c:url var="nextUrl"
				value="/listusersmovies?page=${ currentIndex + 1 }" />
			<c:url var="prevUrl"
				value="/listusersmovies?page=${ currentIndex - 1 }" />

			<div class="row" style="margin: 0 auto;">
				<ul class="pagination">
					<!-- if else  -->
					
					<c:choose>
						<c:when test="${ currentIndex == 0 }">
							<li class="disabled"><a href="#">&lt;&lt;</a></li>
							<li class="disabled"><a href="#">&lt;</a></li>
							<li class="active"><a href="${ firstUrl }">1</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${ firstUrl }">&lt;&lt;</a></li>
							<li><a href="${ prevUrl }">&lt;</a></li>
						</c:otherwise>
					</c:choose>
					<c:forEach var="i" begin="${ beginIndex  }" end="${ endIndex  }">
						<c:url var="pageUrl" value="/listusersmovies?page=${ i+1 }"></c:url>

						<c:choose>
							<c:when test="${ i == currentIndex - 1 }">
								<li class="active"><a href="#">${ i + 1 }</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="${ pageUrl }">${ i + 1 }</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<!-- Перевірка на останній елемент , об ми не мошли вийти за межі . -->
					<c:choose>
						<c:when test="${ currentIndex + 1 ==  numberOfAllPages}">
							<li class="disabled"><a href="#">&gt;</a></li>
							<li class="disabled"><a href="#">&gt;&gt;</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${ nextUrl }">&gt;</a></li>
							<li><a href="${ lastUrl }">&gt;&gt;</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
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