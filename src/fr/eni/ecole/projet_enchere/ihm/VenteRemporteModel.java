package fr.eni.ecole.projet_enchere.ihm;

import fr.eni.ecole.projet_enchere.bo.Retrait;

public class VenteRemporteModel {

	private Retrait retrait;

	public VenteRemporteModel() {
		super();
	}

	public VenteRemporteModel(Retrait retrait) {
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
		return "VenteRemporteModel [retrait=" + retrait + "]";
	}

}
