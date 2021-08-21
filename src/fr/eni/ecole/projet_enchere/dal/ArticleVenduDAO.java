package fr.eni.ecole.projet_enchere.dal;

import java.util.List;

import fr.eni.ecole.projet_enchere.bo.ArticleVendu;

public interface ArticleVenduDAO {

	public void insert(ArticleVendu articlevendu) throws DALException;

	public void update(ArticleVendu articlevendu) throws DALException;

	public void delete(Integer id) throws DALException;

	public List<ArticleVendu> selectAll() throws DALException;

	public ArticleVendu selectById(Integer id) throws DALException;

}
