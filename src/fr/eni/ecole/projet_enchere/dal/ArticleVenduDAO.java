package fr.eni.ecole.projet_enchere.dal;

import java.util.List;

import fr.eni.ecole.projet_enchere.bo.ArticleVendu;
import fr.eni.ecole.projet_enchere.bo.Utilisateur;

public interface ArticleVenduDAO {

	public void insert(ArticleVendu articlevendu) throws DALException;

	public void update(ArticleVendu articlevendu) throws DALException;

	public void delete(Integer id) throws DALException;
	
	public List<Utilisateur> selectAll() throws DALException;
	
	public Utilisateur selectById(Integer id) throws DALException;
	
	
}
