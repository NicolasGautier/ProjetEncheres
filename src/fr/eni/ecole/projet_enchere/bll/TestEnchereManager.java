package fr.eni.ecole.projet_enchere.bll;

import java.time.LocalDate;

import fr.eni.ecole.projet_enchere.bo.Enchere;
import fr.eni.ecole.projet_enchere.dal.ArticleVenduDAO;
import fr.eni.ecole.projet_enchere.dal.ArticleVenduDAOFact;
import fr.eni.ecole.projet_enchere.dal.DALException;
import fr.eni.ecole.projet_enchere.dal.UtilisateurDAO;
import fr.eni.ecole.projet_enchere.dal.UtilisateurDAOFact;

public class TestEnchereManager {
	public static void main(String[] args) throws DALException, BLLException {

		EnchereManager enchMan = EnchereManagerSingl.getInstance();
		UtilisateurDAO utilDao = UtilisateurDAOFact.getInstance();
		ArticleVenduDAO artDao = ArticleVenduDAOFact.getInstance();

		System.out.println("TEST 1 : INSERTION DE DEUX ENCHERES");

		Enchere enchere1 = new Enchere(LocalDate.of(2018, 8, 10), 210, utilDao.selectById(13), artDao.selectById(1));

		enchMan.addEnchere(enchere1);
		enchMan.addEnchere(enchere1);

		System.out.println(enchMan.getAllEnchere());

	}
}