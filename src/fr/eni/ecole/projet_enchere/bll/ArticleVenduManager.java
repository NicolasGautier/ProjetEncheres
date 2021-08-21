package fr.eni.ecole.projet_enchere.bll;

import java.util.List;

import fr.eni.ecole.projet_enchere.bo.ArticleVendu;
import fr.eni.ecole.projet_enchere.bo.Categorie;
import fr.eni.ecole.projet_enchere.bo.Enchere;
import fr.eni.ecole.projet_enchere.bo.Utilisateur;

public interface ArticleVenduManager {

	public void addArticleVendu(ArticleVendu articleVendu) throws BLLException;

	public void setArticleVendu(ArticleVendu articleVendu) throws BLLException;

	public void removeArticleVendu(ArticleVendu articleVendu) throws BLLException;

	public List<ArticleVendu> getAllArticleVendu() throws BLLException;

	public ArticleVendu getArticleVendu(ArticleVendu articleVendu) throws BLLException;

	public List<ArticleVendu> getArticleVenduFiltre(String filtre) throws BLLException;

	public List<ArticleVendu> getArticleVenduCategorieFiltre(Categorie categorie, String filtre) throws BLLException;

	public List<ArticleVendu> getArticleVenduFiltreAchats(String filtre, Boolean enchOuv, Boolean enchCour, Boolean enchRemp, Utilisateur utilisateur) throws BLLException;

	public List<ArticleVendu> getArticleVenduCategorieFiltreAchats(Categorie categorie, String filtre, Boolean enchOuv, Boolean enchCour, Boolean enchRemp, Utilisateur utilisateur) throws BLLException;

	public List<ArticleVendu> getArticleVenduFiltreVentes(String filtre, Boolean ventCour, Boolean ventDeb, Boolean ventTer, Utilisateur utilisateur) throws BLLException;

	public List<ArticleVendu> getArticleVenduCategorieFiltreVentes(Categorie categorie, String filtre, Boolean ventCour, Boolean ventDeb, Boolean ventTer, Utilisateur utilisateur) throws BLLException;

	
}
