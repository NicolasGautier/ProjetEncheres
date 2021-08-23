package fr.eni.ecole.projet_enchere.bll.client0;

import java.util.List;

import fr.eni.ecole.projet_enchere.bll.BLLException;
import fr.eni.ecole.projet_enchere.bll.UtilisateurManager;
import fr.eni.ecole.projet_enchere.bo.ArticleVendu;
import fr.eni.ecole.projet_enchere.bo.Enchere;
import fr.eni.ecole.projet_enchere.bo.Utilisateur;
import fr.eni.ecole.projet_enchere.dal.DALException;
import fr.eni.ecole.projet_enchere.dal.DalFactory;
import fr.eni.ecole.projet_enchere.dal.UtilisateurDAO;

public class UtilisateurManagerImpl implements UtilisateurManager {

	private UtilisateurDAO dao = DalFactory.getUtilisateurDAO();

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
		throw new BLLException("Mot de passe incorrecte");
	}

	@Override
	public boolean newPassChecked(String newPass, String confPass) throws BLLException {
		if (newPass.equals(confPass)) {
			return true;
		} else {
			throw new BLLException("Le nouveau mot de passe ne correspond pas à la confirmation");
		}
	}

	@Override
	public boolean articleRemporteChecked(Utilisateur utilisateur, ArticleVendu articleRemporte) throws BLLException {
		if (articleRemporte.getUtilisateurAchete().getNoUtilisateur().equals(utilisateur.getNoUtilisateur())) {
			return true;
		}
		throw new BLLException("Vous n'êtes pas l'utilisateur qui a remporté l'enchère");
	}

	@Override
	public boolean retraitValideChecked(Utilisateur utilisateur, ArticleVendu articleValide) throws BLLException {
		if (articleValide.getUtilisateurVend().getNoUtilisateur().equals(utilisateur.getNoUtilisateur())) {
			return true;
		}
		throw new BLLException("Vous n'êtes pas l'utilisateur qui a lancé l'enchère");
	}

	@Override
	public boolean pointsSuffisantsChecked(Utilisateur utilisateur, Integer points, List<Enchere> encheres) throws BLLException {
		for(Enchere enchere : encheres) {
			if(enchere.getUtilisateurEncherit().getNoUtilisateur().equals(utilisateur.getNoUtilisateur())) {
				points -= enchere.getMontant_enchere();
			}
		}
		if (utilisateur.getCredit() >= points) {
			return true;
		}
		throw new BLLException("Impossible, vous n'avez pas assez de point");
	}

	@Override
	public void rendPointUtilisateur(Utilisateur utilisateur, Integer credit) throws BLLException {
		utilisateur.addCredit(credit);
		setUtilisateur(utilisateur);
	}

	@Override
	public void prendPointUtilisateur(Utilisateur utilisateur, Integer credit, List<Enchere> encheres) throws BLLException {
		for (Enchere enchere : encheres) {
			if( enchere.getUtilisateurEncherit().getNoUtilisateur().equals(utilisateur.getNoUtilisateur())) {
				rendPointUtilisateur(utilisateur, enchere.getMontant_enchere());
				break;
			}
		}
		utilisateur.removeCredit(credit);
		setUtilisateur(utilisateur);
	}
}
