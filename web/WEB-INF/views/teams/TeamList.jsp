<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="hostUrl">${pageContext.request.requestURL}</c:set>
<c:set var="hostUri">${pageContext.request.requestURI}</c:set>
<c:set var="hostContext" value="${fn:substring(hostUrl, 0, fn:length(hostUrl) - fn:length(hostUri))}${pageContext.request.contextPath}" />
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Team List</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
    <link rel="stylesheet" type="text/css" href="resources/css/table.css">
</head>
<body>
<div class="header">
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
        <h1>Team List</h1>
        <a href="${pageContext.request.contextPath}/teamcreate">Add Team</a>
        <thead>
        <tr>
            <th>Name</th>
            <th>Country</th>
            <th>City</th>
            <th > </th>
        </tr>
        </thead>
        <tfoot>
        <tr>
            <td colspan="4">
                <div class="links"><a href="#">&laquo;</a> <a class="active" href="#">1</a> <a href="#">2</a> <a href="#">3</a> <a href="#">4</a> <a href="#">&raquo;</a></div>
            </td>
        </tr>
        </tfoot>
        <tbody>
        <c:forEach items="${teamsList}" var="team" >
            <tr>
                <td><a href="${pageContext.request.contextPath}/teamHasPlayers?idTeam=${team.id}">${team.name}</a></td>
                <td><p>${team.country}</p></td>
                <td>${team.city}</td>
                <td><a href="${pageContext.request.contextPath}/teamEdit?idTeam=${team.id}" ><button class="btn-btn-warning">Edit</button></a>
                <a href="#" onclick="checkDeleteItem('${hostContext}/teamdelete?idTeam=${team.id}')"><button class="btn-danger">Delete</button></a>
                <a href="${pageContext.request.contextPath}/myTeamAdd?idTeam=${team.id}&username=${loginDto.getUsername()}"><button class="btn-success">Add Team</button> </a></td>
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
