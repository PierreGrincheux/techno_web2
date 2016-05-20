<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Connexion</title>
        <link type="text/css" rel="stylesheet" href="inc/form.css" />
         <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
        <form method="post" action="connection" class="form-signin">
                <h2 class="form-signin-heading">Connection</h2>

                <label for="username" >Username <span class="required">*</span> </label>
                <input type="text" class ="form-control"placeholder="Username"id="username" name="username" value="" size="20" maxlength="60" />
                <span class="error">${form.errors['username']}</span><br/>


                <label for="password">Password <span class="required">*</span></label>
                <input type="password"class ="form-control"placeholder="Password" id="password" name="password" value="" size="20" maxlength="20" />
                <span class="error">${form.errors['password']}</span><br/>

                <input class="btn btn-lg btn-default btn-block" type="submit" value="Login" class="sansLabel" />


                <p class="${empty form.errors ? 'success' : 'error'}">${form.result}</p>

        </form>
        </div>
    </body>
</html>
