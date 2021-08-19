<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
	<jsp:param name="titre" value="accueil" />
</jsp:include>

<jsp:include page="navbar.jsp" />

<div class="row mb-3">
	<div class="justify-content-center">
		<h2>
			Liste des enchères<img alt="image enchere"
				src="<%=request.getContextPath()%>/image/image_enchere.png">
		</h2>
	</div>
	<c:forEach var="key" items="${errModel.err}">
		<c:if test="${key == 'ErrAcc'}">
			<p style="color: red">${errModel.errMessage.get('ErrAcc')}</p>
		</c:if>
	</c:forEach>
</div>

<div class="row mb-3">
	<form action="AccueilServlet" method="post">
		<div class="col-3 themed-grid-col">
			<label for="filtre">Filtres :</label> 
			<input type="search" value="" /> 
			<label for="categorie">Catégorie :</label> 
			<select name="categorieSelect">
				<option value="Toutes">Toutes</option>
				<c:forEach var="categorie" items="${accModel.lstCategorie}">
					<option value="${categorie.libelle}" <c:if test="${accModel.categorie.libelle == categorie.libelle}">selected</c:if>>${categorie.libelle}</option>
				</c:forEach>				
			</select>
		</div>
		<div class="col-9 themed-grid-col">
			<input type="submit" value="Rechercher" />
		</div>
	</form>
</div>
<c:if test="${accModel.lstEnchere.size() > 0}">
	<table>
		<thead>
			<tr>
				<th>Image</th>
				<th>Article</th>
				<th>Montant de l'Enchere</th>
				<th>Date Enchere</th>
				<th>Vendeur</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${accModel.lstEnchere}" var="enchere">
				<tr>
					<td><img alt="image neutre"
						src="<%=request.getContextPath()%>/image/image_informatique.png">
					</td>
					<td>${enchere.articleConcerne.nomArticle}</td>
					<td>${enchere.montant_enchere}</td>
					<td>${enchere.dateEnchere}</td>
					<td>${enchere.utilisateurEncherit.pseudo}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>

</body>
</html>