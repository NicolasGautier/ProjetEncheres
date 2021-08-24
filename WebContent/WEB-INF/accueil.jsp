<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
	<jsp:param name="titre" value="accueil" />
</jsp:include>

<jsp:include page="navbar.jsp" />

<div class="row mb-3">
	<div class="justify-content-center text-center">
		<h2>Liste des enchères</h2>
		
		
		
<!-- 		<img alt="image enchere" -->
<%-- 		src="<%=request.getContextPath()%>/image/image_enchere.png"> --%>
						
			
	</div>
	
	<c:forEach var="key" items="${errModel.err}">		
		<c:if test="${key == 'ErrAcc'}">
			<c:forEach var="erreur" items="${errModel.errMessages.get('ErrAcc')}">
				<p style="color:red">${erreur}</p>
			</c:forEach>	
		</c:if>		
	</c:forEach>
	
</div>

<form class="row g-3" action="AccueilServlet" method = "post">
  <div class="col-md-3"><label for="filtre">Filtres :</label> </div>
  <div class="col-md-3"></div>
  <div class="col-md-3"></div>				  			
  <div class="col-md-3"></div>
 		 
  <div class="col-md-3"><input type="search" name="filtre" value="${accModel.filtre}"placeholder="Le nom de l'article contient " /></div>
  <div class="col-md-3"></div>
  <div class="col-md-3"></div>
  <div class="col-md-3"></div>
  		
  <div class="col-md-3"><label for="categorieSelect">Catégorie :</label>
  		 <select
			name="categorieSelect">
			<option value="Toutes">Toutes</option>
			<c:forEach var="categorie" items="${accModel.lstCategorie}">
				<option value="${categorie.libelle}"
					<c:if test="${accModel.categorie.libelle == categorie.libelle}">selected
					</c:if>>${categorie.libelle}</option>
			</c:forEach>
		</select></div>
  <div class="col-md-3"></div>
  
  <div class="col-md-1"></div>
  <div class="col-md-1"></div>
  <div class="col-md-1"><button type="submit" value="Rechercher" class="btn-secondary btn-lg">Rechercher</button></div>
  <div class="col-md-1"></div>
  <div class="col-md-1"></div>
  <div class="col-md-1"></div>
</form>

<c:if test="${!empty logModel.utilisateur.noUtilisateur}">
	<jsp:include page="achatsMesVentesMenu.jsp" />
</c:if>

<c:if test="${accModel.lstArticleVendu.size() > 0}">
	<table>
		<thead>
			<tr>
				<th>Image</th>
				<th>Article</th>
				<th>Prix</th>
				<th>Date de fin de l'enchere</th>
				<th>Vendeur</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${accModel.lstArticleVendu}" var="articleVendu">
				<tr>
					<td><img alt="image neutre"
						src="<%=request.getContextPath()%>/image/image_informatique.png">
					</td>
					<td><a href="<c:if test="${articleVendu.etatVente != accModel.etatsVenteEncheresTerminees}">DetailVenteServlet</c:if><c:if test="${articleVendu.etatVente == accModel.etatsVenteEncheresTerminees}">VenteRemporteServlet</c:if>?id=${articleVendu.noArticle}">${articleVendu.nomArticle}</a></td>
					<td>${articleVendu.prixVente} points</td>
					<td>${articleVendu.dateFinEncheres}</td>
					<td><a
						href="ProfilServlet?id=${articleVendu.utilisateurVend.noUtilisateur}">${articleVendu.utilisateurVend.pseudo}</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>

<div style = "position : absolute; buttom : 0px; right : 0px "> <img src="image/image_enchere.png">

 </div>
</body>
</html>


  