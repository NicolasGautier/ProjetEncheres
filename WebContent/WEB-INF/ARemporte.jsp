<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">  
<jsp:param name="titre" value="detailvente"/>  
</jsp:include>

<jsp:include page="navbar.jsp">  
<jsp:param name="hidden" value="true" />  
</jsp:include>

${acheteur} <h2> a remporté l'enchere </h2>

<h2>${nomarticle} </h2>
<h2>Description</h2>
<h2>Meilleure offre : </h2>  ${offre}  <h2> par</h2> ${acheteur}
<h2>Mise à prix</h2>
<h2>Fin de l'enchère</h2>
<h2>Retrait</h2>
<h2>Vendeur</h2>


 >
       
<button type="submit" id="retraitE" name="eretraitE" value="RetraitE"> Retrait effectué</button>


<jsp:include page="footer.jsp"/> 