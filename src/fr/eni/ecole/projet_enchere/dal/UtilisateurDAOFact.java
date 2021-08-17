package fr.eni.ecole.projet_enchere.dal;

public class UtilisateurDAOFact {
	public static UtilisateurDAO getInstance() {
		return new UtilisateurDAOImpl();
	}
}
