<%-- 
    Document   : index
    Created on : 23 avr. 2016, 11:35:19
    Author     : Fritsch
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index</title>
        <link type="text/css" rel="stylesheet" href="<c:url value ="inc/form.css" />" />
    </head>
    <body>

        <h1>Hello <c:out value="${member.firstname}"/> !</h1>

        <c:import url="/inc/menu.jsp" />

        <fieldset>
            <p><a href="<c:url value="/addTender"/>">Add a tender</a></p>


            <c:if test="${member.role == 'RC'}">

                <p><a href="<c:url value="/handleTender"/>">Handle a tender</a></p>
                <p><a href="<c:url value="/consultTendersToAssign"/>">Assign CP to a tender</a></p>

            </c:if>    
            <c:if test="${member.role == 'CP'}">
                <p><a href="<c:url value="/consultTenders"/>">Consult tenders</a></p>
                <p><a href="<c:url value="/consultMyTenders"/>">Consult my tenders</a></p>
            </c:if>

        </fieldset>
    </body>
</html>
