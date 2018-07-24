<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.1.1/lumen/bootstrap.min.css">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="_header.jsp"></jsp:include>

	<form action="${ rootURL }/updatemovie" method="post">
		<fieldset style="width: 40%; margin: 0 auto;">
			<legend>Add new Film</legend>
			<div class="form-group row">
				<label for="staticEmail" class="col-sm-2 col-form-label">Movie</label>
				<div class="col-sm-10">
				</div>
			</div>
		<input type="hidden" name="id" value=${ id }>
			<div class="form-group">
				<label>Movie Name</label> <input type="text" class="form-control"
					name="newnameParam" placeholder="Enter Movie Name"
					value="${ movieAttr.filmName }"> <small
					class="form-text text-muted">Please enter movie name.</small>
			</div>

			<div class="form-group">
				<label>Description</label> <input type="text" class="form-control"
					aria-describedby="emailHelp" name="newdescriptionParam"
					placeholder="Enter Description" value="${ movieAttr.description }">
				<small class="form-text text-muted">Info About movie </small>
			</div>

			<div class="form-group">
				<label>Age Limit</label> <input type="text" class="form-control"
				name="newAgeLimitParam" placeholder="Enter Description" value="${ movieAttr.ageLimit }">
			</div>


			<div class="form-group">
				<label>Year</label> <input type="text" class="form-control"
					placeholder="Year" name="newyearParam" value="${ movieAttr.year }">
				<small class="form-text text-muted">Enter please year</small>
			</div>
			
			<button type="submit" class="btn btn-primary">Update Film in ${ userNameAttr }
				collections</button>
		</fieldset>
	</form>



</body>
</html>