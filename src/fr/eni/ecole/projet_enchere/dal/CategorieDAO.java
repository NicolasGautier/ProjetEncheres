package fr.eni.ecole.projet_enchere.dal;

import java.util.List;

import fr.eni.ecole.projet_enchere.bo.Categorie;

public interface CategorieDAO {

	void insert(Categorie categorie) throws DALException;

	void update(Categorie categorie) throws DALException;

	void delete(Integer id) throws DALException;

	List<Categorie> selectAll() throws DALException;

	Categorie selectById(Integer id) throws DALException;

}
