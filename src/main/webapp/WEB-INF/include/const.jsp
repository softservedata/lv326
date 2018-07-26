<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<c:set var="hostContext" value="${fn:substring(hostUrl, 0, fn:length(hostUrl) - fn:length(hostUri))}${pageContext.request.contextPath}"/>
<c:set var="hostUrl">${pageContext.request.requestURL}</c:set>
<c:set var="hostUri">${pageContext.request.requestURI}</c:set>
<c:set var="path" value="${pageContext.request.contextPath}"/>
