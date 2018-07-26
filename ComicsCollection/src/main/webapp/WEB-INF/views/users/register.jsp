<%@include file="/WEB-INF/include/taglib.jsp"%>
<%@include file="/WEB-INF/include/const.jsp"%>

<c:import url="/WEB-INF/views/commons/header.jsp" charEncoding="utf-8" />
<br>
<div class="container">
	<div class="row">
		<div class="col-md-4 offset-md-4 text-center">
			<c:if test="${error ne null}">
				<p style="color: red">${error} </p>
			</c:if>
			<form action="${path}/register" method="post">
				<h2>Register</h2>
				<div class="form-group">
					<input type="text" name="name" class="form-control" placeholder="Name:">
				</div>
				<div class="form-group">
					<input type="text" name="login" class="form-control" placeholder="Email:">
				</div>
				<div class="form-group">
					<input type="password" name="password" class="form-control" placeholder="Password:">
				</div>
				<div class="form-group">
					<input type="password" name="confirmpassword" class="form-control" placeholder="Confirm password:">
				</div>
				<button type="submit" name="register" class="btn btn-primary btn-lg btn-block">Submit</button>
			</form>
			<form action="${path}/usercancel" method="post">
				<button type="submit" name="cancel" value="Cancel" method="post" class="btn btn-primary btn-lg btn-block">
					Cancel
				</button>
			</form>
		</div>
	</div>
</div>