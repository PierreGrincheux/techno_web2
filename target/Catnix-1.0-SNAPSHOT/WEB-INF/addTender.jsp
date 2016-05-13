<%-- 
    Document   : addTender
    Created on : 23 avr. 2016, 17:49:07
    Author     : Fritsch
--%>
//test
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Add Tender Page</title>
        <link type="text/css" rel="stylesheet"  href="<c:url value="inc/form.css" />" />
    </head>
    <body>

        <c:import url="/inc/menu.jsp" />


        <form action = "addTender" method="post">

            <fieldset>
                <legend>Form Tender</legend>


                <label for="title">Title :  <span class="required">*</span></label>
                <input type="text" id="title" name="title" size="20" maxlength="25" />  
                <span class="error">${formTender.errors['title']}</span>
                <br/> 

                <label for="activityArea">Activity Area :   <span class="required">*</span></label>
                <input type="text" id="activityArea" name="activityArea"  size="20" maxlength="25" /> 
                <span class="error">${formTender.errors['activityArea']}</span><span></span>
                <br /> 

                <label for="contactName">Contact Name :   <span class="required">*</span></label>
                <input type="text" id="contactName" name="contactName"  size="20" maxlength="25" /> 
                <span class="error">${formTender.errors['contactName']}</span>
                <br /> 

                <label for="contactPhone">Contact Phone :   <span class="required">*</span></label>
                <input type="text" id="contactPhone" name="contactPhone"  size="20" maxlength="10" /> 
                <span class="error">${formTender.errors['contactPhone']}</span>
                <br /> 

                <label for="contactEmail">Contact Email :</label>
                <input type="text" id="contactEmail" name="contactEmail"  size="20" maxlength="40" /> 
                <br /> 


                <label for="origin">Origin :  <span class="required">*</span></label>
                <input type="text" id="origin" name="origin"  size="20" maxlength="25" /> 
                <span class="error">${formTender.errors['origin']}</span>
                <br /> 

                <label for="additionalFiles">Additional files :  </label>
                <input type="file" id="additionalFiles" name="additionalFiles" /> 
                <span></span>
                <br /> 

                <input type="submit" id="submit" name="submit" value="submit" /> 

                <p class="${empty formTender.errors ? 'success' : 'error'}">${formTender.result}</p>

            </fieldset>
        </form>
    </body>
</html>
