<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">  
<jsp:param name="titre" value="detailvente"/>  
</jsp:include>

<jsp:include page="navbar.jsp">  
<jsp:param name="hidden" value="true" />  
</jsp:include>

<h2> Détail vente </h2>

<h2>${nomarticle.} </h2>
<h2>Description</h2>
<h2>Catégorie</h2>
<h2>Meilleure offre : </h2>
<h2>Mise à prix</h2>
<h2>Fin de l'enchère</h2>
<h2>Retrait</h2>
<h2>Vendeur</h2>
<h2>Ma proposition</h2>

<input type="number" id="mapropostion" name="maproposition"
       min="0" >
       
<button type="submit" id="encherir" name="encherir" value="encherir"> Enchérir</button>


<jsp:include page="footer.jsp"/> 