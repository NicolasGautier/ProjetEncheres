package fr.eni.ecole.projet_enchere.bll.client0;

import java.util.List;

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

}
