package fr.eni.ecole.projet_enchere.dal;

import java.util.List;

import fr.eni.ecole.projet_enchere.bo.ArticleVendu;

public interface ArticleVenduDAO {

	void insert(ArticleVendu articlevendu) throws DALException;

	void update(ArticleVendu articlevendu) throws DALException;

	void delete(Integer id) throws DALException;

	List<ArticleVendu> selectAll() throws DALException;

	ArticleVendu selectById(Integer id) throws DALException;

}
