<jsp:include page="header.jsp">  
<jsp:param name="titre" value="profil" />  
</jsp:include>

<jsp:include page="navbar.jsp"/>

Pseudo : ${model.utilisateur.pseudo}<br>
Nom : ${model.utilisateur.nom}<br>
Prénom :  ${model.utilisateur.prenom}<br>
Email :  ${model.utilisateur.email}<br>
Téléphone : ${model.utilisateur.telephone}<br>
Rue :  ${model.utilisateur.rue}<br>
Code Postal :  ${model.utilisateur.codePostal}<br>
Ville :  ${model.utilisateur.ville} <br>

<input type="submit" value="Modifier"/>

<jsp:include page="footer.jsp"/> 