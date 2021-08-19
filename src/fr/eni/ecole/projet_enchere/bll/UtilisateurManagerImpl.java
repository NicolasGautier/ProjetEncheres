package fr.eni.ecole.projet_enchere.bll;

import java.util.List;

import fr.eni.ecole.projet_enchere.bo.Utilisateur;
import fr.eni.ecole.projet_enchere.dal.DALException;
import fr.eni.ecole.projet_enchere.dal.UtilisateurDAO;
import fr.eni.ecole.projet_enchere.dal.UtilisateurDAOFact;

public class UtilisateurManagerImpl implements UtilisateurManager {

	private UtilisateurDAO dao = UtilisateurDAOFact.getInstance();

	@Override
	public void addUtilisateur(Utilisateur utilisateur) throws BLLException {
		// test de l'existence du pseudo et du mail dans la liste utilisateur

		try {
			for (Utilisateur util : dao.selectAll()) {
				if (util.getPseudo().equals(utilisateur.getPseudo())
						|| util.getEmail().equals(utilisateur.getEmail())) {
					System.out.println("utilisateurmanagerbll : verif login et mail");
					throw new BLLException("Login ou mail déjà utilisé,merci de changer de login ou de mail");
				}
			}
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
		// test de l'existence de caractères uniquement alaphanumérique
		if (utilisateur.getPseudo().matches("[a-zA-Z0-9]*$")) {
			try {
				dao.insert(utilisateur);
			} catch (DALException e) {
				throw new BLLException(e.getMessage());
			}
		} else {
			throw new BLLException("Ne rentrez que des caractères alphanumériques");
		}
	}

	@Override
	public void setUtilisateur(Utilisateur utilisateur) throws BLLException {
		try {
			dao.update(utilisateur);
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
	}

	@Override
	public void removeUtilisateur(Utilisateur utilisateur) throws BLLException {
		try {
			dao.delete(utilisateur.getNoUtilisateur());
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
	}

	@Override
	public List<Utilisateur> getAllUtilisateurs() throws BLLException {

		try {
			return dao.selectAll();
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
	}

	@Override
	public Utilisateur getUtilisateur(Utilisateur utilisateur) throws BLLException {

		try {
			return dao.selectById(utilisateur.getNoUtilisateur());
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
	}

	@Override
	public boolean logAndPassChecked(Utilisateur utilisateur) throws BLLException {
		try {
			for (Utilisateur util : dao.selectAll()) {
				if ((util.getPseudo().equals(utilisateur.getPseudo()) || util.getEmail().equals(utilisateur.getEmail()))
						&& util.getMotDePasse().equals(utilisateur.getMotDePasse()) && util.getActif()) {
					utilisateur.setNoUtilisateur(util.getNoUtilisateur());
					return true;
				}
			}
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
		throw new BLLException("Identifiant ou mot de passe incorrecte(s)");
	}

	@Override
	public boolean passChecked(Utilisateur utilisateur) throws BLLException {
		try {
			for (Utilisateur util : dao.selectAll()) {
				if (util.getMotDePasse().equals(utilisateur.getMotDePasse())
						&& util.getNoUtilisateur().equals(utilisateur.getNoUtilisateur()) && util.getActif()) {
					return true;
				}
			}
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
		throw new BLLException("Mot de passe incorrecte(s)");
	}

	@Override
	public boolean newPassChecked(String newPass, String confPass) throws BLLException {
		if (newPass.equals(confPass)) {
			return true;
		} else {
			throw new BLLException("Le nouveau mot de passe ne correspond pas à la confirmation");
		}
	}
}
