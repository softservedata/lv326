<%@include file="/WEB-INF/include/taglib.jsp"%>
<%@include file="/WEB-INF/include/const.jsp"%>

<c:import url="/WEB-INF/views/commons/header.jsp" charEncoding="utf-8" />
<br>
<div class="container">
    <div class="row">
        <div class="col-md-8 offset-md-2 text-center">
            <h1>Users List</h1>
            <br>
            <table id="mytable" class="display">
                <thead>
                    <tr class="table-info">
                        <th>Name</th>
                        <th>Login</th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${!empty usersListDto.users}">
                        <c:forEach items="${usersListDto.users}" var="us">
                            <tr class="table-secondary">
                                <td>${us.name}</td>
                                <td>${us.login}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
</div>