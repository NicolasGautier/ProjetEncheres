package fr.eni.ecole.projet_enchere.ihm;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.projet_enchere.bo.ArticleVendu;
import fr.eni.ecole.projet_enchere.bo.Enchere;
import fr.eni.ecole.projet_enchere.bo.Utilisateur;

public class DetailVenteModel {
private ArticleVendu articleVendu;

	public void setArticleVendu(ArticleVendu articleVendu) {
	this.articleVendu = articleVendu;
}

	private Enchere enchere;
	private List<Enchere>lstEnchere = new ArrayList<Enchere>();
	
			
	public DetailVenteModel() {
		super();
	}

	public ArticleVendu getArticleVendu() {
		return articleVendu;
	}

	public DetailVenteModel(Enchere enchere) {
		super();
		this.enchere = enchere;
	}

	public DetailVenteModel(Enchere enchere, List<Enchere> lstEnchere) {
		super();
		this.enchere = enchere;
		this.lstEnchere = lstEnchere;
	}


	public Enchere getEnchere() {
		return enchere;
	}

	public void setEnchere(Enchere enchere) {
		this.enchere = enchere;
	}

	public List<Enchere> getLstEnchere() {
		return lstEnchere;
	}
	
	
	public void setLstEnchere(List<Enchere> lstEnchere) {
		this.lstEnchere = lstEnchere;
	}

	@Override
	public String toString() {
		return "DetailVenteModel [enchere=" + enchere + ", lstEnchere=" + lstEnchere + "]";
	}


	
	
	
}
