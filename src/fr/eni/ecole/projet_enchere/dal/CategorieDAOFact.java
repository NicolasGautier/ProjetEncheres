package fr.eni.ecole.projet_enchere.dal;

public class CategorieDAOFact {
	public static CategorieDAO getInstance() {
		return new CategorieDAOImpl();
	}
}
