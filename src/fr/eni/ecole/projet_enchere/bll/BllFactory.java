package fr.eni.ecole.projet_enchere.bll;

import fr.eni.ecole.projet_enchere.bll.client0.EnchereManagerImpl;
import fr.eni.ecole.projet_enchere.bll.client0.UtilisateurManagerImpl;

public abstract class BllFactory {

	private static UtilisateurManager utilisateurManager;
	private static EnchereManager enchereManager;

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

}
