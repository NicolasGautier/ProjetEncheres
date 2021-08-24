<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">  
<jsp:param name="titre" value="login" />  
</jsp:include>

<jsp:include page="navbar.jsp">  
<jsp:param name="hidden" value="true" />  
</jsp:include>

<c:forEach var="key" items="${errModel.err}">		
	<c:if test="${key == 'ErrPar'}">
		<c:forEach var="erreur" items="${errModel.errMessages.get('ErrPar')}">
			<p style="color:red">${erreur}</p>
		</c:forEach>	
	</c:if>	
	<c:if test="${key == 'ErrGet'}">
		<c:forEach var="erreur" items="${errModel.errMessages.get('ErrGet')}">
			<p style="color:red">${erreur}</p>
		</c:forEach>	
	</c:if>		
</c:forEach>
		
<h1>Vous avez remporté la vente</h1>

<div><img alt="image neutre" src="<%=request.getContextPath()%>/image/image_informatique.png"></div>

<div>${ventRempModel.retrait.articleVendu.nomArticle}</div>
<label>Description</label><textarea>${ventRempModel.retrait.articleVendu.description}</textarea>
<label>Meilleur offre:</label><div>${ventRempModel.retrait.articleVendu.prixVente} points</div>
<label>Mise à prix:</label><div>${ventRempModel.retrait.articleVendu.miseAPrix} points</div>
<label>Retrait</label><div>${ventRempModel.retrait.rue}</div>
						<div>${ventRempModel.retrait.code_postal} ${ventRempModel.retrait.ville}</div>
<label>Vendeur:</label><div>${ventRempModel.retrait.articleVendu.utilisateurVend.pseudo}</div>
<label>Tel:</label><div>${ventRempModel.retrait.articleVendu.utilisateurVend.telephone}</div>

<form action="VenteRemporteServlet" method="post">
	<button type="submit" name="formulaireBack" value="back">Back</button>
</form>

<jsp:include page="footer.jsp"/>