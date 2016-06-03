
<%-- 
    Document   : showProspectCard
    Created on : 27 avr. 2016, 19:58:52
    Author     : melanie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Catnix</title>
    </head>
    <body>
        <c:import url="/inc/menu.jsp" />
        <div class="container">

        <c:set var="prospect" scope="session" value="${sessionScope.prospect}" />
        <h1><c:out value="${prospect.company_name}" /></h1>

        <form action = "ShowProspectCard" method="post">
            <div>
                <h2>Informations </h2>
                <label for="activity_area"> Secteur d'activité</label>
                <input type="text" id="activity_area" name="activity_area" value="<c:out value='${prospect.activity_area}' />" /><br>
                <span class="error">${formprospect.errors['activity_area']}</span><br>
                
                <label for="website"> Site internet </label>
                <input type="text" id="website" name="website" value="<c:out value='${prospect.website}' />" /><br>
                
                
                <label for="phone_number"> N° de téléphone </label>
                <input type="text" id="phone_number" name="phone_number" value="<c:out value='${prospect.phone_number}' />" /><br>
                 <span class="error">${formprospect.errors['phone_number']}</span><br>
                
                <label for="email"> Adresse email </label>
                <input type="text" id="email" name="email" value="<c:out value='${prospect.email}' />" /><br>

                <label for="contact_name"> Contact </label>
                <input type="text" id="contact_name" name="contact_name" value="<c:out value='${prospect.contact_name}' />" /><br>

            </div>
            <div>
                <h2>Appel</h2>           
                <p>
                <h3>Anciens commentaires</h3>
                <c:forEach items="${sessionScope.allcomments}" var="comment">
                    <p><c:out value="${comment.date}" /> | <c:out value="${comment.content}" /></p>                    
                </c:forEach>
                <label for="comment">Ajouter un commentaire </label>
                <input type="text" id="content" name="content"/>  
                <br/>    
                <label for="state">Résultat de l'appel <span class="required">*</span></label>
                <br>
                <input type="radio" name="state" value="Rendez-vous pris"> Rendez-vous pris<br>
                <input type="radio" name="state" value="A rappeler"> A rappeler / Echec de l'appel <br>
                <input type="radio" name="state" value="Non interessé"> Non interessé (le prospect sera supprimé)<br>
                
                <!--mettre une fonction javascript pour voir l'input uniquement quand la valeur est "a rappeler / echec de l'appel"-->
                <label for="callback_date">Date de rappel (format : yyyy-mm-dd) </label>
                <input type="date" id="callback_date" name="callback_date" value="<c:out value='${prospect.callback_date}' />" />
                <input type="hidden" id="prospectid" name="prospectid" value="<c:out value='${prospect.id}'/>"/>
                <input type="hidden" id="memberid" name="memberid" value="<c:out value='${member.id}'/>"/>
                <br/> 
                <input type="submit" id="submit" name="submit" value="sauvegarder les modifications" />
                </a>

            </div>
        </form>
        <div>
            <button>Envoyer une plaquette</button>
        </div>
        </div>

    </body>
</html>
