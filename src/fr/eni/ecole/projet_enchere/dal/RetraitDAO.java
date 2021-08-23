package fr.eni.ecole.projet_enchere.dal;

import java.util.List;

import fr.eni.ecole.projet_enchere.bo.Retrait;

public interface RetraitDAO {

	public void insert(Retrait retrait) throws DALException;

	public void update(Retrait retrait) throws DALException;

	public void delete(Integer idArticle) throws DALException;

	public List<Retrait> selectAll() throws DALException;

	public Retrait selectById(Integer idArticle) throws DALException;

}
