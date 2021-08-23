package fr.eni.ecole.projet_enchere.bll.client0;

import java.util.List;

import fr.eni.ecole.projet_enchere.bll.ArticleVenduManager;
import fr.eni.ecole.projet_enchere.bll.BLLException;
import fr.eni.ecole.projet_enchere.bo.ArticleVendu;
import fr.eni.ecole.projet_enchere.dal.ArticleVenduDAO;
import fr.eni.ecole.projet_enchere.dal.DALException;
import fr.eni.ecole.projet_enchere.dal.DalFactory;

public class ArticleVenduManagerImpl implements ArticleVenduManager {

	private ArticleVenduDAO dao = DalFactory.getArticleVenduDAO();

	@Override
	public void addArticleVendu(ArticleVendu articlevendu) throws BLLException {

		try {

			dao.insert(articlevendu);

		} catch (DALException e) {

			throw new BLLException(e.getMessage());
		}
	}

	@Override
	public void setArticleVendu(ArticleVendu articlevendu) throws BLLException {
		try {
			dao.update(articlevendu);

		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
	}

	@Override
	public void removeArticleVendu(ArticleVendu articlevendu) throws BLLException {

		try {
			dao.delete(articlevendu.getNoArticle());

		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
	}

	@Override
	public List<ArticleVendu> getAllArticleVendu(Integer id) throws BLLException {

		try {

			return dao.selectAll();

		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
	}

	@Override
	public ArticleVendu getArticleVendu(ArticleVendu articlevendu) throws BLLException {

		try {

			return dao.selectById(articlevendu.getNoArticle());

		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
	}

}
