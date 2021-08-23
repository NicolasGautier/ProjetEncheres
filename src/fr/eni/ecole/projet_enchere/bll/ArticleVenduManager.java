package fr.eni.ecole.projet_enchere.bll;

import java.util.List;

import fr.eni.ecole.projet_enchere.bo.ArticleVendu;

/*
 * NG 20/08/2021
 * Pour créer l'IHM, création de la BLL sans contraintes 
 */

public interface ArticleVenduManager {

	public void addArticleVendu (ArticleVendu articlevendu) throws BLLException;
	
	public void setArticleVendu (ArticleVendu articlevendu) throws BLLException;
	
	public void removeArticleVendu (ArticleVendu articlevendu) throws BLLException;
	
	List<ArticleVendu> getAllArticleVendu(Integer id) throws BLLException;
	
	public ArticleVendu getArticleVendu (ArticleVendu articlevendu) throws BLLException;
	
}
