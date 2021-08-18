package fr.eni.ecole.projet_enchere.dal;

public class EnchereDAOFact {
	public static EnchereDAO getInstance() {
		return new EnchereDAOMock();
	}
}
