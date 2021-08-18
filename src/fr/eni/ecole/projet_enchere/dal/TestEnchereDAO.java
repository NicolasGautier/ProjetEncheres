package fr.eni.ecole.projet_enchere.dal;

import java.time.LocalDate;

import fr.eni.ecole.projet_enchere.bo.Enchere;

public class TestEnchereDAO {

	public static void main(String[] args) throws DALException {
		EnchereDAO enchDao = EnchereDAOFact.getInstance();
		UtilisateurDAO utilDao = UtilisateurDAOFact.getInstance();
		ArticleVenduDAO artDao = ArticleVenduDAOFact.getInstance(); //TODO Changer les factories
		
		Enchere ench1 = new Enchere(LocalDate.now(),200,utilDao.selectById(13),artDao.selectById(1));
		/*Enchere ench2 = new Enchere(LocalDate.now(),350,utilDao.selectById(13),artDao.selectById(1));
		Enchere ench3 = new Enchere();
		Enchere ench4 = new Enchere();*/

		enchDao.insert(ench1);
		
		System.out.println(enchDao.selectAll());
		
		ench1.setMontant_enchere(300);
		enchDao.update(ench1);
		
		System.out.println(enchDao.selectById(ench1.getUtilisateurEncherit().getNoUtilisateur(),ench1.getArticleConcerne().getNoArticle()));
		
		enchDao.delete(ench1.getUtilisateurEncherit().getNoUtilisateur(),ench1.getArticleConcerne().getNoArticle());
		
		System.out.println(enchDao.selectAll());
	}

}
