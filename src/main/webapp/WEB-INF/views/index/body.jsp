<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<p>Menu index 1</p>
<p>Menu index 2</p>
<br/>
CONTEXT:<c:out value="${pageContext.request.contextPath}" />
<br/>
<a href="${pageContext.request.contextPath}/home">goto home</a>
