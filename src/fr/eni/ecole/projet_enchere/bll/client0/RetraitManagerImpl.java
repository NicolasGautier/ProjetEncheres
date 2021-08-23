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
		try {
			retDao.insert(retrait);
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
	}

	@Override
	public void setRetrait(Retrait retrait) throws BLLException {
		try {
			retDao.update(retrait);
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
	}

	@Override
	public void removeRetrait(Retrait retrait) throws BLLException {
		try {
			retDao.delete(retrait.getArticleVendu().getNoArticle());
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
	}

	@Override
	public List<Retrait> getAllRetraits() throws BLLException {
		try {
			return retDao.selectAll();
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
	}

	@Override
	public Retrait getRetrait(Integer noArticle) throws BLLException {
		try {
			return retDao.selectById(noArticle);
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
	}

	@Override
	public Retrait getRetrait(Retrait retrait) throws BLLException {

		try {
			return retDao.selectById(retrait.getArticleVendu().getNoArticle());
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
	}
}
