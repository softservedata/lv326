<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Open+Sans:600'>
    <title>Edit Page</title>
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
<div class="login-wrap">
    <div class="login-html">
        <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Edit</label>
        <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab"></label>
        <div class="login-form">
            <form action="${pageContext.request.contextPath}/useredit" method="post">
                <div class="sign-in-htm">
                    <div class="group">
                        <h1>${userEdit.username}</h1>
                    </div>
                    <div class="group">
                        <input id="username" name="username" type="hidden" value="${userEdit.username}" readonly class="input">
                   </div>
                            <div class="group">
                                <input id="password" name="password" type="hidden" class="input" data-type="password" value="${userEdit.password}" required>
                            </div>
                    <div class="group">
                        <label for="name" class="label">Name</label>
                        <input id="name" name="name" type="text" value="${userEdit.name}" class="input" required>
                    </div>
                    <div class="group">
                        <label for="surname" class="label">Surname</label>
                        <input id="surname" name="surname" type="text" value="${userEdit.surname}"  class="input" required>
                    </div>
                    <div class="group">
                        <label for="role" class="label">Role</label><p>"${userEdit.role}"</p>

                        <input id="role" type="hidden" name="role" value="${userEdit.role}"  readonly>
                    </div>
                    <div class="group">
                        <input type="submit" class="button" value="Update User">
                    </div>
                    <div class="hr"></div>
                    <div class="foot-lnk">
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
