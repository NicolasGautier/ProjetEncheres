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
			<p style="color: red">${erreur}</p>
		</c:forEach>
	</c:if>
</c:forEach>

<form action="ModifierProfilServlet" method="post">

	<div class="container-fluid">
		<div class="row">
			<div class="col-1"></div>
			<div class="col-2">Pseudo :</div>
			<div class="col-2">
				<input type="text" name="pseudo"
					value="${modProfModel.utilisateur.pseudo}" required="required" />
			</div>
			<div class="col-1"></div>
			<div class="col-2">Nom :</div>
			<div class="col-2">
				<input type="text" name="nom"
					value="${modProfModel.utilisateur.nom}" required="required" />
			</div>
			<div class="col-1"></div>
		</div>

	<div class="divvide"></div>


		<div class="row">
			<div class="col-1"></div>
			<div class="col-2">Prenom :</div>
			<div class="col-2">
				<input type="text" name="prenom"
					value="${modProfModel.utilisateur.prenom}" required="required" />
			</div>
			<div class="col-1"></div>
			<div class="col-2">Email :</div>
			<div class="col-2">
				<input type="text" name="email"
					value="${modProfModel.utilisateur.email}" required="required" />
			</div>
			<div class="col-1"></div>
		</div>

		<div class="divvide"></div>

		<div class="row">
			<div class="col-1"></div>
			<div class="col-2">Téléphone :</div>
			<div class="col-2">
				<input type="text" name="telephone"
					value="${modProfModel.utilisateur.telephone}" required="required" />
			</div>
			<div class="col-1"></div>
			<div class="col-2">Rue :</div>
			<div class="col-2">
				<input type="text" name="rue"
					value="${modProfModel.utilisateur.rue}" required="required" />
			</div>
			<div class="col-1"></div>
		</div>

		<div class="divvide"></div>

		<div class="row">
			<div class="col-1"></div>
			<div class="col-2">Code Postal :</div>
			<div class="col-2">
				<input type="text" name="cp"
					value="${modProfModel.utilisateur.codePostal}" required="required" />
			</div>
			<div class="col-1"></div>
			<div class="col-2">Ville :</div>
			<div class="col-2">
				<input type="text" name="ville"
					value="${modProfModel.utilisateur.ville}" required="required" />
			</div>
			<div class="col-1"></div>
		</div>

		<div class="divvide"></div>

		<div class="row">
			<div class="col-1"></div>
			<div class="col-2">Mot de Passe actuel:</div>
			<div class="col-2">
				<input type="password" name="password" required="required" />
			</div>
			<div class="col-7"></div>
		</div>

		<div class="divvide"></div>


		<div class="row">
			<div class="col-1"></div>
			<div class="col-2">Nouveau mot de passe :</div>
			<div class="col-2">
				<input type="password" name="newPassword" />
			</div>
			<div class="col-1"></div>
			<div class="col-2">Confirmation:</div>
			<div class="col-2">
				<input type="password" name="confPassword" />
			</div>
			<div class="col-1"></div>
		</div>


		<div class="divvide"></div>

		<div class="row">
			<div class="col-1"></div>
			<div class="col-2">
				<h5>Crédit : ${modProfModel.utilisateur.credit}</h5>
			</div>
			<div class="col-9"></div>
		</div>



		<div class="row">
			<div class="col-3"></div>
			<div class="col-3">
				<button class="btn-secondary btn-lg" type="submit"
					name="formulaireProfil" value="enregistrer">Enregistrer</button>


			</div>
			<div class="col-3">
				<button class="btn-secondary btn-lg" id="btnSup" type="submit"
					name="formulaireProfil" value="supprimer">Supprimer mon
					compte</button>
			</div>
			<div class="col-3"></div>
		</div>
	</div>

</form>






<script type="text/javascript">
	document.getElementById("btnSup").onclick = function() {
		let retour = confirm("Etes vous sur de vouloir supprimer votre compte?"); // TODO Appeler Emmanuel pour faire la popup.
		if (retour) {
			return true;
		} else {
			return false;
		}
	}
</script>





<jsp:include page="footer.jsp" />
