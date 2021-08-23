package fr.eni.ecole.projet_enchere.ihm;

import fr.eni.ecole.projet_enchere.bo.ArticleVendu;

public class NewArticleModel {

	private ArticleVendu articlevendu;
	
	public NewArticleModel() {
	}

	public NewArticleModel(ArticleVendu articlevendu) {
		super();
		this.articlevendu = articlevendu;
	}

	public ArticleVendu getArticleVendu() {
		return articlevendu;
	}

	public void setArticleVendu(ArticleVendu articlevendu) {
		this.articlevendu = articlevendu;
	}

	@Override
	public String toString() {
		return "NewArticleModel [articlevendu=" + articlevendu + "]";
	}
	
}
