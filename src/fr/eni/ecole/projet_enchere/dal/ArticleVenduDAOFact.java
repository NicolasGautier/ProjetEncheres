package fr.eni.ecole.projet_enchere.dal;

public class ArticleVenduDAOFact {
	public static ArticleVenduDAO getInstance() {
		return new ArticleVenduDAOImpl();	
	}
}
