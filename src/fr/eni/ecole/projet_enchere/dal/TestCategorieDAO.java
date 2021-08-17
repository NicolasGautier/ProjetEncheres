package fr.eni.ecole.projet_enchere.dal;

import fr.eni.ecole.projet_enchere.bo.Categorie;

public class TestCategorieDAO {

	public static void main(String[] args) throws DALException {
		CategorieDAO catDao = CategorieDAOFact.getInstance();

		Categorie cat1 = new Categorie("Categorie1");
		Categorie cat2 = new Categorie("Categorie2");
		Categorie cat3 = new Categorie("Categorie3");
		Categorie cat4 = new Categorie("Categorie4");

		catDao.insert(cat1);
		catDao.insert(cat2);
		catDao.insert(cat3);
		catDao.insert(cat4);
		
		System.out.println(catDao.selectAll());
		
		cat4.setLibelle("Voiture");
		catDao.update(cat4);
		
		System.out.println(catDao.selectById(cat4.getNoCategorie()));
		
		catDao.delete(cat4.getNoCategorie());
		
		System.out.println(catDao.selectAll());
	}

}
