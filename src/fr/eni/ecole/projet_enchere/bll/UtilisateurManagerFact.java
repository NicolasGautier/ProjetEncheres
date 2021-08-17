package fr.eni.ecole.projet_enchere.bll;

public class UtilisateurManagerFact {

	public static UtilisateurManagerImpl getInstanceManager() {

		return new UtilisateurManagerImpl();

	}

}
