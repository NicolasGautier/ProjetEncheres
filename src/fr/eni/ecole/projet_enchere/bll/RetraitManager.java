package fr.eni.ecole.projet_enchere.bll;

import java.util.List;

import fr.eni.ecole.projet_enchere.bo.Retrait;

public interface RetraitManager {

	public void addRetrait (Retrait retrait)throws BLLException;
	
	public void setRetrait (Retrait retrait)throws BLLException;
	
	public void removeRetrait (Retrait retrait) throws BLLException;
	
	List<Retrait> getAllRetrait (Retrait retrait) throws BLLException;
	
	public Retrait getRetrait (Retrait retrait) throws BLLException;
	
}
