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
		BLLException exception = new BLLException();

		if (categorie.getLibelle().trim().isEmpty()) {
			exception.ajoutMessage("Le libellé est obligatoire");
		}
		
		if (exception.estVide()) {
			try {
				catDao.insert(categorie);
			} catch (DALException e) {
				exception.ajoutMessage("Un problème d'accès à la base de donnée est apparu : " + e.getMessage());
			}
		}
		
		if (!exception.estVide()) {
			throw exception;
		}
	}

	@Override
	public void setCategorie(Categorie categorie) throws BLLException {
		BLLException exception = new BLLException();

		if (categorie.getLibelle().trim().isEmpty()) {
			exception.ajoutMessage("Le libellé est obligatoire");
		}
		if (exception.estVide()) {
			try {
				catDao.update(categorie);
			} catch (DALException e) {
				exception.ajoutMessage("Un problème d'accès à la base de donnée est apparu : " + e.getMessage());
			}
		}

		if (!exception.estVide()) {
			throw exception;
		}
	}

	@Override
	public void removeCategorie(Categorie categorie) throws BLLException {
		BLLException exception = new BLLException();
		try {
			catDao.delete(categorie.getNoCategorie());
		} catch (DALException e) {
			exception.ajoutMessage("Un problème d'accès à la base de donnée est apparu : " + e.getMessage());
		}
		if (!exception.estVide()) {
			throw exception;
		}
	}

	@Override
	public List<Categorie> getAllCategorie() throws BLLException {
		BLLException exception = new BLLException();
		try {
			return catDao.selectAll();
		} catch (DALException e) {
			exception.ajoutMessage("Un problème d'accès à la base de donnée est apparu : " + e.getMessage());
		}
		throw exception;
	}

	@Override
	public Categorie getCategorie(Categorie categorie) throws BLLException {
		BLLException exception = new BLLException();
		try {
			return catDao.selectById(categorie.getNoCategorie());
		} catch (DALException e) {
			exception.ajoutMessage("Un problème d'accès à la base de donnée est apparu : " + e.getMessage());
		}
		throw exception;
	}

}
