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
        <title>Index CP</title>
        <link type="text/css" rel="stylesheet" href="inc/form.css" />
    </head>
    <div id="index">
        <p><a href="<c:url value="/addTender.jsp"/>">Add a tender</a></p>
        <p><a href="<c:url value="/consultMyTender.jsp"/>">Consult my tender</a></p>
        <p><a href="<c:url value="/positionOnTender.jsp"/>">Position on a tender</a></p>
    </div>
</html>
