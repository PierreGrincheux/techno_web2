<%-- 
    Document   : addProspectCSV
    Created on : 31 mai 2016, 14:04:25
    Author     : mélanie
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

        <section class ="container">
        <h1>Ajouter des prospects via un ficher CSV</h1>
        
        <h1>${message}</h1>
        
        <div>
            ! Important :
            chaque ligne du fichier CSV doit avoir la forme suivante :<br>
            "nom de l'entreprise","secteur d'activité","adresse du site internet","adresse email","mon du contact", "numéro de téléphone"<br>
            les champs ne divent pas contenir de guillemets (")<br>
            si un champ n'est pas connu, le laisser vide Ex: "nom de l'entreprise",,"adresse du site internet" <br> <br>
            <!--à remplace par un screenshot-->
        </div>
        <form action = "addProspectCSV" method="post" enctype="multipart/form-data">
            <label for="csvfile">choisissez votre fichier : </label>
            <input type="file" name="csvfile" id="csvfile" accept=".csv"/> <br>
            <input type="submit" id="submit" name="submit" value="Ajouter les prospects" /> 
            
        </form>
        </section>
           
    </body>
</html>
