package fr.eni.ecole.projet_enchere.bll;

import java.util.List;

import fr.eni.ecole.projet_enchere.bo.Categorie;
import fr.eni.ecole.projet_enchere.bo.Enchere;

/*NG 18/08/2021
 *Reprise des fonctionnalités de la DAL avec la contrainte suivante :
 *Lors de la création d'une enchère, il faut tester si, en parcourant le liste,
 *il existe déjà une enchère du même article, du même utilisateur
 *Création d'un algo de tri par catégorie
 */

public interface CategorieManager {

	public void addCategorie(Categorie categorie) throws BLLException;

	public void setCategorie(Categorie categorie) throws BLLException;

	public void removeCategorie(Categorie categorie) throws BLLException;

	public List<Categorie> getAllCategorie() throws BLLException;

	public Categorie getCategorie(Categorie categorie) throws BLLException;
}
