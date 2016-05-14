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

        <table border="1" cellpadding="10" cellspacing="1" width="100%"> 
            <caption> Tender List </caption> 
            <tr> 
                <th> Title </th> 
                <th> Activity Area </th> 
                <th> Contact Name </th> 
                <th> Contact Phone </th> 
                <th> Contact Email </th> 
                <th> Origin </th> 
            </tr> 
            <c:forEach items="${ sessionScope.cpTenders }" var="tender" >

                <tr> 
                    <th> <c:out value="${tender.title}" />  </th> 
                    <th>  <c:out value="${tender.activityArea}" /> </th> 
                    <th>  <c:out value="${tender.contactName}" /> </th> 
                    <th>  <c:out value="${tender.contactPhone}" /> </th> 
                    <th>  <c:out value="${tender.contactEmail}" /> </th> 
                    <th>  <c:out value="${tender.origin}" /> </th> 
                </tr> 

            </c:forEach>
        </table>
    </body>
</html>

