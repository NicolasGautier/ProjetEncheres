<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
    <jsp:param name="titre" value="newarticle" />
</jsp:include>
<jsp:include page="navbar.jsp" />
<c:forEach var="key" items="${errModel.err}">
    <c:if test="${key == 'ErrIns'}">
        <c:forEach var="erreur" items="${errModel.errMessages.get('ErrIns')}">
            <p style="color: red">${erreur}</p>
        </c:forEach>
    </c:if>
</c:forEach>
<div class="row justify-content-start">
    <div class="col">
        <div class="image">
            <img alt="enchère new vente"
                src="<%=request.getContextPath()%>/image/image_enchere.png">
        </div>
    </div>
    <div class="col">
        <div>
            <h2>Nouvelle vente</h2>
        </div>

        <form action="NewArticleServlet<c:if test="${!empty newArtModel.articleVendu.noArticle}">?id=${newArtModel.articleVendu.noArticle}</c:if>"  method="post">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-1"></div>
                    <div class="col-3"><label for="nomArticle">Article :</label></div>
                    <div class="col-7"><input id="nomArticle" name="nomArticle" type="text" value="${newArtModel.articleVendu.nomArticle}"></div>
                    <div class="col-1"></div>
                </div>
                    
                <div class="row">
                    <div class="col-1"></div>
                    <div class="col-3"><label for="description">Description :</label></div>
                    <div class="col-7"><textarea id="description" name="description" rows="4" cols="50">${newArtModel.articleVendu.description}</textarea></div>
                    <div class="col-1"></div>
                </div>
                <div class="row">
                    <div class="col-1"></div>
                    <div class="col-3"><label for="categorieSelect">Catégorie :</label></div>
                    <div class="col-7"><select
                            name="categorieSelect">
                            <c:forEach var="categorie" items="${newArtModel.lstCategorie}">
                                <option value="${categorie.noCategorie}"
                                    <c:if test="${newArtModel.categorie.noCategorie == categorie.noCategorie}">selected
                            </c:if>>${categorie.libelle}</option>
                            </c:forEach>
                        </select></div>
                    <div class="col-1"></div>
                </div>                  
                
                
                <div class="row">
                    <div class="col-1"></div>
                    <div class="col-3"><label for="photoarticle">Photo de l'article :</label></div>
                    <div class="col-7"><input id="photoarticle" name="photoarticle" type="file"></div>
                    <div class="col-1"></div>
                </div>
                                            
                        <!-- TODO insérer photo dans article -->
                        
                <div class="row">
                    <div class="col-1"></div>
                    <div class="col-3"><label for="miseaprix">Mise à prix :</label></div>
                    <div class="col-7"><input type="number" id="miseaprix" name="sprix" min="0" placeholder="150" value="${newArtModel.articleVendu.miseAPrix}"></div>
                    <div class="col-1"></div>
                </div>
                <div class="row">
                    <div class="col-1"></div>
                    <div class="col-3"><label for="dateDebutEncheres">Début de l'enchère :</label></div>
                    <div class="col-7"><input type="datetime-local" id="dateDebutEncheres"  name="dateDebutEncheres" value="${newArtModel.articleVendu.dateDebutEncheres}"></div>
                    <div class="col-1"></div>
                </div>
                        
                <div class="row">
                    <div class="col-1"></div>
                    <div class="col-3"><label for="dateFinEncheres">Fin de l'enchère :</label></div>
                    <div class="col-7"><input type="datetime-local" id="dateFinEncheres" name="dateFinEncheres"value="${newArtModel.articleVendu.dateFinEncheres}"></div>
                    <div class="col-1"></div>
                </div>
                                    
    <fieldset style="border: 10 px solid silver">
          
            <legend><h2>Retrait</h2></legend>
                                                    
                <div class="row">
                    <div class="col-1"></div>
                    <div class="col-3"><label for="rue">Rue :</label></div>
                    <div class="col-7"><input type="text" id="rue"  name="rue" placeholder="Rue des Mouettes" value="${newArtModel.retrait.rue}"></div>
                    <div class="col-1"></div>
                </div>
                        
                <div class="row">
                    <div class="col-1"></div>
                    <div class="col-3"><label for="cp">Code postal :</label></div>
                    <div class="col-7"><input type="text" id="cp" name="code_postal" placeholder="44800" value="${newArtModel.retrait.code_postal}"></div>
                    <div class="col-1"></div>
                </div>
                
                <div class="row">
                    <div class="col-1"></div>
                    <div class="col-3"><label for="ville">Ville :</label></div>
                    <div class="col-7"><input type="text" id="ville" name="ville" placeholder="Saint Herblain"  value="${newArtModel.retrait.ville}"></div>
                    <div class="col-1"></div>
                </div>      
                            
        </fieldset>
                
                    
                    <div class="row">
                        <div class="col-3"></div>
                        <div class="col-2"><button class="btn-secondary btn-lg btn-block" type="submit"name="enregistrer" value="enregistrer">Enregistrer</button></div>
                        <div class="col-2"><button class="btn-secondary btn-lg btn-block" type="submit"name="annuler" value="annuler">Annuler</button></div>
                        <div class="col-2"><c:if test="${newArtModel.annulerVente}"><button class="btn-secondary btn-lg btn-block" type="submit" name="annulerLaVente" value="annulerLaVente">Annuler lavente</button>
                        </c:if></div>
                        <div class="col-3"></div>
                    </div>
    
                </div>
        </form>
    </div>
</div>
<jsp:include page="footer.jsp" />