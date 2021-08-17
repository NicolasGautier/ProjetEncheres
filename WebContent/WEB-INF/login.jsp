<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<c:forEach var="key" items="${errModel.err}">
		<c:if test="${key == 'ErrLog'}">
		<p style="color:red">${errModel.errMessage.get('ErrLog')}</p>
		</c:if>	
	</c:forEach>
	<form action="LoginServlet" method="post">
		<p>Identifiant: <input type="text" name="identifiant" required="required"/></p>
		<p>Mot de passe: <input type="password" name="password" required="required"/></p>
		<button type="submit" name="formulaireLogin" value="Connexion">Connexion</button>
		<input type="checkbox" name="checkRememberMe" value="true"/>
		<a href="#">Mot de passe oublié</a>
	</form>
	
	<a href="#">Créer un compte</a>
</body>
</html>