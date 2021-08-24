package fr.eni.ecole.projet_enchere.ihm;

import fr.eni.ecole.projet_enchere.bo.ArticleVendu;
import fr.eni.ecole.projet_enchere.bo.Enchere;
import fr.eni.ecole.projet_enchere.bo.Retrait;

public class DetailVenteModel {

	private ArticleVendu articleVendu;
	private Retrait retrait;
	private Enchere meilleurEnchere;

	public DetailVenteModel() {
		super();
	}

	public DetailVenteModel(ArticleVendu articleVendu, Retrait retrait) {
		super();
		this.articleVendu = articleVendu;
		this.retrait = retrait;
	}

	public ArticleVendu getArticleVendu() {
		return articleVendu;
	}

	public void setArticleVendu(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
	}

	public Retrait getRetrait() {
		return retrait;
	}

	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}

	public Enchere getMeilleurEnchere() {
		return meilleurEnchere;
	}

	public void setMeilleurEnchere(Enchere meilleurEnchere) {
		this.meilleurEnchere = meilleurEnchere;
	}

	@Override
	public String toString() {
		return "DetailVenteModel [articleVendu=" + articleVendu + ", retrait=" + retrait + ", meilleurEnchere="
				+ meilleurEnchere + "]";
	}

}
