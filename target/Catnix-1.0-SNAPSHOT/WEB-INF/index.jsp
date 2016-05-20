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

        <div class="container">
        <fieldset>
            <p><span class="glyphicon glyphicon-plus"></span><a href="<c:url value="/addTender"/>">Ajouter un AO</a></p>


            <c:if test="${member.role == 'RC'}">

                <p><span class="glyphicon glyphicon glyphicon-tag"></span><a href="<c:url value="/handleTender"/>">Valider un AO</a></p>
                <p><span class="glyphicon glyphicon-list-alt"></span><a href="<c:url value="/consultTendersToAssign"/>">Assigner un AO à un CDP</a></p>
                <p><span class="glyphicon glyphicon-zoom-in"></span><a href="<c:url value="/addProspect"/>">Ajouter des prospects</a></p>

            </c:if>    
            <c:if test="${member.role == 'CP'}">
                <p><span class="glyphicon glyphicon glyphicon-tag"></span><a href="<c:url value="/consultTenders"/>">Consulter les AO disponibles</a></p>
                <p><span class="glyphicon glyphicon glyphicon-tag"></span><a href="<c:url value="/consultMyTenders"/>">Consulter mes AO</a></p>
                <p><span class="glyphicon glyphicon glyphicon-tag"></span><a href="<c:url value="/ShowProspect"/>">Consulter les prospects</a></p>

            </c:if>

        </fieldset>
        </div>
    </body>
</html>
