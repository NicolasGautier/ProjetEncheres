package fr.eni.ecole.projet_enchere.bll;

public class UtilisateurManagerSingl {

	private static UtilisateurManagerImpl instance;

	public static UtilisateurManagerImpl getInstance() {

		if (instance == null) {
			instance = new UtilisateurManagerImpl();
		}
		return instance;
	}
}
