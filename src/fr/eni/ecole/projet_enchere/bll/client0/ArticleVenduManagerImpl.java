package fr.eni.ecole.projet_enchere.bll.client0;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fr.eni.ecole.projet_enchere.bll.ArticleVenduManager;
import fr.eni.ecole.projet_enchere.bll.BLLException;
import fr.eni.ecole.projet_enchere.bo.ArticleVendu;
import fr.eni.ecole.projet_enchere.bo.Categorie;
import fr.eni.ecole.projet_enchere.bo.EtatsVente;
import fr.eni.ecole.projet_enchere.bo.Utilisateur;
import fr.eni.ecole.projet_enchere.dal.ArticleVenduDAO;
import fr.eni.ecole.projet_enchere.dal.DALException;
import fr.eni.ecole.projet_enchere.dal.DalFactory;
import fr.eni.ecole.projet_enchere.dal.EnchereDAO;

public class ArticleVenduManagerImpl implements ArticleVenduManager {
	private ArticleVenduDAO artVendDao = DalFactory.getArticleVenduDAO();
	private EnchereDAO enchDao = DalFactory.getEnchereDAO();

	@Override
	public void addArticleVendu(ArticleVendu articleVendu) throws BLLException {
		BLLException exception = new BLLException();

		if (articleVendu.getNomArticle().trim().isEmpty()) {
			exception.ajoutMessage("Le nom est obligatoire");
		}

		if (articleVendu.getDescription().trim().isEmpty()) {
			exception.ajoutMessage("La description est obligatoire");
		}

		if (articleVendu.getDateDebutEncheres().isBefore(LocalDateTime.now())) {
			exception.ajoutMessage("La date de debut des enchères doit être supérieur à maintenant");
		}

		if (articleVendu.getDateFinEncheres().isBefore(articleVendu.getDateDebutEncheres())) {
			exception.ajoutMessage("La date de début des enchères doit être antèrieur à la date de fin des enchères");
		}

		if (articleVendu.getMiseAPrix() < 0) {
			exception.ajoutMessage("La mise à prix est obligatoire");
		}

		if (exception.estVide()) {
			try {
				artVendDao.insert(articleVendu);
			} catch (DALException e) {
				exception.ajoutMessage("Un problème d'accès à la base de donnée est apparu : " + e.getMessage());
			}
		}

		if (!exception.estVide()) {
			throw exception;
		}
	}

	@Override
	public void setArticleVendu(ArticleVendu articleVendu) throws BLLException {

		BLLException exception = new BLLException();

		if (articleVendu.getNomArticle().trim().isEmpty()) {
			exception.ajoutMessage("Le nom est obligatoire");
		}

		if (articleVendu.getDescription().trim().isEmpty()) {
			exception.ajoutMessage("La description est obligatoire");
		}

		if (articleVendu.getDateDebutEncheres().isBefore(LocalDateTime.now())) {
			exception.ajoutMessage("La date de debut des enchères doit être supérieur à maintenant");
		}

		if (articleVendu.getDateFinEncheres().isBefore(articleVendu.getDateDebutEncheres())) {
			exception.ajoutMessage("La date de début des enchères doit être antèrieur à la date de fin des enchères");
		}

		if (articleVendu.getMiseAPrix() < 0) {
			exception.ajoutMessage("La mise à prix est obligatoire");
		}

		if (exception.estVide()) {
			try {
				artVendDao.update(articleVendu);
			} catch (DALException e) {
				exception.ajoutMessage("Un problème d'accès à la base de donnée est apparu : " + e.getMessage());
			}
		}

		if (!exception.estVide()) {
			throw exception;
		}
	}

	@Override
	public void removeArticleVendu(ArticleVendu articleVendu) throws BLLException {
		BLLException exception = new BLLException();
		try {
			artVendDao.delete(articleVendu.getNoArticle());
		} catch (DALException e) {
			exception.ajoutMessage("Un problème d'accès à la base de donnée est apparu : " + e.getMessage());
		}
		if (!exception.estVide()) {
			throw exception;
		}
	}

	@Override
	public List<ArticleVendu> getAllArticleVendu() throws BLLException {
		BLLException exception = new BLLException();
		try {
			List<ArticleVendu> resultat = artVendDao.selectAll();

			for (ArticleVendu articleVendu : resultat) {
				articleVendu.setEncheres(enchDao.selectById(articleVendu.getNoArticle()));
			}

			return resultat;
		} catch (DALException e) {
			exception.ajoutMessage("Un problème d'accès à la base de donnée est apparu : " + e.getMessage());
		}
		throw exception;
	}

