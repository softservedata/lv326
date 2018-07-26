<%@include file="/WEB-INF/include/taglib.jsp"%>
<%@include file="/WEB-INF/include/const.jsp"%>

<c:import url="/WEB-INF/views/commons/header.jsp" charEncoding="utf-8" />
<br>
<div class="container">
    <div class="row">
        <div class="col-md-4 offset-md-4 text-center">
            <div class="card mb-3">
                <img style="height: 300px; width: 100%; display: block;" src="/resources/avatar.png" alt="Card image">
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">Name: ${userDto.name}</li>
                    <li class="list-group-item">Login: ${userDto.login}</li>
                </ul>
                <a href="${path}/changepassw" class="btn btn-primary btn-lg btn-block"> Change Password</a>
            </div>
        </div>
    </div>
</div>