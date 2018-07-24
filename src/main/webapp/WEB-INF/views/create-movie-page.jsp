<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.1.1/lumen/bootstrap.min.css">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="_header.jsp"></jsp:include>

	<form action="${ rootURL }/createmovie" method="post">
		<fieldset style="width: 40%; margin: 0 auto;">
			<legend>Add new Film</legend>
			<div class="form-group row">
				<label for="staticEmail" class="col-sm-2 col-form-label">Movie</label>
				<div class="col-sm-10">
					<input type="text" class="form-control-plaintext"
						value="Harry Potter">
				</div>
			</div>

			<div class="form-group">
				<label>Movie Name</label> <input type="text" class="form-control"
					name="nameParam" placeholder="Enter Movie Name" value="${ movie.filmName }"> <small
					class="form-text text-muted">Please enter movie name.</small>
			</div>

			<div class="form-group">
				<label>Description</label> <input type="text" class="form-control"
					aria-describedby="emailHelp" name="descriptionParam"
					placeholder="Enter Description" value="${ movie.description }"> <small
					class="form-text text-muted">Info About movie </small>
			</div>

			<div class="form-group">
				<label for="exampleSelect1">Example select</label> <select 
					class="form-control" style="height: 30px" name="ageLimitParam" >
					<c:forEach items="${ ageLimitAttr }" var="ageLimit">
						<option>${ ageLimit.getLimit() }</option>
					</c:forEach>
				</select>
			</div>

			<div class="form-group">
				<label>Year</label> <input type="text" class="form-control" placeholder="Year" 
					name="yearParam" value="${ movie.year }"> 
					<small class="form-text text-muted">Enter please year</small>
			</div>
			<p><font color="red">${error}</font></p>

			<button type="submit" class="btn btn-primary">Add Film to ${ userNameAttr } collections</button>
		</fieldset>
	</form>


</body>
</html>