package fr.eni.ecole.projet_enchere.bll;

import java.util.List;

import fr.eni.ecole.projet_enchere.bo.Enchere;

/*NG 18/08/2021
 *Reprise des fonctionnalités de la DAL avec la contrainte suivante :
 *Lors de la création d'une enchère, il faut tester si, en parcourant le liste,
 *il existe déjà une enchère du même article, du même utilisateur
 */

public interface EnchereManager {

public void addEnchere(Enchere enchere) throws BLLException;
	
	public void setEnchere(Enchere enchere) throws BLLException;

	public void removeEnchere(Enchere enchere) throws BLLException;
	
	public List<Enchere> getAllEnchere() throws BLLException;
	
	public Enchere getEnchere(Enchere enchere) throws BLLException;

}
