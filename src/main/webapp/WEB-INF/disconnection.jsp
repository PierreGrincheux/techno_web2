<%-- 
    Document   : home
    Created on : 23 avr. 2016, 16:37:27
    Author     : Fritsch
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Disconnection</title>
        <link type="text/css" rel="stylesheet"  href="<c:url value="/inc/form.css"/>" />
    </head>
    <body>
        <h1>Disconnection Page</h1>

        <c:import url="/inc/menu.jsp" />

        <form action="disconnection" method="post">
            <input type="submit" value="Logout" class="btn" />
        </form>

    </body>
</html>
