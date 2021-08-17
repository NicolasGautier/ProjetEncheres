<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<form action="UtilisateurServlet" method="post">

		Pseudo : <input type="text" name="Pseudo" value="${model.Utilisateur.Pseudo}"/><br>
		Prénom : <input type="text" name="Prenom" value="${model.Utilisateur.Prenom}"/><br>
		Téléphone : <input type="text" name="Telephone" value="${model.Utilisateur.Telephone}"/><br>
		Code Postal : <input type="text" name="code Postal" value="${model.Utilisateur.CodePostal}"/><br>
		Mot de Passe : <input type="password" name="Mot de passe" value="${model.Utilisateur.MotDePasse}"/><br>
		Nom : <input type="text" name="Nom" value="${model.Utilisateur.Nom}"/><br>
		Email : <input type="email" name="Email" value="${model.Utilisateur.Email}"/><br>
		Rue : <input type="text" name="Rue" value="${model.Utilisateur.Rue}"/><br>
		Ville : <input type="text" name="Ville" value="${model.Utilisateur.Ville}"/><br>
		Confirmation : <input type="password" name="Pseudo" value="${model.Utilisateur.Confirmation}"/><br>
		<input type="submit" value="Créer"/>
		<input type="submit" value="Annuler" />
		
</form>	
		
	<c:forEach items="${model.lstUtilisateur}" var="Utilisateur">
	<p>${utilisateur.Pseudo} ${utilisateur.Prenom}   ${utilisateur.Telephone}    ${utilisateur.CodePostal}  
	   ${utilisateur.MotDePasse}     ${utilisateur.Nom}      ${utilisateur.Email}      ${utilisateur.Rue}      ${utilisateur.Ville}
	    ${utilisateur.Confirmation} </p>
</c:forEach>	
		
</body>
</html>