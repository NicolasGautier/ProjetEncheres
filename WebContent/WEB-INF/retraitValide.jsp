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

<h1>${retValModel.retrait.articleVendu.utilisateurAchete.pseudo} a remporté l'enchere</h1>

<div><img alt="image neutre" src="<%=request.getContextPath()%>/image/image_informatique.png"></div>

<div>${retValModel.retrait.articleVendu.nomArticle}</div>
<label>Description</label><textarea>${retValModel.retrait.articleVendu.description}</textarea>
<label>Meilleur offre:</label><div>${retValModel.retrait.articleVendu.prixVente} points par ${retValModel.retrait.articleVendu.utilisateurAchete.pseudo}</div>
<label>Mise à prix:</label><div>${retValModel.retrait.articleVendu.miseAPrix} points</div>
<label>Retrait</label><div>${retValModel.retrait.rue}</div>
						<div>${retValModel.retrait.code_postal} ${ventRempModel.retrait.ville}</div>
<label>Vendeur:</label><div>${retValModel.retrait.articleVendu.utilisateurVend.pseudo}</div>

<form action="RetraitValideServlet" method="post">
	<input type="hidden" name="id" value="${retValModel.retrait.articleVendu.noArticle}">
	<button type="submit" name="formulaireRetEff" value="retEff">Retrait effectué</button>
</form>

<jsp:include page="footer.jsp"/>