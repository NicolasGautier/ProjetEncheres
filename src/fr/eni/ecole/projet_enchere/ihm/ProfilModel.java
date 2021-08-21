package fr.eni.ecole.projet_enchere.ihm;

import fr.eni.ecole.projet_enchere.bo.Utilisateur;

public class ProfilModel {

	private Utilisateur utilisateur;

	public ProfilModel() {
		super();
	}

	public ProfilModel(Utilisateur utilisateur) {
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
		return "ProfilModel [utilisateur=" + utilisateur + "]";
	}

}
