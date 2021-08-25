<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <link rel="stylesheet" href = "style/style.css"> -->
<nav>
	<div class="row mb-3">
		<div class="col-md-8 themed-grid-col text-left">
															
			<a href="AccueilServlet">
				<img src="<%=request.getContextPath()%>/image/iconeimageeni.jpg">
							</a>
			
		</div>
		<div class="col-6 col-md-4 themed-grid-col text-right">
			<c:if test="${empty param.hidden}">
				<c:if test="${empty logModel.utilisateur.noUtilisateur}">
					<a href="InsererProfilServlet">S'incrire</a>  - <a href="LoginServlet">Se connecter</a>	
				</c:if>	
				<c:if test="${!empty logModel.utilisateur.noUtilisateur}">
					<a href="#">Enchères</a>  -  <a href="NewArticleServlet">Vendre un article</a>  -  <a href="ModifierProfilServlet">Mon profil</a>  -  <a href="LogoutServlet">Déconnexion</a>					
	
				</c:if>
			</c:if>
		</div>
	</div>
	
</nav>
