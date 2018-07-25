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
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/orders.css" type="text/css"/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>

<div class="topnav">
  <a class="active" href="${pageContext.request.contextPath}/orders">Orders</a>
  <c:if test="${idRoleSession eq 1}">
  <a href="${pageContext.request.contextPath}/usersordersadmin">Users Orders</a>
  </c:if>
  <a href="${pageContext.request.contextPath}/profileedit">Edit Profile</a>
  <c:if test="${idRoleSession eq 1}">
  <a href="${pageContext.request.contextPath}/usersprofileseditadmin">Edit Profiles Users</a>
  </c:if>
  <div class="topnav-right">    
    <a href="${pageContext.request.contextPath}/logout">Log out</a>
  </div>
</div>

<div class="container">
  <h2>Order Link</h2>	
	  <c:if test="${error ne null}">
		<p>
			<font color="red">${error}</font>
		</p>
	</c:if> 
	<c:if test="${changesSavedSuccessfully ne null}">
		<p>
			<font color="green">${changesSavedSuccessfully}</font>
		</p>
	</c:if>  	
</div>
 

<div class="container">
 <h2>Page Offset</h2> 
<select id="idVisibleOrders" class="comboBox" onchange="selectVisibleOrders('${hostContext}/ordercount?redirectURLInOrderCount=${redirectURLInOrderCount}&visibleOrders=')">
		<option value="10000"
				<c:if test="${visibleOrders eq '10000'}">
					selected="selected"
				</c:if>
			>all orders</option>
		<option value="10" 
				<c:if test="${visibleOrders eq '10'}">
					selected="selected"
				</c:if>
			>10 orders</option>
		<option value="20"
				<c:if test="${visibleOrders eq '20'}">
					selected="selected"
				</c:if>
			>20 orders</option>
		<option value="50"
				<c:if test="${visibleOrders eq '50'}">
					selected="selected"
				</c:if>
			>50 orders</option>
	</select>
</div>
<hr> 
  
<div class="container">
<form action="${pageContext.request.contextPath}/ordercreate" method="get">  
    <h2>Add Order</h2>
    <button class="addOrderBtn " type="submit" >New Order</button>  
</form>
    
    
    
    


<%-- <%@     firstPage - robut6aktuvny 1 storinky
             pageOffset , visibleItems - kil-y6 items v tabl(10, 20,30)
            & currentPage- poto4na storinka
             & pageCount yakcho pageCount<0 (v List neva items)- tablutsia ne maluetsia  %> --%>


<c:set var="pageStart" value="1"/>
<c:set var="pageEnd" value="9"/>
<c:set var="stopForEach" value="1"/>
          <c:choose>	
		          <c:when test= "${pageNumber > pageCount }">		             
		              <c:forEach var="number" begin="${9}" end="${1}" step="${-1}">
		                 <c:if test="${pageCount-number>0 && stopForEach=1}">
		                  <!-- ${pageStart=pageCount-number}-->
		                   <!-- ${stopForEach=0}-->
		                 </c:if>
		                  <!-- ${stopForEach=1}-->
		             </c:forEach> 		            		             
		             <!-- ${pageEnd=pageCount}-->
		             <!-- ${pageNumber=pageEnd}-->
		          </c:when>
		          <c:when test= "${pageNumber <= 1 }">
		             <!-- ${pageStart=1}-->
		             <!-- ${pageEnd=9}-->
		             <!-- ${pageNumber=pageStart}-->
		          </c:when>		          
		          <c:when test= "${pageNumber <= pageCount && pageNumber>1}">
		              <!--${pageStart=pageNumber}-->
		             <!-- ${pageEnd=pageNumber+8}-->
		          </c:when>
		    </c:choose>	
</div>
<hr> 

