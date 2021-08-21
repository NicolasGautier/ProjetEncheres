package fr.eni.ecole.projet_enchere.dal;

import java.util.List;

import fr.eni.ecole.projet_enchere.bo.Categorie;

public interface CategorieDAO {

	public void insert(Categorie categorie) throws DALException;

	public void update(Categorie categorie) throws DALException;

	public void delete(Integer id) throws DALException;

	public List<Categorie> selectAll() throws DALException;

	public Categorie selectById(Integer id) throws DALException;

}
