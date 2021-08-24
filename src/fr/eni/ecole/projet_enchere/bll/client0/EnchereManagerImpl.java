package fr.eni.ecole.projet_enchere.bll.client0;

import java.util.List;
import java.util.stream.Collectors;

import fr.eni.ecole.projet_enchere.bll.ArticleVenduManager;
import fr.eni.ecole.projet_enchere.bll.BLLException;
import fr.eni.ecole.projet_enchere.bll.BllFactory;
import fr.eni.ecole.projet_enchere.bll.EnchereManager;
import fr.eni.ecole.projet_enchere.bo.Enchere;
import fr.eni.ecole.projet_enchere.dal.DALException;
import fr.eni.ecole.projet_enchere.dal.DalFactory;
import fr.eni.ecole.projet_enchere.dal.EnchereDAO;

public class EnchereManagerImpl implements EnchereManager {
	private EnchereDAO enchDao = DalFactory.getEnchereDAO();
	private ArticleVenduManager artVendManager = BllFactory.getUniqueArticleVenduManager();

	@Override
	public void addEnchere(Enchere enchere) throws BLLException {
		BLLException exception = new BLLException();

		try {
			// Si le montant de l'enchère est inférieur au montant de l'enchère la plus
			// haute
			if (enchere.getMontant_enchere() < enchDao.selectAll().stream()
					.sorted((e1, e2) -> e2.getMontant_enchere().compareTo(e1.getMontant_enchere()))
					.collect(Collectors.toList()).get(0).getMontant_enchere()) {
				exception.ajoutMessage("Le montant de l'enchère doit être supérieur au montant de la dernière enchère");
			}
		} catch (DALException e) {
			exception.ajoutMessage("Un problème d'accès à la base de donnée est apparu : " + e.getMessage());
		}

		if (exception.estVide()) {
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
				enchere.getArticleConcerne().setPrixVente(enchere.getMontant_enchere());
				artVendManager.setArticleVendu(enchere.getArticleConcerne());
			} catch (DALException e) {
				exception.ajoutMessage("Un problème d'accès à la base de donnée est apparu : " + e.getMessage());
			}
		}

		if (!exception.estVide()) {
			throw exception;
		}
	}

	@Override
	public void setEnchere(Enchere enchere) throws BLLException {
		BLLException exception = new BLLException();

		try {
			// Si le montant de l'enchère est inférieur au montant de l'enchère la plus
			// haute
			if (enchere.getMontant_enchere() < enchDao.selectAll().stream()
					.sorted((e1, e2) -> e2.getMontant_enchere().compareTo(e1.getMontant_enchere()))
					.collect(Collectors.toList()).get(0).getMontant_enchere()) {
				exception.ajoutMessage("Le montant de l'enchère doit être supérieur au montant de la dernière enchère");
			}
		} catch (DALException e) {
			exception.ajoutMessage("Un problème d'accès à la base de donnée est apparu : " + e.getMessage());
		}

		if (exception.estVide()) {
			try {
				enchDao.update(enchere);
			} catch (DALException e) {
				exception.ajoutMessage("Un problème d'accès à la base de donnée est apparu : " + e.getMessage());
			}
		}

		if (!exception.estVide()) {
			throw exception;
		}
	}

	@Override
	public void removeEnchere(Enchere enchere) throws BLLException {
		BLLException exception = new BLLException();
		try {
			enchDao.delete(enchere.getUtilisateurEncherit().getNoUtilisateur(),
					enchere.getArticleConcerne().getNoArticle());
		} catch (DALException e) {
			exception.ajoutMessage("Un problème d'accès à la base de donnée est apparu : " + e.getMessage());
		}
		if (!exception.estVide()) {
			throw exception;
		}
	}

	@Override
	public List<Enchere> getAllEnchere() throws BLLException {
		BLLException exception = new BLLException();
		try {
			return enchDao.selectAll();
		} catch (DALException e) {
			exception.ajoutMessage("Un problème d'accès à la base de donnée est apparu : " + e.getMessage());
		}
		throw exception;
	}

	@Override
	public Enchere getEnchere(Enchere enchere) throws BLLException {
		BLLException exception = new BLLException();
		try {
			return enchDao.selectById(enchere.getUtilisateurEncherit().getNoUtilisateur(),
					enchere.getArticleConcerne().getNoArticle());
		} catch (DALException e) {
			exception.ajoutMessage("Un problème d'accès à la base de donnée est apparu : " + e.getMessage());
		}
		throw exception;
	}

}
