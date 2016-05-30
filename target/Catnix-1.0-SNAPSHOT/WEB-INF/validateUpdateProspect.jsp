<%-- 
    Document   : validateUpdateProspect
    Created on : 17 mai 2016, 18:10:23
    Author     : mélanie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Catnix</title>
    </head>
    <body>
        <h1>prospectipated</h1>
        <p class="${empty formprospect.errors ? 'success' : 'error'}">${formprospect.result}</p>
    </body>
</html>
