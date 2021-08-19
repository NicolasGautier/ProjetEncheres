<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav>
	<a href="AccueilServlet">ENI-Encheres</a>
	
	<c:if test="${empty param.hidden}">
		<c:if test="${empty logModel}">
			<a href="InsererProfilServlet">S'incrire</a> - <a href="LoginServlet">Se connecter</a>	
		</c:if>	
		<c:if test="${!empty logModel}">
			<a href="#">Enchères</a>
			<a href="#">Vendre un article</a>
			<a href="#">Mon profil</a>
			<a href="#">Déconnexion</a>	
		</c:if>
		
		
	</c:if>
</nav>
