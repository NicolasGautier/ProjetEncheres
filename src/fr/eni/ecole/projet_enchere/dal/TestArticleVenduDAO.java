package fr.eni.ecole.projet_enchere.dal;

import java.time.LocalDate;

import fr.eni.ecole.projet_enchere.bo.ArticleVendu;
import fr.eni.ecole.projet_enchere.bo.EtatsVente;

public class TestArticleVenduDAO {

	public static void main(String[] args) throws DALException {
		ArticleVenduDAO artVenDao = ArticleVenduDAOFact.getInstance();
		UtilisateurDAO utilDao = UtilisateurDAOFact.getInstance();
		CategorieDAO catDao = CategorieDAOFact.getInstance();

		ArticleVendu art1 = new ArticleVendu("nom1", "description1", LocalDate.now(), LocalDate.now(), 50, 150,
				EtatsVente.CREEE, utilDao.selectById(1), utilDao.selectById(13), catDao.selectById(1), null);
		
		ArticleVendu art2 = new ArticleVendu("nom2", "description2", LocalDate.now(), LocalDate.now(), 250, 450,
				EtatsVente.CREEE, utilDao.selectById(2), utilDao.selectById(14), catDao.selectById(1), null);
		
		artVenDao.insert(art1);
		artVenDao.insert(art2);
		
		System.out.println(artVenDao.selectAll());
		
		art2.setNomArticle("Rasta");
		artVenDao.update(art2);
		
		System.out.println(artVenDao.selectById(art2.getNoArticle()));
		
		artVenDao.delete(art2.getNoArticle());
		
		System.out.println(artVenDao.selectAll());
		
	}

}
