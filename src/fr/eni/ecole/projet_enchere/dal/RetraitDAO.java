package fr.eni.ecole.projet_enchere.dal;

import java.util.List;

import fr.eni.ecole.projet_enchere.bo.Retrait;

public interface RetraitDAO {

	void insert(Retrait retrait) throws DALException;

	void update(Retrait retrait) throws DALException;

	void delete(Integer idArticle) throws DALException;

	List<Retrait> selectAll() throws DALException;

	Retrait selectById(Integer idArticle) throws DALException;

}
