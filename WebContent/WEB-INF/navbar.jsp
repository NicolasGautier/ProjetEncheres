<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav>
	<div class="row mb-3">
		<div class="col-md-8 themed-grid-col">
			<a href="AccueilServlet">ENI-Encheres</a>
		</div>
		<div class="col-6 col-md-4 themed-grid-col">
			<c:if test="${empty param.hidden}">
				<c:if test="${empty logModel.utilisateur.noUtilisateur}">
					<a href="InsererProfilServlet">S'incrire</a> - <a href="LoginServlet">Se connecter</a>	
				</c:if>	
				<c:if test="${!empty logModel.utilisateur.noUtilisateur}">
					<a href="#">Enchères</a>
					<a href="#">Vendre un article</a>
					<a href="ModifierProfilServlet">Mon profil</a>
					<a href="#">Déconnexion</a>	
				</c:if>
			</c:if>
		</div>
	</div>
	
</nav>
