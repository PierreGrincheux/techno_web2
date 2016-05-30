<%-- 
    Document   : handleTender
    Created on : 27 avr. 2016, 13:50:43
    Author     : Fritsch
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Catnix</title>
        <link type="text/css" rel="stylesheet" href="<c:url value ="inc/form.css" />" />
    </head>
    <body>

        <c:import url="/inc/menu.jsp" />

        <div class="container">

            <h1> Liste des appels d'offre à valider </h1>
            <br/>

            <table  class="table table-striped table-bordered"> 
                <thead>
                    <tr> 
                        <th> Titre </th> 
                        <th> Secteur d'activité </th> 
                        <th> Nom du contact </th> 
                        <th> Mobile du contact </th> 
                        <th> Mail du contact </th> 
                        <th> Origine de l'AO </th> 
                        <th> Accepter </th> 
                        <th> Rejeter </th> 
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
                            <td>  <c:out value="${tender.origin}" /> </td> 
                            <td> <a href="<c:url value="/acceptTender"><c:param name="idTender" value="${ tender.id }" /></c:url>"> Accept </a> </td>
                            <td> <a href="<c:url value="/rejectTender"><c:param name="idTender" value="${ tender.id }" /></c:url>"> Reject </a> </td> 
                            </tr> 
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
