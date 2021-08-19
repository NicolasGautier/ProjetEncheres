package fr.eni.ecole.projet_enchere.ihm;

import fr.eni.ecole.projet_enchere.bo.Utilisateur;

public class ModifierProfilModel {

	private Utilisateur utilisateur;

	public ModifierProfilModel() {
		super();
	}

	public ModifierProfilModel(Utilisateur utilisateur) {
		super();
		this.utilisateur = utilisateur;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	@Override
	public String toString() {
		return "ModifierProfilModel [utilisateur=" + utilisateur + "]";
	}
	
	
}
