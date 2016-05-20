<%-- 
    Document   : consultMyTenders
    Created on : 10 mai 2016, 11:01:37
    Author     : Nico
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consult my Tenders</title>
        <link type="text/css" rel="stylesheet" href="<c:url value ="inc/form.css" />" />
    </head>
    <body>


        <c:import url="/inc/menu.jsp" />
        <div class="container">
            <h2> Tender List </h2> 
        <table class="table table-bordered table-striped"> 
            <thead>
            <tr> 
                <th> Title </th> 
                <th> Activity Area </th> 
                <th> Contact Name </th> 
                <th> Contact Phone </th> 
                <th> Contact Email </th> 
                <th> Origin </th> 
            </tr> 
            </thead>
            <tbody>
            <c:forEach items="${ sessionScope.cpTenders }" var="tender" >

                <tr> 
                    <td> <c:out value="${tender.title}" />  </td> 
                    <td>  <c:out value="${tender.activityArea}" /> </td> 
                    <td>  <c:out value="${tender.contactName}" /> </td> 
                    <td>  <c:out value="${tender.contactPhone}" /> </td> 
                    <td>  <c:out value="${tender.contactEmail}" /> </td> 
                    <td>  <c:out value="${tender.origin}" /> </td> 
                </tr> 

            </c:forEach>
            </tbody>
        </table>
        </div>
    </body>
</html>

