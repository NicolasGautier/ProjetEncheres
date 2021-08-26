package fr.eni.ecole.projet_enchere.dal.mock;

import java.time.LocalDateTime;

import fr.eni.ecole.projet_enchere.bo.ArticleVendu;
import fr.eni.ecole.projet_enchere.bo.EtatsVente;
import fr.eni.ecole.projet_enchere.dal.ArticleVenduDAO;
import fr.eni.ecole.projet_enchere.dal.DALException;
import fr.eni.ecole.projet_enchere.dal.DalFactory;

public class TestArticleVenduMock {
	public static void main(String[] args) throws DALException {

		ArticleVenduDAO dao = DalFactory.getArticleVenduDAO();

		ArticleVendu article1 = new ArticleVendu(1, "PC Gamer", "PC Gamer pour travailler", LocalDateTime.now().plusDays(5),
				LocalDateTime.now().plusDays(20), 210, null, EtatsVente.CREEE, null, null, null, null,"image_informatique.png");
		ArticleVendu article2 = new ArticleVendu(2, "Rocket Stove", "Rocket Stove pour riz et pâtes",
				LocalDateTime.now().plusDays(8), LocalDateTime.now().plusDays(30), 185, null, EtatsVente.CREEE, null, null,
				null, null,"image_informatique.png");
		ArticleVendu article3 = new ArticleVendu(3, "Super Chaise", "Super Chaise pour s'assoir",
				LocalDateTime.now().plusDays(10), LocalDateTime.now().plusDays(20), 120, null, EtatsVente.CREEE, null, null,
				null, null,"image_informatique.png");

		System.out.println("TEST 1 : INSERTION DE TROIS ARTICLES");
		dao.insert(article1);
		dao.insert(article2);
		dao.insert(article3);
		System.out.println(dao.selectAll());

		System.out.println("TEST 2 : set article3");
		dao.update(article3);
		System.out.println(dao.selectAll());
		
		System.out.println("TEST 3 : select by id article 2");
		
		System.out.println(dao.selectById(article2.getNoArticle()));
		
		System.out.println("TEST 4 : delete article 2");
		
		dao.delete(article2.getNoArticle());
		
		System.out.println(dao.selectAll());
		
		
		
		
	}

}
