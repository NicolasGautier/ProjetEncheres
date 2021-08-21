package fr.eni.ecole.projet_enchere.bll;

import fr.eni.ecole.projet_enchere.bll.client0.ArticleVenduManagerImpl;
import fr.eni.ecole.projet_enchere.bll.client0.CategorieManagerImpl;
import fr.eni.ecole.projet_enchere.bll.client0.EnchereManagerImpl;
import fr.eni.ecole.projet_enchere.bll.client0.UtilisateurManagerImpl;

public abstract class BllFactory {

	private static UtilisateurManager utilisateurManager;
	private static EnchereManager enchereManager;
	private static CategorieManager categorieManager;
	private static ArticleVenduManager articleVenduManager;

	public static UtilisateurManager getUniqueUtilisateurManager() {
		if (utilisateurManager == null) {
			utilisateurManager = new UtilisateurManagerImpl();
		}
		return utilisateurManager;
	}

	public static EnchereManager getUniqueEnchereManager() {
		if (enchereManager == null) {
			enchereManager = new EnchereManagerImpl();
		}
		return enchereManager;
	}

	public static CategorieManager getUniqueCategorieManager() {
		if (categorieManager == null) {
			categorieManager = new CategorieManagerImpl();
		}
		return categorieManager;
	}
	
	public static ArticleVenduManager getUniqueArticleVenduManager() {
		if (articleVenduManager == null) {
			articleVenduManager = new ArticleVenduManagerImpl();
		}
		return articleVenduManager;
	}
	
}
