<%-- 
    Document   : menu
    Created on : 27 avr. 2016, 11:34:21
    Author     : Fritsch
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
        <fieldset>
            <p><a href="<c:url value="/index"/>">Index Coucou à tous</a></p>
            <p><a href="<c:url value="/disconnection"/>">Sign out</a></p>
        </fieldset>
         <br/>
    </body>
</html>
