package fr.eni.ecole.projet_enchere.bll.client0;

import java.util.List;

import fr.eni.ecole.projet_enchere.bll.BLLException;
import fr.eni.ecole.projet_enchere.bll.RetraitManager;
import fr.eni.ecole.projet_enchere.bo.Retrait;
import fr.eni.ecole.projet_enchere.dal.DALException;
import fr.eni.ecole.projet_enchere.dal.DalFactory;
import fr.eni.ecole.projet_enchere.dal.RetraitDAO;

public class RetraitManagerImpl implements RetraitManager {

	private RetraitDAO retDao = DalFactory.getRetraitDAO();

	@Override
	public void addRetrait(Retrait retrait) throws BLLException {
		BLLException exception = new BLLException();

		if (retrait.getRue().trim().isEmpty()) {
			exception.ajoutMessage("La rue est obligatoire");
		}

		if (retrait.getCode_postal().trim().isEmpty()) {
			exception.ajoutMessage("Le code postal est obligatoire");
		}

		if (retrait.getVille().trim().isEmpty()) {
			exception.ajoutMessage("La ville est obligatoire");
		}

		if (exception.estVide()) {
			try {
				retDao.insert(retrait);
			} catch (DALException e) {
				exception.ajoutMessage("Un problème d'accès à la base de donnée est apparu : " + e.getMessage());
			}
		}

		if (!exception.estVide()) {
			throw exception;
		}
	}

	@Override
	public void setRetrait(Retrait retrait) throws BLLException {
		BLLException exception = new BLLException();

		if (retrait.getRue().trim().isEmpty()) {
			exception.ajoutMessage("La rue est obligatoire");
		}

		if (retrait.getCode_postal().trim().isEmpty()) {
			exception.ajoutMessage("Le code postal est obligatoire");
		}

		if (retrait.getVille().trim().isEmpty()) {
			exception.ajoutMessage("La ville est obligatoire");
		}

		if (exception.estVide()) {
			try {
				retDao.update(retrait);
			} catch (DALException e) {
				exception.ajoutMessage("Un problème d'accès à la base de donnée est apparu : " + e.getMessage());
			}
		}

		if (!exception.estVide()) {
			throw exception;
		}
	}

	@Override
	public void removeRetrait(Retrait retrait) throws BLLException {
		BLLException exception = new BLLException();
		try {
			retDao.delete(retrait.getArticleVendu().getNoArticle());
		} catch (DALException e) {
			exception.ajoutMessage("Un problème d'accès à la base de donnée est apparu : " + e.getMessage());
		}
	}

	@Override
	public List<Retrait> getAllRetraits() throws BLLException {
		BLLException exception = new BLLException();
		try {
			return retDao.selectAll();
		} catch (DALException e) {
			exception.ajoutMessage("Un problème d'accès à la base de donnée est apparu : " + e.getMessage());
		}
		throw exception;
	}

	@Override
	public Retrait getRetrait(Integer noArticle) throws BLLException {
		BLLException exception = new BLLException();
		try {
			return retDao.selectById(noArticle);
		} catch (DALException e) {
			exception.ajoutMessage("Un problème d'accès à la base de donnée est apparu : " + e.getMessage());
		}
		throw exception;
	}

	@Override
	public Retrait getRetrait(Retrait retrait) throws BLLException {
		BLLException exception = new BLLException();
		try {
			return retDao.selectById(retrait.getArticleVendu().getNoArticle());
		} catch (DALException e) {
			exception.ajoutMessage("Un problème d'accès à la base de donnée est apparu : " + e.getMessage());
		}
		throw exception;
	}
}
