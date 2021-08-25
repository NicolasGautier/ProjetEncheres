<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">  
<jsp:param name="titre" value="detailvente"/>  
</jsp:include>

<jsp:include page="navbar.jsp">  
<jsp:param name="hidden" value="true" />  
</jsp:include>

<h2> Détail vente </h2>

<c:forEach var="key" items="${errModel.err}">		
		<c:if test="${key == 'ErrIns'}">
			<c:forEach var="erreur" items="${errModel.errMessages.get('ErrIns')}">
				<p style="color:red">${erreur}</p>
			</c:forEach>	
		</c:if>		
		<c:if test="${key == 'ErrInit'}">
			<c:forEach var="erreur" items="${errModel.errMessages.get('ErrInit')}">
				<p style="color:red">${erreur}</p>
			</c:forEach>
		</c:if>	
</c:forEach>

<h2>${detailVente.articleVendu.nomArticle} </h2>
<label>Description</label><div>${detailVente.articleVendu.description}</div>
<label>Catégorie</label><div>${detailVente.articleVendu.categorie.libelle}</div>
<label>Meilleure offre : </label><div>${detailVente.articleVendu.prixVente} points par ${detailVente.meilleurEnchere.utilisateurEncherit.pseudo}</div>
<label>Mise à prix</label><div>${detailVente.articleVendu.miseAPrix}</div>
<label>Fin de l'enchère</label><div>${detailVente.articleVendu.dateFinEncheres}</div>
<label>Retrait</label><div>${detailVente.retrait.rue}</div>
					  <div>${detailVente.retrait.code_postal} ${detailVente.retrait.ville}</div>
<label>Vendeur</label><div>${detailVente.articleVendu.utilisateurVend.pseudo}</div>

<c:if test="${detailVente.articleVendu.utilisateurVend.noUtilisateur != logModel.utilisateur.noUtilisateur}">
	<form action="DetailVenteServlet?id=${detailVente.articleVendu.noArticle}" method="post">
		<label>Ma proposition</label><input type="number" id="proposition" name="proposition" min="${detailVente.articleVendu.prixVente + 1}">
		<button type="submit" id="encherir" name="formulaireEncherir" value="encherir">Enchérir</button>
	</form>
</c:if>       
       
<jsp:include page="footer.jsp"/> 