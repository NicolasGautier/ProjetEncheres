package fr.eni.ecole.projet_enchere.dal;

import fr.eni.ecole.projet_enchere.bo.ArticleVendu;
import fr.eni.ecole.projet_enchere.bo.Retrait;
import fr.eni.ecole.projet_enchere.bo.Utilisateur;

public class TestRetraitDAO {

	public static void main(String[] args) throws DALException {
		RetraitDAO retDao = DalFactory.getRetraitDAO();
		ArticleVenduDAO artDao = DalFactory.getArticleVenduDAO();
		UtilisateurDAO utilDao = DalFactory.getUtilisateurDAO();
		
		ArticleVendu art = artDao.selectById(1);
		Utilisateur util = utilDao.selectById(13);
		
		Retrait ret1 = new Retrait(util.getRue(),util.getCodePostal(),util.getVille(),art);

		retDao.insert(ret1);
		
		System.out.println(retDao.selectAll());
		
		ret1.setRue("Du Chemin");
		retDao.update(ret1);
		
		System.out.println(retDao.selectById(ret1.getArticleVendu().getNoArticle()));
		
		retDao.delete(ret1.getArticleVendu().getNoArticle());
		
		System.out.println(retDao.selectAll());
	}

}
