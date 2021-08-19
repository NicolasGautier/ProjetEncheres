package fr.eni.ecole.projet_enchere.dal.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import fr.eni.ecole.projet_enchere.bo.ArticleVendu;
import fr.eni.ecole.projet_enchere.dal.ArticleVenduDAO;
import fr.eni.ecole.projet_enchere.dal.DALException;

public class ArticleVenduMock implements ArticleVenduDAO{
	private static int cpt = 1;
	public static List<ArticleVendu> lst = new ArrayList<ArticleVendu>();
	
	@Override
	public void insert(ArticleVendu articlevendu) throws DALException {
		lst.add(articlevendu);
		articlevendu.setNoArticle(cpt++);
		}
	@Override
	public void update(ArticleVendu articlevendu) throws DALException {
		Integer index = IntStream.range(0, lst.size()).filter(i -> lst.get(i).getNoArticle()== articlevendu.getNoArticle())
				.findFirst().orElse(-1);
		if(index !=-1)
			lst.set(index, articlevendu);
	}
	
	@Override
	public void delete(Integer id) throws DALException {
		lst.removeIf(c -> c.getNoArticle().equals(id));
	}

	@Override
	public List<ArticleVendu> selectAll() throws DALException {

		return lst;
	}

	@Override
	public ArticleVendu selectById(Integer id) throws DALException {
		
		return lst.stream().filter(a -> a.getNoArticle() == id).collect(Collectors.toList()).get(0);
	}

	

	

	
	
}
