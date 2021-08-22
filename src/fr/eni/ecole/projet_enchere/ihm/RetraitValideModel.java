package fr.eni.ecole.projet_enchere.ihm;

import fr.eni.ecole.projet_enchere.bo.Retrait;

public class RetraitValideModel {

	private Retrait retrait;

	public RetraitValideModel() {
		super();
	}

	public RetraitValideModel(Retrait retrait) {
		super();
		this.retrait = retrait;
	}

	public Retrait getRetrait() {
		return retrait;
	}

	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}

	@Override
	public String toString() {
		return "RetraitValideModel [retrait=" + retrait + "]";
	}

}
