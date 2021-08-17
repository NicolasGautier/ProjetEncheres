package fr.eni.ecole.projet_enchere.bll;

import java.util.List;

import fr.eni.ecole.projet_enchere.bo.Utilisateur;
import fr.eni.ecole.projet_enchere.dal.DALException;
import fr.eni.ecole.projet_enchere.dal.UtilisateurDAO;
import fr.eni.ecole.projet_enchere.dal.UtilisateurDAOFact;

public class UtilisateurManagerImpl implements UtilisateurManager {

	private UtilisateurDAO dao = UtilisateurDAOFact.getInstance();

	@Override
	public void insert(Utilisateur utilisateur) throws BLLException {
		// test de l'existence du pseudo et du mail dans la liste utilisateur

		try {
			for (Utilisateur element : dao.selectAll()) {
				if (element.getPseudo().equals(utilisateur.getPseudo())
						|| element.getEmail().equals(utilisateur.getEmail())) {
					System.out.println("utilisateurmanagerbll : verif login et mail");
					throw new BLLException("Login ou mail déjà en base de donnée,merci de changer de login ou de mail");
				}
			}
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		} 
		// test de l'existence de caractères uniquement alaphanumérique
		if (utilisateur.getPseudo().matches("[a-zA-Z0-9]*$")) {
			System.out.println("utilisateurmanagerbll : verif caract alphanumerique du login");
			try {
				dao.insert(utilisateur);
			} catch (DALException e) {
				throw new BLLException(e.getMessage());
			}
		} 
	}

	@Override
	public void update(Utilisateur utilisateur) throws BLLException {
		try {
			dao.update(utilisateur);
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
	}

	@Override
	public void delete(Integer id) throws BLLException {
		try {
			dao.delete(id);
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
	}

	@Override
	public List<Utilisateur> selectAll() throws BLLException {

		try {
			return dao.selectAll();
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
	}

	@Override
	public Utilisateur selectById(Integer id) throws BLLException {
		
		try {
			return dao.selectById(id);
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		} 
	}
	
	@Override
	public boolean logAndPassChecked(Utilisateur utilisateur) throws BLLException {
		boolean areInDatabase = false;

		// test de l'existence du pseudo et du mail dans la liste utilisateur
		try {
			for (Utilisateur element : dao.selectAll()) {
				if ((element.getPseudo().equals(utilisateur.getPseudo()) || element.getEmail().equals(utilisateur.getEmail()))
						&& element.getMotDePasse().equals(utilisateur.getMotDePasse())) {
					System.out.println("utilisateurmanagerbll : verif login et mail en base de donnée");

					areInDatabase = true;
				}
			}
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
		return areInDatabase;
	}
}
