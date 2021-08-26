<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
	<jsp:param name="titre" value="detailvente" />
</jsp:include>

<jsp:include page="navbar.jsp">
	<jsp:param name="hidden" value="true" />
</jsp:include>




<div class="row">
	<div class="col-5"></div>
	<c:forEach var="key" items="${errModel.err}">
		<c:if test="${key == 'ErrIns'}">
			<c:forEach var="erreur" items="${errModel.errMessages.get('ErrIns')}">
				<p style="color: red">${erreur}</p>
			</c:forEach>
		</c:if>
		<c:if test="${key == 'ErrInit'}">
			<c:forEach var="erreur"
				items="${errModel.errMessages.get('ErrInit')}">
				<p style="color: red">${erreur}</p>
			</c:forEach>
		</c:if>
	</c:forEach>
	<div class="col-3"></div>
	<div class="col-4"></div>
</div>

<h2>Détail vente</h2>
<h2>${detailVente.articleVendu.nomArticle}</h2>

<div class="container-fluid">
	<div class="row">
		<div class="col-4">
			<img class="card-img-left" alt="image neutre"
				src="<%=request.getContextPath()%>/image/${detailVente.articleVendu.image}">
		</div>

		<div class="col-8">
			<div class="card" style="width: 40rem">
				<div class="divvide"></div>
				<div class="divvide"></div>

				<div class="row">
					<div class="col-2"></div>
					<div class="col-3">Description</div>
					<div class="col-2"></div>
					<div class="col-3">${detailVente.articleVendu.description}</div>
					<div class="col-2"></div>
				</div>

				<div class="divvide"></div>
				<div class="divvide"></div>

				<div class="row">
					<div class="col-2"></div>
					<div class="col-3">Catégorie</div>
					<div class="col-2"></div>
					<div class="col-3">${detailVente.articleVendu.categorie.libelle}</div>
					<div class="col-2"></div>
				</div>

				<div class="divvide"></div>
				<div class="divvide"></div>

				<div class="row">
					<div class="col-2"></div>
					<div class="col-3">Meilleure offre :</div>
					<div class="col-2"></div>
					<div class="col-3">${detailVente.articleVendu.prixVente}points
						par ${detailVente.meilleurEnchere.utilisateurEncherit.pseudo}</div>
					<div class="col-2"></div>
				</div>

				<div class="divvide"></div>
				<div class="divvide"></div>

				<div class="row">
					<div class="col-2"></div>
					<div class="col-3">Mise à prix</div>
					<div class="col-2"></div>
					<div class="col-3">${detailVente.articleVendu.miseAPrix}</div>
					<div class="col-2"></div>
				</div>

				<div class="divvide"></div>
				<div class="divvide"></div>

				<div class="row">
					<div class="col-2"></div>
					<div class="col-3">Fin de l'enchère</div>
					<div class="col-2"></div>
					<div class="col-3">${detailVente.articleVendu.dateFinEncheresFormat}</div>
					<div class="col-2"></div>
				</div>

				<div class="divvide"></div>
				<div class="divvide"></div>

				<div class="row">
					<div class="col-2"></div>
					<div class="col-3">Retrait</div>
					<div class="col-2"></div>
					<div class="col-3">${detailVente.retrait.rue}</div>
					<div class="col-2"></div>
				</div>

				<div class="divvide"></div>
				<div class="divvide"></div>

				<div class="row">
					<div class="col-2"></div>
					<div class="col-3"></div>
					<div class="col-2"></div>
					<div class="col-3">${detailVente.retrait.code_postal}
						${detailVente.retrait.ville}</div>
					<div class="col-2"></div>
				</div>

				<div class="divvide"></div>
				<div class="divvide"></div>

				<div class="row">
					<div class="col-2"></div>
					<div class="col-3">Vendeur</div>
					<div class="col-2"></div>
					<div class="col-3">${detailVente.articleVendu.utilisateurVend.pseudo}</div>
					<div class="col-2"></div>
				</div>

				<div class="divvide"></div>
				<div class="divvide"></div>
			</div>
		</div>
	</div>

	<c:if
		test="${detailVente.articleVendu.utilisateurVend.noUtilisateur != logModel.utilisateur.noUtilisateur}">
		<div class="divvide"></div>
		<div class="divvide"></div>
		<div class="row">
			<div class="divvide"></div>
			<div class="col-4"></div>
			<div class="col-4">
				<div class="card" style="width: 50rem">
					<div class="divvide"></div>
					<form
						action="DetailVenteServlet?id=${detailVente.articleVendu.noArticle}"
						method="post">

						<div class="row">
							<div class="col-1"></div>
							<div class="col-2">Ma proposition</div>
							<div class="col-1"></div>
							<div class="col-3">
								<input type="number" id="proposition" name="proposition"
									min="${detailVente.articleVendu.prixVente + 1}">
							</div>
							<div class="col-2"></div>
							
								<button class="btn-secondary btn-lg" type="submit" id="encherir"
									name="formulaireEncherir" value="encherir">Enchérir</button>
							
							<div class="col-1"></div>
						</div>
					</form>
					<div class="divvide"></div>
				</div>
			</div>
			<div class="col-4"></div>
		</div>
	</c:if>
</div>




<jsp:include page="footer.jsp" />
