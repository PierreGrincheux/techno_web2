<%-- 
    Document   : positionOnTender
    Created on : 3 mai 2016, 18:22:27
    Author     : Fritsch
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Position on Tender</title>
        <link type="text/css" rel="stylesheet" href="<c:url value ="inc/form.css" />" />
    </head>
    <body>
        
        <c:import url="/inc/menu.jsp" />

        <form method="post" action="positionOnTender">
            <fieldset>
                <legend>Form</legend>


                <label for="title">Title : </label>
                <input type="text" id="title" name="title" value="<c:out value="${targetedTender.title}"/>" size="20" maxlength="60" />
                 <span class="error">${positionOnTenderForm.errors['title']}</span><br/>
                <br/>

                <label for="nameCP">Name CP : </label>
                <input type="text" id="nameCP" name="nameCP" value="<c:out value="${member.lastname}"/> <c:out value="${member.firstname}"/>" size="20" maxlength="60" />
                <span class="error">${positionOnTenderForm.errors['nameCP']}</span><br/>
                <br/><br/>

                <label for="motivationText">Motivation Text : </label>
                <textarea id="motivationText" name="motivationText" ></textarea>
                 <span class="error">${positionOnTenderForm.errors['motivationText']}</span><br/>
                <br/>

                <input type="submit" value="Submit" class="sansLabel" />


                <p class="${empty positionOnTenderForm.errors ? 'success' : 'error'}">${positionOnTenderForm.result}</p>

            </fieldset>
        </form>
    </body>
</html>
