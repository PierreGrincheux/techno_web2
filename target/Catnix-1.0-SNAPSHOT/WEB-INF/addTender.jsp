<%-- 
    Document   : addTender
    Created on : 23 avr. 2016, 17:49:07
    Author     : Fritsch
--%>

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

        <div class ="container">
            <form action = "addTender" method="post" class="form-horizontal" role=form">

                
                    <legend>Form Tender</legend>
                    <div class="form-group">
                        <label for="title" class="control-label col-sm-2">Title :  <span class="required">*</span></label>
                     
                        <div class="col-sm-8">
                            <input type="text" id="title" name="title" size="20" maxlength="25" class="form-control" />  
                        </div>
                        <span class="error">${formTender.errors['title']}</span>
                        <br/> 
                    </div>
                    <div class="form-group">
                        <label for="activityArea" class="control-label col-sm-2">Activity Area :   <span class="required">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="activityArea" name="activityArea"  size="20" maxlength="25" class="form-control"/> 
                        </div>
                        <span class="error">${formTender.errors['activityArea']}</span><span></span>
                        <br /> 
                    </div>
                        
                    <div class="form-group">
                        <label for="contactName" class="control-label col-sm-2">Contact Name :   <span class="required">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="contactName" name="contactName"  size="20" maxlength="25" class="form-control" /> 
                        </div>
                        <span class="error">${formTender.errors['contactName']}</span>
                        <br /> 
                    </div>
                    
                    <div class="form-group">
                        <label for="contactPhone" class="control-label col-sm-2">Contact Phone :   <span class="required">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="contactPhone" name="contactPhone"  size="20" maxlength="10" class="form-control" />   
                        </div>
                        <span class="error">${formTender.errors['contactPhone']}</span>
                        <br /> 
                    </div>
                    
                    <div class="form-group">
                        <label for="contactEmail" class="control-label col-sm-2">Contact Email :</label>
                        <div class="col-sm-8">
                          <input type="text" id="contactEmail" name="contactEmail"  size="20" maxlength="40" class="form-control" /> 
                        </div>
                        <br /> 
                    </div>
                    
                    <div class="form-group">
                        <label for="origin" class="control-label col-sm-2">Origin :  <span class="required">*</span></label>
                        <div class="col-sm-8">
                           <input type="text" id="origin" name="origin"  size="20" maxlength="25" class="form-control"/> 
                        </div>
                        <span class="error">${formTender.errors['origin']}</span>
                        <br /> 
                    </div>
                    
                    <div class="form-group">
                        <label for="additionalFiles" class="control-label col-sm-2">Additional files :  </label>
                        <div class="col-sm-10">
                            <input type="file" id="additionalFiles" name="additionalFiles" />       
                        </div>
                        <span></span>
                        <br /> <br /> 
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-2">
                             <input type="submit" id="submit" name="submit" value="submit" class="btn btn-default btn-md" /> 

                              <p class="${empty formTender.errors ? 'success' : 'error'}">${formTender.result}</p>
                        <div>
                    </div>
            </form>
        </div>
    </body>
</html>
