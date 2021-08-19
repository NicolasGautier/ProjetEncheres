package fr.eni.ecole.projet_enchere.dal;

import fr.eni.ecole.projet_enchere.dal.context.ArticleVenduDAOImpl;
import fr.eni.ecole.projet_enchere.dal.context.CategorieDAOImpl;
import fr.eni.ecole.projet_enchere.dal.context.EnchereDAOImpl;
import fr.eni.ecole.projet_enchere.dal.context.RetraitDAOImpl;
import fr.eni.ecole.projet_enchere.dal.context.UtilisateurDAOImpl;

public abstract class DalFactory {
	public static ArticleVenduDAO getArticleVenduDAO() {
		return new ArticleVenduDAOImpl();
	}
	
	public static CategorieDAO getCategorieDAO() {
		return new CategorieDAOImpl();
	}
	
	public static EnchereDAO getEnchereDAO() {
		return new EnchereDAOImpl();
	}
	
	public static RetraitDAO getRetraitDAO() {
		return new RetraitDAOImpl();
	}
	
	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOImpl();
	}
}
