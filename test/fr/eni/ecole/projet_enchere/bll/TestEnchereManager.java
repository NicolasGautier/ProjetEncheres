package fr.eni.ecole.projet_enchere.bll;

import java.time.LocalDateTime;

import fr.eni.ecole.projet_enchere.bo.ArticleVendu;
import fr.eni.ecole.projet_enchere.bo.Categorie;
import fr.eni.ecole.projet_enchere.bo.Enchere;
import fr.eni.ecole.projet_enchere.bo.EtatsVente;
import fr.eni.ecole.projet_enchere.bo.Utilisateur;
import fr.eni.ecole.projet_enchere.dal.DALException;

public class TestEnchereManager {
	public static void main(String[] args) throws DALException, BLLException {

		EnchereManager manager = BllFactory.getUniqueEnchereManager();
		Categorie informatique = new Categorie("Informatique");
		Categorie ameublement = new Categorie("Ameublement");
		EnchereManager enchMan = BllFactory.getUniqueEnchereManager();
//		UtilisateurDAO utilDao = UtilisateurDAOFact.getInstance();
//		ArticleVenduDAO artDao = ArticleVenduDAOFact.getInstance();

		System.out.println("TEST 1 : INSERTION DE DEUX ENCHERES");		
		Enchere enchere1 = new Enchere(LocalDateTime.now(), 210,
				new Utilisateur(1,"pseudo1", "nom1","prenom1","email1","telephone1","rue1","cp1","ville1","mot_de_passe1",0,false,true),
				new ArticleVendu(1, "PC Gamer", "PC Gamer pour travailler", LocalDateTime.now().plusDays(5),
						LocalDateTime.now().plusDays(20), 210, null, EtatsVente.CREEE, null, null, informatique, null,"image_informatique.png"));
		
//		Enchere enchere2 = new Enchere(LocalDate.of(2018, 10, 9), 180,
//				new Utilisateur(2,"pseudo2", "nom2","prenom2","email2","telephone2","rue2","cp2","ville2","mot_de_passe2",0,false,true),
//				new ArticleVendu(2, "Rocket Stove", "Rocket Stove pour riz et pâtes",
//						LocalDate.now().plusDays(8), LocalDate.now().plusDays(30), 185, null, EtatsVente.CREEE, null, null,
//						ameublement, null));
//
//
//		Enchere enchere3 = new Enchere(LocalDate.of(2018, 8, 10), 210, utilDao.selectById(13), artDao.selectById(1));

		System.out.println("TEST 2 : TRI PAR CATEGORIE (MOCK)");
		//System.out.println(manager.getEnchereCategorieFiltre(ameublement, ""));
		enchMan.addEnchere(enchere1);
		enchMan.addEnchere(enchere1);
		System.out.println(enchMan.getAllEnchere());

	}
}