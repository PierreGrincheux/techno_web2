<%-- 
    Document   : assignTenderToCp
    Created on : 8 mai 2016, 23:21:47
    Author     : Nico
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
        <h2> Tender to Assign List </h2>
        <!--<table border="1" cellpadding="10" cellspacing="1" width="100%">--> 
        <table class="table table-striped table-bordered">
            <thead>
            <tr> 
                <th> Title </th> 
                <th> Motivation Text </th> 
                <th> Name CP </th> 
                <th> Date </th> 
                <th> Action </th> 
            </tr> 
            </thead>
            <tbody>
            <c:forEach items="${ sessionScope.tendersToAssign }" var="tenderToAssign" >
                <tr> 
                    <td> <c:out value="${tenderToAssign.title}" />  </td> 
                    <td>  <c:out value="${tenderToAssign.motivationText}" /> </td> 
                    <td>  <c:out value="${tenderToAssign.nameCP}" /> </td> 
                    <td>  <c:out value="${tenderToAssign.date}" /> </td> 
                    <td> <a href="<c:url value="/assignCpToTender">
                                <c:param name="idTenderToAssign" value="${ tenderToAssign.id }" />
                                <c:param name="idTender" value="${ tenderToAssign.id_tender }" />
                                <c:param name="nameCp" value="${ tenderToAssign.nameCP }" />
                            </c:url>"> Assign </a> </td> 
                </tr> 
            </c:forEach>
            </tbody>
        </table>
    </div>
    </body>
</html>
