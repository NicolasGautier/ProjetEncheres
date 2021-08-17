<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<form action="LoginServlet" method="post">
		<p>Identifiant: <input type="text" name="identifant" required="required"/></p>
		<p>Mot de passe: <input type="password" name="password" required="required"/></p>
		<button type="submit" name="formulaireLogin" value="Connexion">Connexion</button>
		<input type="checkbox" name="checkRememberMe"/>
		<a href="#">Mot de passe oublié</a>
	</form>
	
	<a href="#">Créer un compte</a>
</body>
</html>