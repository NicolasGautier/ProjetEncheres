package fr.eni.ecole.projet_enchere.dal;

import java.util.List;

import fr.eni.ecole.projet_enchere.bo.Utilisateur;

public interface UtilisateurDAO {

	public void insert(Utilisateur utilisateur) throws DALException;

	public void update(Utilisateur utilisateur) throws DALException;

	public void delete(Integer id) throws DALException;
	
	public List<Utilisateur> selectAll() throws DALException;
	
	public Utilisateur selectById(Integer id) throws DALException;

	void updatePW(Utilisateur utilisateur) throws DALException;
}