	@Override
	public ArticleVendu getArticleVendu(Integer id) throws BLLException {
		BLLException exception = new BLLException();
		try {
			ArticleVendu resultat = artVendDao.selectById(id);
			resultat.setEncheres(enchDao.selectById(resultat.getNoArticle()));
			return resultat;
		} catch (DALException e) {
			exception.ajoutMessage("Un problème d'accès à la base de donnée est apparu : " + e.getMessage());
		}
		throw exception;
	}

	@Override
	public ArticleVendu getArticleVendu(ArticleVendu articleVendu) throws BLLException {
		BLLException exception = new BLLException();
		try {
			ArticleVendu resultat = artVendDao.selectById(articleVendu.getNoArticle());
			resultat.setEncheres(enchDao.selectById(resultat.getNoArticle()));
			return resultat;
		} catch (DALException e) {
			exception.ajoutMessage("Un problème d'accès à la base de donnée est apparu : " + e.getMessage());
		}
		throw exception;
	}

	@Override
	public List<ArticleVendu> getArticleVenduFiltre(String filtre) throws BLLException {
		List<ArticleVendu> lstFiltre = new ArrayList<ArticleVendu>();
		for (ArticleVendu articleVendu : getAllArticleVendu()) {
			if (articleVendu.getNomArticle().indexOf(filtre) != -1) {
				lstFiltre.add(articleVendu);
			}
		}
		return lstFiltre.stream().filter(a -> a.getEtatVente().equals(EtatsVente.EN_COURS))
				.collect(Collectors.toList());
	}

	@Override
	public List<ArticleVendu> getArticleVenduCategorieFiltre(Categorie categorie, String filtre) throws BLLException {
		List<ArticleVendu> lstCategorieFiltre = new ArrayList<ArticleVendu>();
		for (ArticleVendu articleVendu : getAllArticleVendu()) {
			if (articleVendu.getCategorie().getNoCategorie().equals(categorie.getNoCategorie())
					&& articleVendu.getNomArticle().indexOf(filtre) != -1) {
				lstCategorieFiltre.add(articleVendu);
			}
		}
		return lstCategorieFiltre.stream().filter(a -> a.getEtatVente().equals(EtatsVente.EN_COURS))
				.collect(Collectors.toList());
	}

	@Override
	public List<ArticleVendu> getArticleVenduFiltreAchats(String filtre, Boolean enchOuv, Boolean enchCour,
			Boolean enchRemp, Utilisateur utilisateur) throws BLLException {
		List<ArticleVendu> lstFiltre = new ArrayList<ArticleVendu>();

		for (ArticleVendu articleVendu : getAllArticleVendu()) {
			if (enchOuv) {
				// Si la vente est en cour && que son nom correspond au filtre
				if (articleVendu.getEtatVente().equals(EtatsVente.EN_COURS)
						&& articleVendu.getNomArticle().indexOf(filtre) != -1) {
					lstFiltre.add(articleVendu);
				}
			} else if (enchCour) {
				// Si la vente est en cour && que son nom correspond au filtre && l'utilisateur
				// participe à une enchère
				if (articleVendu.getEtatVente().equals(EtatsVente.EN_COURS)
						&& articleVendu.getNomArticle().indexOf(filtre) != -1
						&& 0 < articleVendu.getEncheres().stream().filter(e -> e.getUtilisateurEncherit()
								.getNoUtilisateur().equals(utilisateur.getNoUtilisateur())).collect(Collectors.toList())
								.size()) {
					lstFiltre.add(articleVendu);
				}
			}
			if (enchRemp) {
				// Si la vente est terminée && que son nom correspond au filtre && l'utilisateur
				// est l'acheteur.
				if (articleVendu.getEtatVente() == EtatsVente.ENCHERES_TERMINEES
						&& articleVendu.getNomArticle().indexOf(filtre) != -1 && articleVendu.getUtilisateurAchete()
								.getNoUtilisateur().equals(utilisateur.getNoUtilisateur())) {
					lstFiltre.add(articleVendu);
				}
			}
		}
		return lstFiltre;
	}

