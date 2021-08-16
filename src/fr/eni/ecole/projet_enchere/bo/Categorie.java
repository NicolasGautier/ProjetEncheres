package fr.eni.ecole.projet_enchere.bo;

import java.util.ArrayList;
import java.util.List;

public class Categorie {

	private Integer noCategorie;
	private String libelle;

	private List<ArticleVendu> articleVendus = new ArrayList<ArticleVendu>();

	public Categorie() {
		super();
	}

	public Categorie(String libelle) {
		super();
		this.libelle = libelle;
	}

	public Categorie(Integer noCategorie, String libelle) {
		super();
		this.noCategorie = noCategorie;
		this.libelle = libelle;
	}

	public Integer getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(Integer noCategorie) {
		this.noCategorie = noCategorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public void addArticleVendu(ArticleVendu articleVendu) {
		articleVendus.add(articleVendu);
	}

	public void removeArticleVendu(ArticleVendu articleVendu) {
		articleVendus.remove(articleVendu);
	}

	@Override
	public String toString() {
		return "Categorie [noCategorie=" + noCategorie + ", libelle=" + libelle + ", articleVendus=" + articleVendus
				+ "]";
	}

}
