package fr.eni.ecole.projet_enchere.bll.client0;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.projet_enchere.bll.BLLException;
import fr.eni.ecole.projet_enchere.bll.EnchereManager;
import fr.eni.ecole.projet_enchere.bo.Categorie;
import fr.eni.ecole.projet_enchere.bo.Enchere;
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
	public List<Enchere> getAllEnchereCategorie(Categorie categorie) throws BLLException {
		List<Enchere> lstCategorie = new ArrayList<Enchere>();
		try {
			for (Enchere encheres : dao.selectAll()) {	
				if(encheres.getArticleConcerne().getCategorie().getNoCategorie().equals(categorie.getNoCategorie())) {
					lstCategorie.add(encheres);
				}
			}
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
		return lstCategorie;
	}

	

}