	@Override
	public List<ArticleVendu> getArticleVenduCategorieFiltreAchats(Categorie categorie, String filtre, Boolean enchOuv,
			Boolean enchCour, Boolean enchRemp, Utilisateur utilisateur) throws BLLException {
		List<ArticleVendu> lstFiltre = new ArrayList<ArticleVendu>();

		for (ArticleVendu articleVendu : getAllArticleVendu()) {
			if (enchOuv) {
				// Si la vente est en cour && que son nom correspond au filtre && que la
				// categorie correspond
				if (articleVendu.getEtatVente().equals(EtatsVente.EN_COURS)
						&& articleVendu.getNomArticle().indexOf(filtre) != -1
						&& articleVendu.getCategorie().getNoCategorie().equals(categorie.getNoCategorie())) {
					lstFiltre.add(articleVendu);
				}
			} else if (enchCour) {
				// Si la vente est en cour && que son nom correspond au filtre && l'utilisateur
				// participe à une enchère && que la categorie correspond
				if (articleVendu.getEtatVente().equals(EtatsVente.EN_COURS)
						&& articleVendu.getNomArticle().indexOf(filtre) != -1
						&& 0 < articleVendu.getEncheres().stream()
								.filter(e -> e.getUtilisateurEncherit().getNoUtilisateur()
										.equals(utilisateur.getNoUtilisateur()))
								.collect(Collectors.toList()).size()
						&& articleVendu.getCategorie().getNoCategorie().equals(categorie.getNoCategorie())) {
					lstFiltre.add(articleVendu);
				}
			}
			if (enchRemp) {
				// Si la vente est terminée && que son nom correspond au filtre && l'utilisateur
				// est l'acheteur && que l'enchère est terminée && que la categorie
				// correspond
				if (articleVendu.getEtatVente() == EtatsVente.ENCHERES_TERMINEES
						&& articleVendu.getNomArticle().indexOf(filtre) != -1
						&& articleVendu.getUtilisateurAchete().getNoUtilisateur().equals(utilisateur.getNoUtilisateur())
						&& articleVendu.getCategorie().getNoCategorie().equals(categorie.getNoCategorie())) {
					lstFiltre.add(articleVendu);
				}
			}
		}
		return lstFiltre;
	}

	@Override
	public List<ArticleVendu> getArticleVenduFiltreVentes(String filtre, Boolean ventCour, Boolean ventDeb,
			Boolean ventTer, Utilisateur utilisateur) throws BLLException {
		List<ArticleVendu> lstFiltre = new ArrayList<ArticleVendu>();

		for (ArticleVendu articleVendu : getAllArticleVendu()) {
			// Si le nom correspond à l'index && l'utilisateur est le vendeur
			if (articleVendu.getNomArticle().indexOf(filtre) != -1
					&& utilisateur.getNoUtilisateur().equals(articleVendu.getUtilisateurVend().getNoUtilisateur())) {
				lstFiltre.add(articleVendu);
			}
		}

		List<ArticleVendu> lstResultat = new ArrayList<ArticleVendu>();

		if (ventCour) {
			lstResultat.addAll(lstFiltre.stream().filter(a -> a.getEtatVente().equals(EtatsVente.EN_COURS))
					.collect(Collectors.toList()));
		}
		if (ventDeb) {
			lstResultat.addAll(lstFiltre.stream().filter(a -> a.getEtatVente().equals(EtatsVente.CREEE))
					.collect(Collectors.toList()));
		}
		if (ventTer) {
			lstResultat.addAll(lstFiltre.stream().filter(a -> a.getEtatVente().equals(EtatsVente.ENCHERES_TERMINEES))
					.collect(Collectors.toList()));
		}

		return lstResultat;
	}

	@Override
	public List<ArticleVendu> getArticleVenduCategorieFiltreVentes(Categorie categorie, String filtre, Boolean ventCour,
			Boolean ventDeb, Boolean ventTer, Utilisateur utilisateur) throws BLLException {
		List<ArticleVendu> lstFiltre = new ArrayList<ArticleVendu>();

		for (ArticleVendu articleVendu : getAllArticleVendu()) {
			// Si le nom correspond à l'index && la categorie correspond && l'utilisateur
			// est le vendeur
			if (articleVendu.getNomArticle().indexOf(filtre) != -1
					&& articleVendu.getCategorie().getNoCategorie().equals(categorie.getNoCategorie())
					&& utilisateur.getNoUtilisateur().equals(articleVendu.getUtilisateurVend().getNoUtilisateur())) {
				lstFiltre.add(articleVendu);
			}
		}

		List<ArticleVendu> lstResultat = new ArrayList<ArticleVendu>();

		if (ventCour) {
			lstResultat.addAll(lstFiltre.stream().filter(a -> a.getEtatVente().equals(EtatsVente.EN_COURS))
					.collect(Collectors.toList()));
		}
		if (ventDeb) {
			lstResultat.addAll(lstFiltre.stream().filter(a -> a.getEtatVente().equals(EtatsVente.CREEE))
					.collect(Collectors.toList()));
		}
		if (ventTer) {
			lstResultat.addAll(lstFiltre.stream().filter(a -> a.getEtatVente().equals(EtatsVente.ENCHERES_TERMINEES))
					.collect(Collectors.toList()));
		}

		return lstResultat;

	}

}
