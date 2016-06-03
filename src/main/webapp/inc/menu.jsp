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
        <title>Catnix</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet"/>


        <script src="https://code.jquery.com/jquery-1.11.2.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
    </head>
    <body>

        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">Catnix</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="<c:url value="/index"/>">Accueil</a></li>
                    <li><a href="<c:url value="/connection"/>">Déconnexion</a></li>
                </ul>
            </div>
        </nav>

                <p class="requiredFieldText">Champs obligatoires : <span style="color: #c00">*</span></p>

    </body>
</html>
