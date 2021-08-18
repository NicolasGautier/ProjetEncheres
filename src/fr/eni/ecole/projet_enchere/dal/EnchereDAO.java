package fr.eni.ecole.projet_enchere.dal;

import java.util.List;

import fr.eni.ecole.projet_enchere.bo.Enchere;

public interface EnchereDAO {

	public void addEnchere(Enchere enchere) throws DALException;
	
	public List<Enchere> getAllEnchere() throws DALException;
	
}
