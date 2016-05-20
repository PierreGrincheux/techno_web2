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
        <link type="text/css" rel="stylesheet"  href="<c:url value="inc/form.css" />" />
    </head>
    <body>
        
        <c:import url="/inc/menu.jsp" />
        
        <div class ="container">
        <form action = "addProspect" method="post" class="form-horizontal" role=form">
        
            <legend>Ajouter un nouveau prospect</legend>
            
            <div class="form-group">
                <label for="company_name" class="control-label col-sm-2">Nom de l'entreprise :  <span class="required">*</span></label>

                <div class="col-sm-8">
                    <input type="text" id="company_name" name="company_name" size="20" maxlength="25" class="form-control" />  
                </div>
                <span class="error">${formprospect.errors['company_name']}</span>
                <br/> 
            </div>
                
            <div class="form-group">
                <label for="activity_area" class="control-label col-sm-2">Secteur d'activité :  <span class="required">*</span></label>

                <div class="col-sm-8">
                    <input type="text" id="activity_area" name="activity_area" size="20" maxlength="25" class="form-control" />  
                </div>
                <span class="error">${formprospect.errors['activity_area']}</span>
                <br/> 
            </div>
              
            <div class="form-group">
                <label for="website" class="control-label col-sm-2">Adresse du site internet :  <span class="required">*</span></label>

                <div class="col-sm-8">
                    <input type="text" id="website" name="website" size="20" maxlength="20" class="form-control" />  
                </div>
                <span class="error">${formprospect.errors['website']}</span>
                <br/> 
            </div>

            <div class="form-group">
                <label for="phone_number" class="control-label col-sm-2">Numéro de téléphone :  <span class="required">*</span></label>

                <div class="col-sm-8">
                    <input type="text" id="phone_number" name="phone_number" size="20" maxlength="25" class="form-control" />  
                </div>
                <span class="error">${formprospect.errors['phone_number']}</span>
                <br/> 
            </div>
                
            <div class="form-group">
                <label for="email" class="control-label col-sm-2">Adresse email :  <span class="required">*</span></label>

                <div class="col-sm-8">
                    <input type="text" id="email" name="email" size="20" maxlength="25" class="form-control" />  
                </div>
                <span class="error">${formprospect.errors['email']}</span>
                <br/> 
            </div>                 
                          
           <div class="form-group">
                <label for="contact_name" class="control-label col-sm-2">Nom du contact :  <span class="required">*</span></label>

                <div class="col-sm-8">
                    <input type="text" id="contact_name" name="contact_name" size="20" maxlength="25" class="form-control" />  
                </div>
                <span class="error">${formprospect.errors['contact_name']}</span>
                <br/> 
            </div>    

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-2">
                     <input type="submit" id="submit" name="submit" value="Ajouter" class="btn btn-default btn-md" /> 

                      <p class="${empty formprospect.errors ? 'success' : 'error'}">${formprospect.result}</p>
                <div>
            </div>
            </form>
        </div>
    </body>
</html>

