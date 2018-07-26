<%@include file="/WEB-INF/include/taglib.jsp"%>
<%@include file="/WEB-INF/include/const.jsp"%>

<html>
<head>
	<title>${pageTitle}</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	<link rel="icon" type="image/x-icon" href="/resources/favicon.ico">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootswatch/4.0.0/sketchy/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css">
	<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.3.min.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript">
        $(document).ready(function(){
            $("#mytable").DataTable();
        });
	</script>
</head>
<body>
	<div class="container">
		<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
			<a class="navbar-brand" href="">Comics Collection</a>
			<div class="collapse navbar-collapse" id="navbarColor01">
				<ul class="navbar-nav mr-auto">
					<c:if test="${userDto.role == 'admin'}">
						<li class="nav-item">
							<a class="nav-link" href="${path}/userslist">Users List</a>
						</li>
					</c:if>
					<li class="nav-item">
						<a class="nav-link" href="${path}/usercomics">My List</a>
					</li>
				</ul>
				<div class="form-inline my-2 my-lg-0">
					<ul class="navbar-nav mymargin">
						<li>
							<a href="${path}/userprofile" class="nav-link">${userDto.login}</a>
						</li>
						<li>
							<a href="${path}/logout" class="nav-link">Logout</a>
						</li>
						<li class="divider"></li>
					</ul>
				</div>
			</div>
		</nav>
	</div>
</body>

