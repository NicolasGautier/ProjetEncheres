<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">  
<jsp:param name="titre" value="profil" />  
</jsp:include>

<jsp:include page="navbar.jsp">  
<jsp:param name="hidden" value="true" />  
</jsp:include>

<h2>Mon profil</h2>
		
	<c:forEach var="key" items="${errModel.err}">		
		<c:if test="${key == 'ErrLog'}">
			<c:forEach var="erreur" items="${errModel.errMessages.get('ErrLog')}">
				<p style="color:red">${erreur}</p>
			</c:forEach>	
		</c:if>		
	</c:forEach>
	
	<form action="ModifierProfilServlet" method="POST">
	
		Pseudo : <input type="text" name="pseudo" value="${modProfModel.utilisateur.pseudo}"/><br>
		Prénom : <input type="text" name="prenom" value="${modProfModel.utilisateur.prenom}"/><br>
		Téléphone : <input type="text" name="telephone" value="${modProfModel.utilisateur.telephone}"/><br>
		Code Postal : <input type="text" name="cp" value="${modProfModel.utilisateur.codePostal}"/><br>
		Mot de Passe actuel : <input type="password" name="password" required="required"/><br>
		Nouveau Mot de Passe : <input type="password" name="newPassword"/><br>
		Nom : <input type="text" name="nom" value="${modProfModel.utilisateur.nom}"/><br>
		Email : <input type="email" name="email" value="${modProfModel.utilisateur.email}"/><br>
		Rue : <input type="text" name="rue" value="${modProfModel.utilisateur.rue}"/><br>
		Ville : <input type="text" name="ville" value="${modProfModel.utilisateur.ville}"/><br>
		Confirmation : <input type="password" name="confPassword"/><br>
			
		Crédit : ${modProfModel.utilisateur.credit}
		
		<button type="submit" name="formuaireProfil" value="enregistrer">Enregistrer</button>
		<button id="btnSup" type="submit" name="formuaireProfil" value="supprimer" >Supprimer mon compte</button>
	</form>
	
<script type="text/javascript">
	document.getElementById("btnSup").onclick = function(){
		let retour = confirm("Etes vous sur de vouloir supprimer votre compte?"); // TODO Appeler Emmanuel pour faire la popup.
		if(retour){
			return true;
		} else {
			return false;
		}
	}
</script>

<jsp:include page="footer.jsp"/> 