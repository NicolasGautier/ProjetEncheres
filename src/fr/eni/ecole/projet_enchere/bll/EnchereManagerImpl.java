package fr.eni.ecole.projet_enchere.bll;

import java.util.List;

import fr.eni.ecole.projet_enchere.bo.Enchere;
import fr.eni.ecole.projet_enchere.dal.DALException;
import fr.eni.ecole.projet_enchere.dal.EnchereDAO;
import fr.eni.ecole.projet_enchere.dal.EnchereDAOFact;

public class EnchereManagerImpl implements EnchereManager {
	private EnchereDAO dao = EnchereDAOFact.getInstance();


	@Override
	public void addEnchere(Enchere enchere) throws BLLException {
		try {
			// Parcours de la liste
			//for (Enchere ench : dao.selectAll()) {
				//Si l'article cncerné par l'enchère existe dans la base de donnée
				//if (ench.getArticleConcerne().getNoArticle().equals(enchere.getArticleConcerne().getNoArticle())
					//	&& ench.getUtilisateurEncherit().getNoUtilisateur().equals(enchere.getUtilisateurEncherit().getNoUtilisateur())) {
					//Update de l'enchere
				//	dao.update(enchere);

				//} else {
					//Sinon, on insert une nouvelle enchère
					dao.insert(enchere);
				//}
			//}
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
			dao.selectById(enchere.getUtilisateurEncherit().getNoUtilisateur(),
					enchere.getArticleConcerne().getNoArticle());
			return enchere;

		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}

	}

}
