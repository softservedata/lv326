<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Open+Sans:600'>
    <title>Sign in</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
</head>
<body>
<div class="login-wrap">
    <div class="login-html">
        <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Registration</label>
        <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab"></label>
        <div class="login-form">
            <form action="${pageContext.request.contextPath}/usercreate" method="post">
                <div class="sign-in-htm">
                    <div class="group">
                        <label for="username"  class="label">Username</label>
                        <input id="username" name="username" type="text" class="input" value="${user.username}" required>
                    </div>
                    <c:if test="${errorUser ne null}">
                    <div class="group">
                            <p><font color="red">${errorUser}</font></p>
                    </div>
                    </c:if>
                    <div class="group">
                        <label for="password" class="label">Password</label>
                        <input id="password" name="password" type="password"  class="input" data-type="password" required>
                    </div>
                    <div class="group">
                        <label for="firstname" class="label">Name</label>
                        <input id="firstname" name="firstname" type="text" class="input" value="${user.name}" required>
                    </div>
                    <div class="group">
                        <label for="lastname" class="label">Surname</label>
                        <input id="lastname" name="lastname" type="text" value="${user.surname}" class="input" required>
                    </div>
                    <div class="group">
                        <input id="role" name="role" type="hidden" value="USER" class="input" required>
                    </div>
                    <div class="group">
                        <input type="submit" class="button" value="Sign Up">
                    </div>

                    <div class="hr"></div>
                    <div class="foot-lnk">
                        <div class="group">
                            <a href="${pageContext.request.contextPath}/login">Sign In</a>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
