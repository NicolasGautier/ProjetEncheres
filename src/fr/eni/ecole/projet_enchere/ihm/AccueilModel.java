package fr.eni.ecole.projet_enchere.ihm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.eni.ecole.projet_enchere.bo.Categorie;
import fr.eni.ecole.projet_enchere.bo.Enchere;

public class AccueilModel {

	private String filtre;
	private Categorie categorie;
	private List<Categorie> lstCategorie = new ArrayList<Categorie>();
	private List<Enchere> lstEnchere = new ArrayList<Enchere>();
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

	public AccueilModel(String filtre, Categorie categorie, List<Categorie> lstCategorie, List<Enchere> lstEnchere) {
		super();
		this.filtre = filtre;
		this.categorie = categorie;
		this.lstCategorie = lstCategorie;
		this.lstEnchere = lstEnchere;
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

	public List<Enchere> getLstEnchere() {
		return lstEnchere;
	}

	public void setLstEnchere(List<Enchere> lstEnchere) {
		this.lstEnchere = lstEnchere;
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

	@Override
	public String toString() {
		return "AccueilModel [filtre=" + filtre + ", categorie=" + categorie + ", lstCategorie=" + lstCategorie
				+ ", lstEnchere=" + lstEnchere + ", lstRadio=" + lstRadio + ", lstCheckbox=" + lstCheckbox + "]";
	}

}
