package fr.eni.ecole.projet_enchere.bo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArticleVendu {
	
	private Integer noArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private Integer miseAPrix;
	private Integer prixVente;
	private EtatsVente etatVente;
	private Utilisateur utilisateurAchete;
	private Utilisateur utilisateurVend;
	private List<Enchere> encheres = new ArrayList<Enchere>();
	private Categorie categorie;
	private Retrait retrait;
	
	public ArticleVendu() {
		super();
	}

	public ArticleVendu(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			Integer miseAPrix, Integer prixVente, EtatsVente etatVente, Utilisateur utilisateurAchete,
			Utilisateur utilisateurVend, Categorie categorie, Retrait retrait) {
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.utilisateurAchete = utilisateurAchete;
		this.utilisateurVend = utilisateurVend;
		this.categorie = categorie;
		this.retrait = retrait;
	}

	public ArticleVendu(Integer noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, Integer miseAPrix, Integer prixVente, EtatsVente etatVente,
			Utilisateur utilisateurAchete, Utilisateur utilisateurVend, Categorie categorie, Retrait retrait) {
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.utilisateurAchete = utilisateurAchete;
		this.utilisateurVend = utilisateurVend;
		this.categorie = categorie;
		this.retrait = retrait;
	}

	public Integer getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(Integer noArticle) {
		this.noArticle = noArticle;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}
	
	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}
	
	public Integer getMiseAPrix() {
		return miseAPrix;
	}

	public void setMiseAPrix(Integer miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(Integer prixVente) {
		this.prixVente = prixVente;
	}

	public EtatsVente getEtatVente() {
		return etatVente;
	}

	public void setEtatVente(EtatsVente etatVente) {
		this.etatVente = etatVente;
	}

	public Utilisateur getUtilisateurAchete() {
		return utilisateurAchete;
	}

	public void setUtilisateurAchete(Utilisateur utilisateurAchete) {
		this.utilisateurAchete = utilisateurAchete;
	}

	public Utilisateur getUtilisateurVend() {
		return utilisateurVend;
	}

	public void setUtilisateurVend(Utilisateur utilisateurVend) {
		this.utilisateurVend = utilisateurVend;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Retrait getRetrait() {
		return retrait;
	}

	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}
	
	public void addEnchere(Enchere enchere) {
		encheres.add(enchere);
	}
	
	public void removeEnchere(Enchere enchere) {
		encheres.remove(enchere);
	}

	@Override
	public String toString() {
		return "ArticleVendu [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix="
				+ miseAPrix + ", prixVente=" + prixVente + ", etatVente=" + etatVente + ", utilisateurAchete="
				+ utilisateurAchete + ", utilisateurVend=" + utilisateurVend + ", encheres=" + encheres + ", categorie="
				+ categorie + ", retrait=" + retrait + "]";
	}
}
