<%-- 
    Document   : addProspects
    Created on : 26 avr. 2016, 17:05:45
    Author     : melanie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Prospects Page</title>
        <link type="text/css" rel="stylesheet"  href="inc/form.css">
    </head>
    <body>
        <fieldset>
            <legend>Ajouter un nouveau prospect</legend>

            <form action = "addProspect" method="post">
                <label for="company_name">Nom de l'entreprise : <span class="required">*</span></label>
                <input type="text" id="company_name" name="company_name"/>  
                <span class="error">${formprospect.errors['company_name']}</span><br/>
                <br/> 

                <label for="activity_area">Secteur d'activité : <span class="required">*</span></label>
                <input type="text" id="activity_area" name="activity_area"/>
                <span class="error">${formprospect.errors['activity_area']}</span><br/>
                 <br /> 
                
                <label for="website">Adresse du site internet : </label>
                <input type="text" id="website" name="website"/>
                <span class="error">${formprospect.errors['website']}</span><br/>
                 <br /> 
                
                <label for="phone_number">Numéro de téléphone : <span class="required">*</span></label>
                <input type="text" id="phone_number" name="phone_number"/> 
                <span class="error">${formprospect.errors['phone_number']}</span><br/>
                 <br />                
                          
                <label for="email"> Adresse email : </label>
                <input type="text" id="email" name="email"/> 
                <span class="error">${formprospect.errors['email']}</span><br/>
                 <br />                
                
                <label for="contact_name">Nom du contact : </label>
                <input type="text" id="contact_name" name="contact_name"/> 
                <span class="error">${formprospect.errors['contact_name']}</span><br/>
                 <br />                
                 
                <input type="submit" id="submit" name="submit" value="Ajouter" /> 
                
                <p class="${empty formprospect.errors ? 'success' : 'error'}"> ${formprospect.result}</p>

            </form>
        </fieldset>
    </body>
</html>

