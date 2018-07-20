<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	<c:set var="req" value="${pageContext.request}" />
	<c:set var="url">${req.requestURL}</c:set>
	<c:set var="base" value="${fn:substring(url, 0, fn:length(url) - fn:length(req.requestURI))}${req.contextPath}" />
	<c:set var="context" value="${req.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

  <h1>Work</h1>
  <br> <br>
  <!-- HTML Comment -->
  <%-- JSP Comment --%>
  <h2>Hello1 ${name}</h2>
  <br><br>url: ${url}
  <br><br>base: <c:out value="${base}" />
  <br><br>context: <c:out value="${context}" />

	<br><br>
	<form action="${base}/views/info.jsp" method="get">
		Text: <input type="text" name="box" value="get"> <br> <br>
		<input type="submit" name="Mysubmit" value="Send"> <br> <br>
	</form>

	<br><br>
	<form action="${pageContext.request.contextPath}/views/info.jsp" method="get">
		Text: <input type="text" name="box" value="get2_/views/info.jsp"> <br> <br>
		<input type="submit" name="Mysubmit" value="Send"> <br> <br>
	</form>

	<br><br>
	<form action="${pageContext.request.contextPath}/dashboard" method="get">
		Text: <input type="text" name="box" value="get3_/dashboard"> <br> <br>
		<input type="submit" name="Mysubmit" value="Send"> <br> <br>
	</form>

</body>
</html>
 