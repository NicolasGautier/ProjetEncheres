package fr.eni.ecole.projet_enchere.bll;

import java.util.List;

import fr.eni.ecole.projet_enchere.bo.Utilisateur;

/*Il a été choisi de centraliser toutes les contraintes dans une BLL unique :
 * NG 16/08/2021, création :
 * 1/ Utilisateur : Reprise des fonctionalités du DAO en ajoutant des cotraintes en EnchereManagerImpl
 * 2/ Utilisateur : Vérification du login et mot de passe en base de donnée
 */
public interface UtilisateurManager {
	
	public void insert(Utilisateur utilisateur) throws Exception;
	
	public void update(Utilisateur utilisateur) throws Exception;

	public void delete(Integer id) throws Exception;
	
	public List<Utilisateur> selectAll() throws Exception;
	
	public Utilisateur selectById(Integer id) throws Exception;

	boolean logAndPassChecked(Utilisateur utilisateur) throws Exception;

}
