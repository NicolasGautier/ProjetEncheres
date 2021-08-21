package fr.eni.ecole.projet_enchere.bll.client0;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fr.eni.ecole.projet_enchere.bll.BLLException;
import fr.eni.ecole.projet_enchere.bll.EnchereManager;
import fr.eni.ecole.projet_enchere.bo.Categorie;
import fr.eni.ecole.projet_enchere.bo.Enchere;
import fr.eni.ecole.projet_enchere.bo.EtatsVente;
import fr.eni.ecole.projet_enchere.bo.Utilisateur;
import fr.eni.ecole.projet_enchere.dal.DALException;
import fr.eni.ecole.projet_enchere.dal.DalFactory;
import fr.eni.ecole.projet_enchere.dal.EnchereDAO;

public class EnchereManagerImpl implements EnchereManager {
	private EnchereDAO enchDao = DalFactory.getEnchereDAO();

	@Override
	public void addEnchere(Enchere enchere) throws BLLException {
		try {
			Boolean insert = true;
			for (Enchere ench : enchDao.selectAll()) {
				if (ench.getArticleConcerne().getNoArticle().equals(enchere.getArticleConcerne().getNoArticle())
						&& ench.getUtilisateurEncherit().getNoUtilisateur()
								.equals(enchere.getUtilisateurEncherit().getNoUtilisateur())) {
					setEnchere(enchere);
					insert = false;
				}
			}
			if (insert) {
				enchDao.insert(enchere);
			}
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
	}

	@Override
	public void setEnchere(Enchere enchere) throws BLLException {
		try {
			enchDao.update(enchere);
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
	}

	@Override
	public void removeEnchere(Enchere enchere) throws BLLException {
		try {
			enchDao.delete(enchere.getUtilisateurEncherit().getNoUtilisateur(),
					enchere.getArticleConcerne().getNoArticle());
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
	}

	@Override
	public List<Enchere> getAllEnchere() throws BLLException {
		try {
			return enchDao.selectAll();
		} catch (DALException e) {

			throw new BLLException(e.getMessage());
		}
	}

	@Override
	public Enchere getEnchere(Enchere enchere) throws BLLException {
		try {
			return enchDao.selectById(enchere.getUtilisateurEncherit().getNoUtilisateur(),
					enchere.getArticleConcerne().getNoArticle());
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
	}

	@Override
	public List<Enchere> getEnchereFiltre(String filtre) throws BLLException {
		List<Enchere> lstFiltre = new ArrayList<Enchere>();
		for (Enchere encheres : getAllEnchere()) {
			if (encheres.getArticleConcerne().getNomArticle().indexOf(filtre) != -1) {
				lstFiltre.add(encheres);
			}
		}
		return lstFiltre.stream().filter(e -> e.getArticleConcerne().getEtatVente().equals(EtatsVente.EN_COURS))
				.collect(Collectors.toList());
	}

	@Override
	public List<Enchere> getEnchereCategorieFiltre(Categorie categorie, String filtre) throws BLLException {
		List<Enchere> lstCategorieFiltre = new ArrayList<Enchere>();
		for (Enchere encheres : getAllEnchere()) {
			if (encheres.getArticleConcerne().getCategorie().getNoCategorie().equals(categorie.getNoCategorie())
					&& encheres.getArticleConcerne().getNomArticle().indexOf(filtre) != -1) {
				lstCategorieFiltre.add(encheres);
			}
		}
		return lstCategorieFiltre.stream()
				.filter(e -> e.getArticleConcerne().getEtatVente().equals(EtatsVente.EN_COURS))
				.collect(Collectors.toList());
	}

	@Override
	public List<Enchere> getEnchereFiltreAchats(String filtre, Boolean enchOuv, Boolean enchCour, Boolean enchRemp,
			Utilisateur utilisateur) throws BLLException {

		List<Enchere> lstFiltre = new ArrayList<Enchere>();

		for (Enchere encheres : getAllEnchere()) {
			if (enchOuv) {
				if (encheres.getArticleConcerne().getNomArticle().indexOf(filtre) != -1) {
					lstFiltre.add(encheres);
				}
			} else if (enchCour || enchRemp) {
				if (encheres.getArticleConcerne().getNomArticle().indexOf(filtre) != -1 && utilisateur
						.getNoUtilisateur().equals(encheres.getUtilisateurEncherit().getNoUtilisateur())) {
					lstFiltre.add(encheres);
				}
			}
		}

		List<Enchere> lstResultat = new ArrayList<Enchere>();

		if (enchOuv || enchCour) {
			lstResultat.addAll(
					lstFiltre.stream().filter(e -> e.getArticleConcerne().getEtatVente().equals(EtatsVente.EN_COURS))
							.collect(Collectors.toList()));
		}
		if (enchRemp) {
			lstResultat.addAll(lstFiltre.stream()
					.filter(e -> e.getArticleConcerne().getEtatVente().equals(EtatsVente.ENCHERES_TERMINEES))
					.collect(Collectors.toList()));
		}

		return lstResultat;
	}

	@Override
	public List<Enchere> getEnchereCategorieFiltreAchats(Categorie categorie, String filtre, Boolean enchOuv,
			Boolean enchCour, Boolean enchRemp, Utilisateur utilisateur) throws BLLException {

		List<Enchere> lstFiltre = new ArrayList<Enchere>();

		for (Enchere encheres : getAllEnchere()) {
			if (enchOuv) {
				if (encheres.getArticleConcerne().getNomArticle().indexOf(filtre) != -1 && encheres.getArticleConcerne()
						.getCategorie().getNoCategorie().equals(categorie.getNoCategorie())) {
					lstFiltre.add(encheres);
				}
			} else if (enchCour || enchRemp) {
				if (encheres.getArticleConcerne().getNomArticle().indexOf(filtre) != -1
						&& encheres.getArticleConcerne().getCategorie().getNoCategorie()
								.equals(categorie.getNoCategorie())
						&& utilisateur.getNoUtilisateur()
								.equals(encheres.getUtilisateurEncherit().getNoUtilisateur())) {
					lstFiltre.add(encheres);
				}
			}
		}

		List<Enchere> lstResultat = new ArrayList<Enchere>();

		if (enchOuv || enchCour) {
			lstResultat.addAll(
					lstFiltre.stream().filter(e -> e.getArticleConcerne().getEtatVente().equals(EtatsVente.EN_COURS))
							.collect(Collectors.toList()));
		}
		if (enchRemp) {
			lstResultat.addAll(lstFiltre.stream()
					.filter(e -> e.getArticleConcerne().getEtatVente().equals(EtatsVente.ENCHERES_TERMINEES))
					.collect(Collectors.toList()));
		}

		return lstResultat;
	}

	@Override
	public List<Enchere> getEnchereFiltreVentes(String filtre, Boolean ventCour, Boolean ventDeb, Boolean ventTer,
			Utilisateur utilisateur) throws BLLException {

		List<Enchere> lstFiltre = new ArrayList<Enchere>();

		for (Enchere encheres : getAllEnchere()) {
			if (encheres.getArticleConcerne().getNomArticle().indexOf(filtre) != -1 && utilisateur.getNoUtilisateur()
					.equals(encheres.getArticleConcerne().getUtilisateurVend().getNoUtilisateur())) {
				lstFiltre.add(encheres);
			}
		}

		List<Enchere> lstResultat = new ArrayList<Enchere>();

		if (ventCour) {
			lstResultat.addAll(
					lstFiltre.stream().filter(e -> e.getArticleConcerne().getEtatVente().equals(EtatsVente.EN_COURS))
							.collect(Collectors.toList()));
		}
		if (ventDeb) {
			lstResultat.addAll(
					lstFiltre.stream().filter(e -> e.getArticleConcerne().getEtatVente().equals(EtatsVente.CREEE))
							.collect(Collectors.toList()));
		}
		if (ventTer) {
			lstResultat.addAll(lstFiltre.stream()
					.filter(e -> e.getArticleConcerne().getEtatVente().equals(EtatsVente.ENCHERES_TERMINEES))
					.collect(Collectors.toList()));
		}

		return lstResultat;
	}

	@Override
	public List<Enchere> getEnchereCategorieFiltreVentes(Categorie categorie, String filtre, Boolean ventCour,
			Boolean ventDeb, Boolean ventTer, Utilisateur utilisateur) throws BLLException {

		List<Enchere> lstFiltre = new ArrayList<Enchere>();

		for (Enchere encheres : getAllEnchere()) {
			if (encheres.getArticleConcerne().getNomArticle().indexOf(filtre) != -1
					&& encheres.getArticleConcerne().getCategorie().getNoCategorie().equals(categorie.getNoCategorie())
					&& utilisateur.getNoUtilisateur()
							.equals(encheres.getArticleConcerne().getUtilisateurVend().getNoUtilisateur())) {
				lstFiltre.add(encheres);
			}
		}

		List<Enchere> lstResultat = new ArrayList<Enchere>();

		if (ventCour) {
			lstResultat.addAll(
					lstFiltre.stream().filter(e -> e.getArticleConcerne().getEtatVente().equals(EtatsVente.EN_COURS))
							.collect(Collectors.toList()));
		}
		if (ventDeb) {
			lstResultat.addAll(
					lstFiltre.stream().filter(e -> e.getArticleConcerne().getEtatVente().equals(EtatsVente.CREEE))
							.collect(Collectors.toList()));
		}
		if (ventTer) {
			lstResultat.addAll(lstFiltre.stream()
					.filter(e -> e.getArticleConcerne().getEtatVente().equals(EtatsVente.ENCHERES_TERMINEES))
					.collect(Collectors.toList()));
		}

		return lstResultat;
	}

}
