<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AfficherProfils</title>
</head>
<body>
<p style="color:red">${erreur}</p>
<h1>ENI-ENCHERES</h1>
<h2>Mon profil</h2>
	<form action="ModifierProfilServlet" method="POST">
	
		Pseudo : <input type="text" name="Pseudo" value="${model.utilisateur.pseudo}"/><br>
		Prénom : <input type="text" name="Prenom" value="${model.utilisateur.prenom}"/><br>
		Téléphone : <input type="text" name="Telephone" value="${model.utilisateur.telephone}"/><br>
		Code Postal : <input type="text" name="code Postal" value="${model.utilisateur.codePostal}"/><br>
		Nouveau Mot de Passe : <input type="password" name="Nouveau mot de passe" value="${model.utilisateur.motDePasse}"/><br>
		<%-- Mot de Passe actuel : <input type="password" name="Mot de passe actuel" value="${model.utilisateur.motDePasseActuel}"/><br>--%>
		Nom : <input type="text" name="Nom" value="${model.utilisateur.nom}"/><br>
		Email : <input type="email" name="Email" value="${model.utilisateur.email}"/><br>
		Rue : <input type="text" name="Rue" value="${model.utilisateur.rue}"/><br>
		Ville : <input type="text" name="Ville" value="${model.utilisateur.ville}"/><br>
		<%--Confirmation : <input type="password" name="Pseudo" value="${model.utilisateur.confirmation}"/><br> --%>
			
		<%--Crédit : ${model.modifierProfil}--%>
		
		<input type="submit" value="Enregistrer"/>
		<input type="submit" value="Supprimer mon compte" />
	</form>
</body>
</html>