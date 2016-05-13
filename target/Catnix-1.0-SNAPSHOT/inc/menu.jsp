<%-- 
    Document   : menu
    Created on : 27 avr. 2016, 11:34:21
    Author     : Fritsch
--%>

//pouet!!!!

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link> <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet">
        

<script src="https://code.jquery.com/jquery-1.11.2.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script> </link>
    </head>
    <body>

<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Catnix</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="<c:url value="/index"/>">Accueil</a></li>
      <li class="dropdown">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">Appel d'offre<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="<c:url value="/addTender"/>">Add a tender</a>


            <c:if test="${member.role == 'RC'}">

            <li><a href="<c:url value="/handleTender"/>">Handle a tender</a></li>
            <li><a href="<c:url value="/consultTendersToAssign"/>">Assign CP to a tender</a></li>

            </c:if>    
            <c:if test="${member.role == 'CP'}">
                <li><a href="<c:url value="/consultTenders"/>">Consult tenders</a></li>
                <li><a href="<c:url value="/consultMyTenders"/>">Consult my tenders</a></li>
            </c:if>
                         
                        </ul>
                    </li>
      <li><a href="<c:url value="/disconnection"/>">Déconnexion</a></li>
    </ul>
  </div>
</nav>
  
<!--<div class="container">
  <h3>Basic Navbar Example</h3>
  <p>A navigation bar is a navigation header that is placed at the top of the page.</p>
</div> -->

         <br/>
    </body>
</html>
