<%-- 
    Document   : handleTender
    Created on : 27 avr. 2016, 13:50:43
    Author     : Fritsch
--%>
<!--kk-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tender List</title>
        <link type="text/css" rel="stylesheet" href="<c:url value ="inc/form.css" />" />
    </head>
    <body>

        <c:import url="/inc/menu.jsp" />
        
        
        <div class="container">
            <h2> Tender List </h2> 
            <table class="table table-striped table-bordered">
                
       <!--/*<table border="1" cellpadding="10" cellspacing="1" width="100%"> */-->
          
       <thead>
            <tr> 
                <th> Title </th> 
                <th> Activity Area </th> 
                <th> Contact Name </th> 
                <th> Contact Phone </th> 
                <th> Contact Email </th> 
                <th> Origin </th> 
                <th> Take </th> 
                <th> Reject </th> 
            </tr>
       </thead>
       <tbody>
            <c:forEach items="${ sessionScope.tenders }" var="tender" >
                <tr> 
                    <td> <c:out value="${tender.title}" />  </td> 
                    <td>  <c:out value="${tender.activityArea}" /> </td> 
                    <td>  <c:out value="${tender.contactName}" /> </td> 
                    <td>  <c:out value="${tender.contactPhone}" /> </td> 
                    <td>  <c:out value="${tender.contactEmail}" /> </td> 
                    <td>  <c:out value="${tender.origin}" /> </th> 
                    <td> <a href="<c:url value="/acceptTender"><c:param name="idTender" value="${ tender.id }" /></c:url>"> Accept </a> </th>
                    <td> <a href="<c:url value="/rejectTender"><c:param name="idTender" value="${ tender.id }" /></c:url>"> Reject </a> </th> 
                    </tr> 
            </c:forEach>
       </tbody>
        </table>
       </div>
    </body>
</html>
