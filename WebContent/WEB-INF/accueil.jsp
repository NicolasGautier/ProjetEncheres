<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
	<jsp:param name="titre" value="accueil" />
</jsp:include>

<jsp:include page="navbar.jsp" />

<div class="container-fluid">
	<div class="row">
		<div class="col-12">
			<h2>Liste des enchères</h2>
		</div>

	</div>
</div>
<c:forEach var="key" items="${errModel.err}">
	<c:if test="${key == 'ErrAcc'}">
		<c:forEach var="erreur" items="${errModel. errMessages.get('ErrAcc')}">
			<p style="color: red">${erreur}</p>
		</c:forEach>
	</c:if>
</c:forEach>


<form class="container" action="AccueilServlet" method="post">
	<div class="row align-items-center">
		<div class="col-md-3">
			<p>
				<label for="filtre">Filtres :</label> <input type="search"
					name="filtre" value="${accModel.filtre}"
					placeholder="Le nom de l'article contient " />
			</p>

			<p>
				<label for="categorieSelect">Catégorie :</label> <select
					name="categorieSelect">
					<option value="Toutes">Toutes</option>

					<c:forEach var="categorie" items="${accModel.lstCategorie}">
						<option value="${categorie.noCategorie}"
							<c:if test="${accModel.categorie.noCategorie == categorie.noCategorie}">selected
					</c:if>>${categorie.libelle}</option>
					</c:forEach>
				</select>
			</p>
		</div>

		<div class="col-md-3 ml-md-auto">
			<button type="submit" value="Rechercher"
				class="btn-secondary btn-lg btn-block">Rechercher</button>
		</div>
	</div>


	<c:if test="${!empty logModel.utilisateur.noUtilisateur}">
		<jsp:include page="achatsMesVentesMenu.jsp" />
	</c:if>


</form>




<c:if test="${accModel.lstArticleVendu.size() > 0}">

	<br>
	<div class="card-deck">
		<c:forEach items="${accModel.lstArticleVendu}" var="articleVendu">
			<div class="card" style="max-width: 25rem;">
				<div class="row align-items-center">
					<div class="col-6">
						<img class="card-img-left" alt="image neutre"
							src="<%=request.getContextPath()%>/image/image_informatique.png">
					</div>
					<div class="col-6">
						<div class="card-body">
							<h5 class="card-title">
								<a
									href="<c:if test="${articleVendu.etatVente != accModel.etatsVenteEncheresTerminees}">DetailVenteServlet</c:if>
									<c:if test="${articleVendu.etatVente == accModel.etatsVenteEncheresTerminees}">VenteRemporteServlet</c:if>?id=${articleVendu.noArticle}">${articleVendu.nomArticle}</a>
							</h5>
							<p class="card-text">${articleVendu.prixVente}points</p>
							<p class="card-text">${articleVendu.dateFinEncheres}</p>
							<p class="card-text">
								<a
									href="ProfilServlet?id=${articleVendu.utilisateurVend.noUtilisateur}">${articleVendu.utilisateurVend.pseudo}</a>
							</p>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>

</c:if>

</body>
</html>