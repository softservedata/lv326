<%@include file="/WEB-INF/include/taglib.jsp"%>
<%@include file="/WEB-INF/include/const.jsp"%>

<c:import url="/WEB-INF/views/commons/header.jsp" charEncoding="utf-8" />
<br>
<div class="container">
	<div class="row">
		<div class="col-md-8 offset-md-2 text-center">
			<h1>${userDto.name}'s Comics List</h1>
			<br>
			<a href="${path}/addcomics" class="btn btn-primary btn-lg btn-block">ADD NEW COMICS</a>
			<br>
			<table id="mytable" class="display">
				<thead>
					<tr class="table-info">
						<th>Title</th>
						<th>Author</th>
						<th>Publishing Office</th>
						<th>Description</th>
						<th>edit</th>
						<th>delete</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${!empty userComicsDto.comics}">
						<c:forEach items="${userComicsDto.comics}" var="comics">
							<tr class="table-secondary">
								<td>${comics.title}</td>
								<td>${comics.author}</td>
								<td>${comics.publishingOffice}</td>
								<td>${comics.description}</td>
								<td><a href="${path}/editcomics?idComics=${comics.idComics}">edit</a></td>
								<td><a href="#" onclick="checkDeleteComics('${hostContext}/deletecomics?idComics=${comics.idComics}')">delete</a></td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
	function checkDeleteComics(url) {
		if (confirm("Are you sure?")) {
			window.location.href = url;
		}
	}
</script>