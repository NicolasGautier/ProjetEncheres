package fr.eni.ecole.projet_enchere.dal;

public class RetraitDAOFact {
	public static RetraitDAO getInstance() {
		return new RetraitDAOImpl();
	}
}
