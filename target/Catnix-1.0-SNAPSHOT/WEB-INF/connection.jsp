<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Connexion</title>
        <link type="text/css" rel="stylesheet" href="inc/form.css" />
    </head>
    <body>

        <form method="post" action="connection">
            <fieldset>
                <legend>Connection</legend>


                <label for="username">Username <span class="required">*</span></label>
                <input type="text" id="username" name="username" value="" size="20" maxlength="60" />
                <span class="error">${form.errors['username']}</span><br/>


                <label for="password">Password <span class="required">*</span></label>
                <input type="password" id="password" name="password" value="" size="20" maxlength="20" />
                <span class="error">${form.errors['password']}</span><br/>

                <input type="submit" value="Login" class="sansLabel" />


                <p class="${empty form.errors ? 'success' : 'error'}">${form.result}</p>

            </fieldset>
        </form>
    </body>
</html>