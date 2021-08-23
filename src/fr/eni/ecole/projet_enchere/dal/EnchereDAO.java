package fr.eni.ecole.projet_enchere.dal;

import java.util.List;

import fr.eni.ecole.projet_enchere.bo.Enchere;

public interface EnchereDAO {

	public void insert(Enchere enchere) throws DALException;

	public void update(Enchere enchere) throws DALException;

	public void delete(Integer idUtilisateur, Integer idArticle) throws DALException;

	public List<Enchere> selectAll() throws DALException;

	public List<Enchere> selectById(Integer idArticle) throws DALException;
	
	public Enchere selectById(Integer idUtilisateur, Integer idArticle) throws DALException;
	
}
