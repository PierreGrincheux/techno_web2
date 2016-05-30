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
        <title>Catnix</title>
        <link type="text/css" rel="stylesheet"  href="inc/table.css">
    </head>
    <body>
        
        <c:import url="/inc/menu.jsp" />
        
        <div class="container">
        <h2>Liste des prospects</h2>
        <table class="table table-striped table-bordered">
            <thead>
                <tr> 
                    <th> Nom de l'entreprise </th> 
                    <th> Secteur d'activité </th> 
                    <th> Etat </th> 
                    <th> Action </th> 
                </tr> 
            </thead>
            <tbody>
            <c:forEach items="${sessionScope.prospects}" var="prospect">
                <tr> 
                    <td> <c:out value="${prospect.company_name}" />  </td> 
                    <td>  <c:out value="${prospect.activity_area}" /> </td> 
                    <td>  <c:out value="${prospect.state}" /> </td> 
                    <td> <a href="<c:url value="/ShowProspectCard">
                                <c:param name="prospectid" value="${ prospect.id }" />
                            </c:url>"> Appeler </a> </td> 
                </tr> 
            </c:forEach>
            </tbody>
            </table> 
        </div>
    </body>
</html>
