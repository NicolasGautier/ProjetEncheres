<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insertion Utilisateur</title>
</head>
<body>
<p style="color:red">${erreur}</p>

<h1>ENI-ENCHERES</h1>
<h2>Mon profil</h2>
<c:forEach var="key" items="${errModel.err}">
		<c:if test="${key == 'ErrIns'}">
		<p style="color:red">${errModel.errMessage.get('ErrIns')}</p>
		</c:if>	
	</c:forEach>
<form action="InsererUtilisateurServlet" method="post">

		Pseudo : <input type="text" name="Pseudo" value="${insModel.utilisateur.pseudo}" required="required"/><br>
		Prénom : <input type="text" name="Prenom" value="${insModel.utilisateur.prenom}" required="required"/><br>
		Téléphone : <input type="text" name="Telephone" value="${insModel.utilisateur.telephone}"/><br>
		Code Postal : <input type="text" name="code Postal" value="${insModel.utilisateur.codePostal}" required="required"/><br>
		Mot de Passe : <input type="password" name="Mot de passe" value="${insModel.utilisateur.motDePasse}" required="required"/><br>
		Nom : <input type="text" name="Nom" value="${insModel.utilisateur.nom}" required="required"/><br>
		Email : <input type="email" name="Email" value="${insModel.utilisateur.email}" required="required"/><br>
		Rue : <input type="text" name="Rue" value="${insModel.utilisateur.rue}" required="required"/><br>
		Ville : <input type="text" name="Ville" value="${insModel.utilisateur.ville}" required="required"/><br>
<%-- 		Confirmation : <input type="password" name="Pseudo" value="${model.utilisateur.confirmation}"/><br> --%>
		<input type="submit" value="Créer"/>
		<input type="submit" value="Annuler" />
		
</form>	
				
</body>
</html>