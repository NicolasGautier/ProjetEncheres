package fr.eni.ecole.projet_enchere.bll;

import java.util.List;

import fr.eni.ecole.projet_enchere.bo.ArticleVendu;
import fr.eni.ecole.projet_enchere.bo.Enchere;
import fr.eni.ecole.projet_enchere.bo.Utilisateur;

/*Il a été choisi de centraliser toutes les contraintes dans une BLL unique :
 * NG 16/08/2021, création :
 * 1/ Utilisateur : Reprise des fonctionalités du DAO en ajoutant des cotraintes en EnchereManagerImpl
 * 2/ Utilisateur : Vérification du login et mot de passe en base de donnée
 */
public interface UtilisateurManager {

	public void addUtilisateur(Utilisateur utilisateur) throws BLLException;

	public void setUtilisateur(Utilisateur utilisateur) throws BLLException;

	public void removeUtilisateur(Utilisateur utilisateur) throws BLLException;

	public List<Utilisateur> getAllUtilisateurs() throws BLLException;

	public Utilisateur getUtilisateur(Utilisateur utilisateur) throws BLLException;

	public boolean logAndPassChecked(Utilisateur utilisateur) throws BLLException;

	public boolean passChecked(Utilisateur utilisateur) throws BLLException;

	public boolean newPassChecked(String newPass, String confPass) throws BLLException;

	public boolean articleRemporteChecked(Utilisateur utilisateur, ArticleVendu articleRemporte) throws BLLException;
	
	public boolean retraitValideChecked(Utilisateur utilisateur, ArticleVendu articleValide) throws BLLException; 
	
	public boolean pointsSuffisantsChecked(Utilisateur utilisateur, Integer points, List<Enchere> encheres) throws BLLException;

	public void rendPointUtilisateur(Utilisateur utilisateur, Integer credit) throws BLLException;; 

	public void prendPointUtilisateur(Utilisateur utilisateur, Integer credit, List<Enchere> encheres) throws BLLException;; 

}
