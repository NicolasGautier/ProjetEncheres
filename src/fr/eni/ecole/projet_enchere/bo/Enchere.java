package fr.eni.ecole.projet_enchere.bo;

import java.time.LocalDate;

public class Enchere {
	
	private LocalDate dateEnchere;
	private Integer montant_enchere;
	
	private Utilisateur utilisateurEncherit;
	private ArticleVendu articleConcerne;
	
	public Enchere() {
		super();
	}

	public Enchere(LocalDate dateEnchere, Integer montant_enchere, Utilisateur utilisateurEncherit,
			ArticleVendu articleConcerne) {
		super();
		this.dateEnchere = dateEnchere;
		this.montant_enchere = montant_enchere;
		this.utilisateurEncherit = utilisateurEncherit;
		this.articleConcerne = articleConcerne;
	}

	public LocalDate getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public Integer getMontant_enchere() {
		return montant_enchere;
	}

	public void setMontant_enchere(Integer montant_enchere) {
		this.montant_enchere = montant_enchere;
	}

	public Utilisateur getUtilisateurEncherit() {
		return utilisateurEncherit;
	}

	public void setUtilisateurEncherit(Utilisateur utilisateurEncherit) {
		this.utilisateurEncherit = utilisateurEncherit;
	}

	public ArticleVendu getArticleConcerne() {
		return articleConcerne;
	}

	public void setArticleConcerne(ArticleVendu articleConcerne) {
		this.articleConcerne = articleConcerne;
	}

	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", montant_enchere=" + montant_enchere + ", utilisateurEncherit="
				+ utilisateurEncherit + ", articleConcerne=" + articleConcerne + "]";
	}
}
