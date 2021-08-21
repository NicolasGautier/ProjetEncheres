package fr.eni.ecole.projet_enchere.ihm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.eni.ecole.projet_enchere.bo.ArticleVendu;
import fr.eni.ecole.projet_enchere.bo.Categorie;

public class AccueilModel {

	private String filtre;
	private Categorie categorie;
	private List<Categorie> lstCategorie = new ArrayList<Categorie>();
	private List<ArticleVendu> lstArticleVendu = new ArrayList<ArticleVendu>();
	private Map<String, Boolean> lstRadio = new HashMap<String, Boolean>();
	private Map<String, Boolean> lstCheckbox = new HashMap<String, Boolean>();

	public AccueilModel() {
	}

	public AccueilModel(String filtre, Categorie categorie, List<Categorie> lstCategorie) {
		super();
		this.filtre = filtre;
		this.categorie = categorie;
		this.lstCategorie = lstCategorie;
	}

	public AccueilModel(String filtre, Categorie categorie, List<Categorie> lstCategorie,
			List<ArticleVendu> lstArticleVendu) {
		super();
		this.filtre = filtre;
		this.categorie = categorie;
		this.lstCategorie = lstCategorie;
		this.lstArticleVendu = lstArticleVendu;
	}

	public String getFiltre() {
		return filtre;
	}

	public void setFiltre(String filtre) {
		this.filtre = filtre;
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

	public List<ArticleVendu> getLstArticleVendu() {
		return lstArticleVendu;
	}

	public void setLstArticleVendu(List<ArticleVendu> lstArticleVendu) {
		this.lstArticleVendu = lstArticleVendu;
	}

	public Map<String, Boolean> getLstRadio() {
		return lstRadio;
	}

	public void setLstRadio(String key, Boolean etat) {
		this.lstRadio.put(key, etat);
	}

	public Map<String, Boolean> getLstCheckbox() {
		return lstCheckbox;
	}

	public void setLstCheckbox(String key, Boolean etat) {
		this.lstCheckbox.put(key, etat);
	}

	

}
