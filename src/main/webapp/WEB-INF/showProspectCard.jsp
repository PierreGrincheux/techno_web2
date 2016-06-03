<%-- 
    Document   : showProspectCard
    Created on : 27 avr. 2016, 19:58:52
    Author     : melanie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><c:out value="${sessionScope.prospect.company_name}" /></h1>
        <div>
            <h2>informations nécéssaires pour contacter le prospect</h2>
            <p>
                Secteur d'activité : <c:out value="${sessionScope.prospect.company_name}" /> <br>
                Site internet : <c:out value="${sessionScope.prospect.company_name}" /> <br>
                N° de téléphone : <c:out value="${sessionScope.prospect.company_name}" /> <br>
                Adresse email : <c:out value="${sessionScope.prospect.company_name}" /> <br>
                Contact : <c:out value="${sessionScope.prospect.company_name}" /> <br>
                
            </p>
        </div>
        

    </body>
</html>
