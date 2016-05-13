<%-- 
    Document   : prospectsList
    Created on : 26 avr. 2016, 17:02:53
    Author     : melanie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show prospects</title>
        <link type="text/css" rel="stylesheet"  href="inc/table.css">
    </head>
    <body>
        <h1>Liste des prospects</h1>
        <table class="showprospectstable">
            <tr>
                <td>Nom de l'entreprise</td>
                <td>Secteur d'activité</td>
                <td>Etat</td>
                <td>Action</td>
            </tr>
            <c:forEach items="${sessionScope.prospects}" var="prospect">
                <tr>
                    <td> <c:out value="${prospect.company_name}" /></td>
                    <td> <c:out value="${prospect.activity_area}" /></td>
                    <td> <c:out value="${prospect.state}" /></td>
                    <td><a href="<c:url value="/ShowProspectCard"><c:param name="prospectid" value="${ prospect.id }" /></c:url>"> appeler</a> </td> 
                </tr>
            </c:forEach>
        </table> 
    </body>
</html>
