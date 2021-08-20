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
	private EnchereDAO dao = DalFactory.getEnchereDAO();

	@Override
	public void addEnchere(Enchere enchere) throws BLLException {
		try {
			Boolean insert = true;
			for (Enchere ench : dao.selectAll()) {
				if (ench.getArticleConcerne().getNoArticle().equals(enchere.getArticleConcerne().getNoArticle())
						&& ench.getUtilisateurEncherit().getNoUtilisateur()
								.equals(enchere.getUtilisateurEncherit().getNoUtilisateur())) {
					setEnchere(enchere);
					insert = false;
				}
			}
			if (insert) {
				dao.insert(enchere);
			}
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
	}

	@Override
	public void setEnchere(Enchere enchere) throws BLLException {
		try {
			dao.update(enchere);
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
	}

	@Override
	public void removeEnchere(Enchere enchere) throws BLLException {
		try {
			dao.delete(enchere.getUtilisateurEncherit().getNoUtilisateur(),
					enchere.getArticleConcerne().getNoArticle());
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
	}

	@Override
	public List<Enchere> getAllEnchere() throws BLLException {
		try {
			return dao.selectAll();
		} catch (DALException e) {

			throw new BLLException(e.getMessage());
		}
	}

	@Override
	public Enchere getEnchere(Enchere enchere) throws BLLException {
		try {
			return dao.selectById(enchere.getUtilisateurEncherit().getNoUtilisateur(),
					enchere.getArticleConcerne().getNoArticle());
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
	}

	@Override
	public List<Enchere> getEnchereFiltre(String filtre) throws BLLException {
		List<Enchere> lstFiltre = new ArrayList<Enchere>();
		try {
			for (Enchere encheres : dao.selectAll()) {
				if (encheres.getArticleConcerne().getNomArticle().indexOf(filtre) != -1) {
					lstFiltre.add(encheres);
				}
			}
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
		return lstFiltre;
	}

	@Override
	public List<Enchere> getEnchereCategorieFiltre(Categorie categorie, String filtre) throws BLLException {
		List<Enchere> lstCategorieFiltre = new ArrayList<Enchere>();
		try {
			for (Enchere encheres : dao.selectAll()) {
				if (encheres.getArticleConcerne().getCategorie().getNoCategorie().equals(categorie.getNoCategorie())
						&& encheres.getArticleConcerne().getNomArticle().indexOf(filtre) != -1) {
					lstCategorieFiltre.add(encheres);
				}
			}
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
		return lstCategorieFiltre;
	}

	@Override
	public List<Enchere> getEnchereFiltreAchats(String filtre, String enchOuv, String enchCour, String enchRemp,
			Utilisateur utilisateur) throws BLLException {
		Boolean enchOuvChek = false;
		Boolean enchCourChek = false;
		Boolean enchRempChek = false;

		if ("on".equals(enchOuv))
			enchOuvChek = true;
		if ("on".equals(enchCour))
			enchCourChek = true;
		if ("on".equals(enchRemp))
			enchRempChek = true;

		System.out.println(enchOuvChek);
		System.out.println(enchCourChek);
		System.out.println(enchRempChek);
		System.out.println(utilisateur);
		List<Enchere> lstFiltre = new ArrayList<Enchere>();

		try {
			for (Enchere encheres : dao.selectAll()) {
				if (enchOuvChek) {
					if (encheres.getArticleConcerne().getNomArticle().indexOf(filtre) != -1) {
						lstFiltre.add(encheres);
					}
				} else if (enchCourChek || enchRempChek) {
					if (encheres.getArticleConcerne().getUtilisateurAchete() != null) {
						if (encheres.getArticleConcerne().getNomArticle().indexOf(filtre) != -1
								&& utilisateur.getNoUtilisateur().equals(
										encheres.getArticleConcerne().getUtilisateurAchete().getNoUtilisateur())) {
							lstFiltre.add(encheres);
						}
					}
				}
			}
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}

		List<Enchere> lstResultat = new ArrayList<Enchere>();

		if (enchOuvChek || enchCourChek) {
			lstResultat.addAll(
					lstFiltre.stream().filter(e -> e.getArticleConcerne().getEtatVente().equals(EtatsVente.EN_COURS))
							.collect(Collectors.toList()));
		}
		if (enchRempChek) {
			lstResultat.addAll(lstFiltre.stream()
					.filter(e -> e.getArticleConcerne().getEtatVente().equals(EtatsVente.ENCHERES_TERMINEES))
					.collect(Collectors.toList()));
		}

		return lstResultat;
	}

	@Override
	public List<Enchere> getEnchereCategorieFiltreAchats(Categorie categorie, String filtre, String enchOuv,
			String enchCour, String enchRemp, Utilisateur utilisateur) throws BLLException {

		//
		Boolean enchOuvChek = false;
		Boolean enchCourChek = false;
		Boolean enchRempChek = false;

		if ("on".equals(enchOuv))
			enchOuvChek = true;
		if ("on".equals(enchCour))
			enchCourChek = true;
		if ("on".equals(enchRemp))
			enchRempChek = true;

		List<Enchere> lstFiltre = new ArrayList<Enchere>();

		try {
			for (Enchere encheres : dao.selectAll()) {
				if (enchOuvChek) {
					if (encheres.getArticleConcerne().getNomArticle().indexOf(filtre) != -1 && encheres
							.getArticleConcerne().getCategorie().getNoCategorie().equals(categorie.getNoCategorie())) {
						lstFiltre.add(encheres);
					}
				} else if (enchCourChek || enchRempChek) {
					if (encheres.getArticleConcerne().getNomArticle().indexOf(filtre) != -1
							&& encheres.getArticleConcerne().getCategorie().getNoCategorie()
									.equals(categorie.getNoCategorie())
							&& utilisateur.getNoUtilisateur()
									.equals(encheres.getArticleConcerne().getUtilisateurAchete().getNoUtilisateur())) {
						lstFiltre.add(encheres);
					}
				}
			}
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}

		List<Enchere> lstResultat = new ArrayList<Enchere>();

		if (enchOuvChek || enchCourChek) {
			lstResultat.addAll(
					lstFiltre.stream().filter(e -> e.getArticleConcerne().getEtatVente().equals(EtatsVente.EN_COURS))
							.collect(Collectors.toList()));
		}
		if (enchRempChek) {
			lstResultat.addAll(lstFiltre.stream()
					.filter(e -> e.getArticleConcerne().getEtatVente().equals(EtatsVente.ENCHERES_TERMINEES))
					.collect(Collectors.toList()));
		}

		return lstResultat;
	}

	@Override
	public List<Enchere> getEnchereFiltreVentes(String filtre, String ventCour, String ventDeb, String ventTer,
			Utilisateur utilisateur) throws BLLException {
		Boolean ventCourChek = false;
		Boolean ventDebChek = false;
		Boolean ventTerChek = false;

		if ("on".equals(ventCour))
			ventCourChek = true;
		if ("on".equals(ventDeb))
			ventDebChek = true;
		if ("on".equals(ventTer))
			ventTerChek = true;

		List<Enchere> lstFiltre = new ArrayList<Enchere>();

		try {
			for (Enchere encheres : dao.selectAll()) {
				if (encheres.getArticleConcerne().getNomArticle().indexOf(filtre) != -1
						&& utilisateur.getNoUtilisateur()
								.equals(encheres.getArticleConcerne().getUtilisateurVend().getNoUtilisateur())) {
					lstFiltre.add(encheres);
				}
			}
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}

		List<Enchere> lstResultat = new ArrayList<Enchere>();

		if (ventCourChek) {
			lstResultat.addAll(
					lstFiltre.stream().filter(e -> e.getArticleConcerne().getEtatVente().equals(EtatsVente.EN_COURS))
							.collect(Collectors.toList()));
		}
		if (ventDebChek) {
			lstResultat.addAll(
					lstFiltre.stream().filter(e -> e.getArticleConcerne().getEtatVente().equals(EtatsVente.CREEE))
							.collect(Collectors.toList()));
		}
		if (ventTerChek) {
			lstResultat.addAll(lstFiltre.stream()
					.filter(e -> e.getArticleConcerne().getEtatVente().equals(EtatsVente.ENCHERES_TERMINEES))
					.collect(Collectors.toList()));
		}

		return lstResultat;
	}

	@Override
	public List<Enchere> getEnchereCategorieFiltreVentes(Categorie categorie, String filtre, String ventCour,
			String ventDeb, String ventTer, Utilisateur utilisateur) throws BLLException {
		Boolean ventCourChek = false;
		Boolean ventDebChek = false;
		Boolean ventTerChek = false;

		if ("on".equals(ventCour))
			ventCourChek = true;
		if ("on".equals(ventDeb))
			ventDebChek = true;
		if ("on".equals(ventTer))
			ventTerChek = true;

		List<Enchere> lstFiltre = new ArrayList<Enchere>();

		try {
			for (Enchere encheres : dao.selectAll()) {
				if (encheres.getArticleConcerne().getNomArticle().indexOf(filtre) != -1
						&& encheres.getArticleConcerne().getCategorie().getNoCategorie()
								.equals(categorie.getNoCategorie())
						&& utilisateur.getNoUtilisateur()
								.equals(encheres.getArticleConcerne().getUtilisateurVend().getNoUtilisateur())) {
					lstFiltre.add(encheres);
				}
			}
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}

		List<Enchere> lstResultat = new ArrayList<Enchere>();

		if (ventCourChek) {
			lstResultat.addAll(
					lstFiltre.stream().filter(e -> e.getArticleConcerne().getEtatVente().equals(EtatsVente.EN_COURS))
							.collect(Collectors.toList()));
		}
		if (ventDebChek) {
			lstResultat.addAll(
					lstFiltre.stream().filter(e -> e.getArticleConcerne().getEtatVente().equals(EtatsVente.CREEE))
							.collect(Collectors.toList()));
		}
		if (ventTerChek) {
			lstResultat.addAll(lstFiltre.stream()
					.filter(e -> e.getArticleConcerne().getEtatVente().equals(EtatsVente.ENCHERES_TERMINEES))
					.collect(Collectors.toList()));
		}

		return lstResultat;
	}

}
