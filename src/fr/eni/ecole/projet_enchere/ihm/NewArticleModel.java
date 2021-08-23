package fr.eni.ecole.projet_enchere.ihm;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.projet_enchere.bo.ArticleVendu;
import fr.eni.ecole.projet_enchere.bo.Categorie;

public class NewArticleModel {

	private ArticleVendu articleVendu;
	private Categorie categorie;
	private List<Categorie> lstCategorie = new ArrayList<Categorie>();

	public NewArticleModel() {
	}

	public NewArticleModel(ArticleVendu articleVendu, List<Categorie> lstCategorie) {
		super();
		this.articleVendu = articleVendu;
		this.lstCategorie = lstCategorie;
	}

	public ArticleVendu getArticleVendu() {
		return articleVendu;
	}

	public void setArticleVendu(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	public List<Categorie> getLstCategorie() {
		return lstCategorie;
	}

	public void setLstCategorie(List<Categorie> lstCategorie) {
		this.lstCategorie = lstCategorie;
	}

	@Override
	public String toString() {
		return "NewArticleModel [articleVendu=" + articleVendu + ", categorie=" + lstCategorie + "]";
	}
}
