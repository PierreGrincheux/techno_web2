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
        <c:set var = "prospect" scope = "session" value = "${sessionScope.prospect}"/>
        
        <h1><c:out value="${prospect.company_name}" /></h1>
        <div>
            <h2>informations nécéssaires pour contacter le prospect</h2>
            <p>
                Secteur d'activité : <c:out value="${sessionScope.prospect.activity_area}" /> <br>
                Site internet : <c:out value="${sessionScope.prospect.website}" /> <br>
                N° de téléphone : <c:out value="${sessionScope.prospect.phone_number1}" /> <br>
                Adresse email : <c:out value="${sessionScope.prospect.email}" /> <br>
                Contact : <c:out value="${sessionScope.prospect.contact_name}" /> <br>

            </p>
        </div>


    </body>
</html>
