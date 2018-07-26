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
            <form action="${path}/changepassw" method="post">
                <h2>Change Password</h2>
                <div class="form-group">
                    <input type="password" name="oldpassword" class="form-control" placeholder="Enter old password">
                </div>
                <div class="form-group">
                    <input type="password" name="password" class="form-control" placeholder="Enter new  password">
                </div>
                <div class="form-group">
                    <input type="password" name="confirmpassword" class="form-control" placeholder="Confirm new password:">
                </div>
                <button type="submit" class="btn btn-primary btn-lg btn-block">Submit</button>
            </form>
        </div>
    </div>
</div>