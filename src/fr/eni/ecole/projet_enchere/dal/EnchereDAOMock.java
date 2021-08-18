package fr.eni.ecole.projet_enchere.dal;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.projet_enchere.bo.Enchere;

public class EnchereDAOMock implements EnchereDAO {
	public static List<Enchere> lst = new ArrayList<>();
	
	@Override
	public void addEnchere(Enchere enchere) throws DALException {
		
		lst.add(enchere);

	}
	
	@Override
	public List<Enchere> getAllEnchere() throws DALException {
	
		return lst;
	}

}