<div class="w3-container">
<c:set var="currentOrders" value="${userOrdersDto.orders}"/>
	<c:if test="${currentOrders ne null && currentOrders.size() gt 0 && pageCount gt 0}">
		<table class="w3-table w3-striped w3-border" border="1">
			<tr>
				<th>Id_Order</th>
				<th>Shop</th>
				<th>Address</th>
				<th>Production</th>
				<th>Scope</th>
				<th>Status</th>
				<th colspan="2">Action</th>	
			</tr>
			
			<c:choose>
			<c:when test="${pageNumber eq 1}">
			<c:forEach var="row" items="${currentOrders}" begin="0" end="${visibleOrders-1}">
				<tr>
					<td>${row.getIdOrder()}</td>
					<td>${row.getShop()}</td>
					<td>${row.getAddress()}</td>
					<td>${row.getProduction()}</td>
					<td>${row.getScope()}</td>
					<td>${row.getStatus()}</td>
					<td>					
					<button type="button" class="tableBtn" onclick="location.href='${pageContext.request.contextPath}/orderedit?idOrder=${row.getIdOrder()}';">Edit</button>
				    </td>
					<td>
					<button type="button" class="tableBtn" onclick="checkDeleteOrder('${hostContext}/orderdelete?idOrder=${row.getIdOrder()}')">Delete</button>
				    </td>				    
				</tr>
			</c:forEach>			
			</c:when>				
			
			<c:when test="${pageNumber > 1 && pageNumber<= pageCount}">
			<c:forEach var="row" items="${currentOrders}" begin="${visibleOrders* (pageNumber-1) }" end="${(visibleOrders* pageNumber)-1}" >
				<tr>
					<td>${row.getIdOrder()}</td>
					<td>${row.getShop()}</td>
					<td>${row.getAddress()}</td>
					<td>${row.getProduction()}</td>
					<td>${row.getScope()}</td>
					<td>${row.getStatus()}</td>
					<td>
					<button type="button" class="tableBtn" onclick="location.href='${pageContext.request.contextPath}/orderedit?idOrder=${row.getIdOrder()}';">Edit</button>
				    </td>
					<td>
					<button type="button" class="tableBtn" onclick="checkDeleteOrder('${hostContext}/orderdelete?idOrder=${row.getIdOrder()}')">Delete</button>
				    </td>			    
				</tr>
			</c:forEach>	
			</c:when>
			</c:choose>			
		</table>
		
		<hr>

		
		
		
		
		<div class="pagination">
		<a href="${hostContext}/orders?pageNumber=1">&laquo;</a>
		<a href="${hostContext}/orders?pageNumber=${pageNumber - 10}">&lt;</a>
		
		   
		           		
		 <c:choose>
		          <c:when test= "${ currentOrders.size() <= visibleOrders }">
		              <a class="active" href="${hostContext}/orders?pageNumber=1">1</a>
		           </c:when>
		           <c:otherwise>
		             <c:forEach var="page"  begin="${pageStart}" end="${pageEnd}">		    
		   <c:if test="${page <= pageCount }">
		        <c:choose>
		          <c:when test= "${page eq  pageNumber}">
		              <a class="active" href="${hostContext}/orders?pageNumber=${page}">${page}</a>
		           </c:when>
		           <c:otherwise>
		             <a href="${hostContext}/orders?pageNumber=${page}">${page}</a>
 		          </c:otherwise>
		         </c:choose>           
           </c:if>        
        </c:forEach>
 		          </c:otherwise>
		         </c:choose>    
		
        <a href="${hostContext}/orders?pageNumber=${pageNumber + 10}">&gt;</a>
        <a href="${hostContext}/orders?pageNumber=${pageCount}">&raquo;</a>
		</div>
</c:if>
</div>
<br>
<hr>
<hr>

<div class="navbar">
  <p>@ Created by Roman Mozil</p>
</div>

<script type="text/javascript">
		function checkDeleteOrder(url) {
			if (confirm("Are you sure?")) {
				window.location.href = url;
			}
		}
		function selectVisibleOrders(url) {
			var visibleOrders = document.getElementById("idVisibleOrders");
			window.location.href = url
				+ visibleOrders.options[visibleOrders.selectedIndex].value;
		}
	</script>
</body>
</html>
