package fr.eni.ecole.projet_enchere.bll.client0;

import java.util.List;

import fr.eni.ecole.projet_enchere.bll.BLLException;
import fr.eni.ecole.projet_enchere.bll.CategorieManager;
import fr.eni.ecole.projet_enchere.bo.Categorie;
import fr.eni.ecole.projet_enchere.dal.CategorieDAO;
import fr.eni.ecole.projet_enchere.dal.DALException;
import fr.eni.ecole.projet_enchere.dal.DalFactory;

public class CategorieManagerImpl implements CategorieManager {
	private CategorieDAO catDao = DalFactory.getCategorieDAO();
	
	@Override
	public void addCategorie(Categorie categorie) throws BLLException {
		try {
			catDao.insert(categorie);
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
	}

	@Override
	public void setCategorie(Categorie categorie) throws BLLException {
		try {
			catDao.update(categorie);
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
	}

	@Override
	public void removeCategorie(Categorie categorie) throws BLLException {
		try {
			catDao.delete(categorie.getNoCategorie());
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
	}

	@Override
	public List<Categorie> getAllCategorie() throws BLLException {
		try {
			return catDao.selectAll();
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
	}

	@Override
	public Categorie getCategorie(Categorie categorie) throws BLLException {
		try {
			return catDao.selectById(categorie.getNoCategorie());
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
	}

}
