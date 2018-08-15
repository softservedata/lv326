
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="hostUrl">${pageContext.request.requestURL}</c:set>
<c:set var="hostUri">${pageContext.request.requestURI}</c:set>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Users List</title>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
    <link rel="stylesheet" type="text/css" href="resources/css/table.css">
</head>
<body>
<div class="header">
    <a href="${pageContext.request.contextPath}/userprofile?username=${loginDto.getUsername()}"><input type="button" value="My Profile"></a>
    <a href="${pageContext.request.contextPath}/myteams?username=${loginDto.getUsername()}"><input type="button" value="My Teams"></a>
    <a href="${pageContext.request.contextPath}/teamsList"><input type="button" value="Team List"></a>
    <a href="${pageContext.request.contextPath}/usersList"><input type="button" value="Users List"></a>
    <a href="${pageContext.request.contextPath}/logout"><input type="button" value="Log Out"></a>
</div>
<hr>
<hr>
<div class="templatemo-table">
    <table class="MyTable">
        <h1>Users List</h1>

        <thead>
        <tr>
            <th>USERNAME</th>
            <th>Surname Name</th>
            <th>Role</th>
            <th> </th>
            <th> </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${usersList}" var="user" >
        <tr>
            <td>${user.username}</td>
            <td><p>${user.surname} ${user.name}</p></td>
            <td>${user.role}</td>
            <td><a href="${pageContext.request.contextPath}/useredit?username=${user.username}" >Edit</a></td>
            <td><a href="#" onclick="checkDeleteItem('${hostContext}/userdelete?username=${user.username}')">Delete</a></td>
            </td>
        </tr>
            </c:forEach>
        </tbody>
        </tbody>
    </table>

</div>

<script type="text/javascript">
    function checkDeleteItem(url) {
        if (confirm("Are you sure?")) {
            window.location.href = url;
        }
    }
    function selectVisibleItems(url) {
        var visibleItems = document.getElementById("idVisibleItems");
        window.location.href = url
            + visibleItems.options[visibleItems.selectedIndex].value;
    }
</script>
</body>
</html>