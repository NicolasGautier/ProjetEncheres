package fr.eni.ecole.projet_enchere.dal;

import java.util.List;

import fr.eni.ecole.projet_enchere.bo.Enchere;

public interface EnchereDAO {

	void insert(Enchere enchere) throws DALException;

	void update(Enchere enchere) throws DALException;

	void delete(Integer idUtilisateur, Integer idArticle) throws DALException;

	List<Enchere> selectAll() throws DALException;

	Enchere selectById(Integer idUtilisateur, Integer idArticle) throws DALException;
	
}
