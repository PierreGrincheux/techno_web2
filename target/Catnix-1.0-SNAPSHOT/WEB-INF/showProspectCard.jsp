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
        <title>JSP Page</title>
    </head>
    <body>
        <c:set var="prospect" scope="session" value="${sessionScope.prospect}" />
        <h1><c:out value="${prospect.company_name}" /></h1>
        
        <form action = "udpadeProspect" method="post">
            <div>
                <h2>Informations </h2>
                <label for="activity_area"> Secteur d'activité</label>
                <input type="text" id="activity_area" name="activity_area" value="<c:out value='${prospect.activity_area}' />" /><br>

                <label for="activity_area"> Site internet </label>
                <input type="text" id="website" name="website" value="<c:out value='${prospect.website}' />" /><br>

                <label for="activity_area"> N° de téléphone </label>
                <input type="text" id="phone_number" name="phone_number" value="<c:out value='${prospect.phone_number1}' />" /><br>

                <label for="activity_area"> Adresse email </label>
                <input type="text" id="email" name="email" value="<c:out value='${prospect.email}' />" /><br>

                <label for="activity_area"> Contact </label>
                <input type="text" id="contact" name="contact" value="<c:out value='${prospect.contact_name}' />" /><br>

            </div>
            <div>
                <h2>Appel</h2>           
                    <label for="comment">Commentaires </label>
                    <input type="text" id="comment" name="comment"/>  
                    <br/> 

                    <label for="call_outcome">Résultat de l'appel <span class="required">*</span></label>
                    <select form="updateProspect"> 
                        <!--form ne fonctionne pas sur IE-->
                        <option value="success">Rendez-vous pris</option>

                        <option value="to_callback"> A rappeler</option>
                        <option value="voice_mail"> Echec de l'appel</option>
                        <option value="not_interested"> Non interessé (le prospect sera supprimé) </option>
                    </select>
                     <br /> 
                    <!--mettre une fonction javascript pour voir l'input inuquement quand la valeur de la liste est "a rappeler"-->
                    <label for="callback_date">Date de rappel  </label>
                    <input type="date" id="callback_date" name="callback_date"/>
                     <br /> 

                    <input type="submit" id="submit" name="submit" value="sauvegarder" /> 
            </div>
        </form>
        <div>
            <button>Envoyer une plaquette</button>
        </div>

    </body>
</html>
