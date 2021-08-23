package fr.eni.ecole.projet_enchere.ihm;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.projet_enchere.bo.ArticleVendu;
import fr.eni.ecole.projet_enchere.bo.Categorie;

public class NewArticleModel {

	private ArticleVendu articleVendu;
	private List<Categorie> categorie = new ArrayList<Categorie>();

	public NewArticleModel() {
	}

	public NewArticleModel(ArticleVendu articleVendu, List<Categorie> categorie) {
		super();
		this.articleVendu = articleVendu;
		this.categorie = categorie;
	}

	public ArticleVendu getArticleVendu() {
		return articleVendu;
	}

	public void setArticleVendu(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
	}

	public List<Categorie> getCategorie() {
		return categorie;
	}

	public void setCategorie(List<Categorie> categorie) {
		this.categorie = categorie;
	}

	@Override
	public String toString() {
		return "NewArticleModel [articleVendu=" + articleVendu + ", categorie=" + categorie + "]";
	}

	

}
