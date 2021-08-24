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
		BLLException exception = new BLLException();

		if (utilisateur.getPseudo().trim().isEmpty()) {
			exception.ajoutMessage("Le pseudo est obligatoire");
		}

		if (utilisateur.getNom().trim().isEmpty()) {
			exception.ajoutMessage("Le nom est obligatoire");
		}

		if (utilisateur.getPrenom().trim().isEmpty()) {
			exception.ajoutMessage("Le prenom est obligatoire");
		}

		if (utilisateur.getEmail().trim().isEmpty()) {
			exception.ajoutMessage("L'email est obligatoire");
		}

		if (utilisateur.getRue().trim().isEmpty()) {
			exception.ajoutMessage("La rue est obligatoire");
		}

		if (utilisateur.getCodePostal().trim().isEmpty()) {
			exception.ajoutMessage("Le code postal est obligatoire");
		}

		if (utilisateur.getVille().trim().isEmpty()) {
			exception.ajoutMessage("La ville est obligatoire");
		}

		if (utilisateur.getMotDePasse().trim().isEmpty()) {
			exception.ajoutMessage("Le mot de passe est obligatoire");
		}

		// test de l'existence du pseudo et du mail dans la liste utilisateur
		try {
			for (Utilisateur util : dao.selectAll()) {
				if (util.getPseudo().equals(utilisateur.getPseudo())
						|| util.getEmail().equals(utilisateur.getEmail())) {
					exception.ajoutMessage("Login ou mail déjà utilisé,merci de changer de login ou de mail");
				}
			}
		} catch (DALException e) {
			exception.ajoutMessage("Un problème d'accès à la base de donnée est apparu : " + e.getMessage());
		}

		// test de l'existence de caractères uniquement alaphanumérique
		if (utilisateur.getPseudo().matches("[a-zA-Z0-9]*$")) {
			exception.ajoutMessage("Ne rentrez que des caractères alphanumériques");
		}

		if (exception.estVide()) {
			try {
				dao.insert(utilisateur);
			} catch (DALException e) {
				exception.ajoutMessage("Un problème d'accès à la base de donnée est apparu : " + e.getMessage());
			}
		}

		if (!exception.estVide()) {
			throw exception;
		}
	}

	@Override
	public void setUtilisateur(Utilisateur utilisateur) throws BLLException {
		BLLException exception = new BLLException();

		if (utilisateur.getPseudo().trim().isEmpty()) {
			exception.ajoutMessage("Le pseudo est obligatoire");
		}

		if (utilisateur.getNom().trim().isEmpty()) {
			exception.ajoutMessage("Le nom est obligatoire");
		}

		if (utilisateur.getPrenom().trim().isEmpty()) {
			exception.ajoutMessage("Le prenom est obligatoire");
		}

		if (utilisateur.getEmail().trim().isEmpty()) {
			exception.ajoutMessage("L'email est obligatoire");
		}

		if (utilisateur.getRue().trim().isEmpty()) {
			exception.ajoutMessage("La rue est obligatoire");
		}

		if (utilisateur.getCodePostal().trim().isEmpty()) {
			exception.ajoutMessage("Le code postal est obligatoire");
		}

		if (utilisateur.getVille().trim().isEmpty()) {
			exception.ajoutMessage("La ville est obligatoire");
		}

		if (utilisateur.getMotDePasse().trim().isEmpty()) {
			exception.ajoutMessage("Le mot de passe est obligatoire");
		}

		// test de l'existence du pseudo et du mail dans la liste utilisateur
		try {
			for (Utilisateur util : dao.selectAll()) {
				if (util.getPseudo().equals(utilisateur.getPseudo())
						|| util.getEmail().equals(utilisateur.getEmail())) {
					exception.ajoutMessage("Login ou mail déjà utilisé,merci de changer de login ou de mail");
				}
			}
		} catch (DALException e) {
			exception.ajoutMessage("Un problème d'accès à la base de donnée est apparu : " + e.getMessage());
		}

		// test de l'existence de caractères uniquement alaphanumérique
		if (utilisateur.getPseudo().matches("[a-zA-Z0-9]*$")) {
			exception.ajoutMessage("Ne rentrez que des caractères alphanumériques");
		}

		if (exception.estVide()) {
			try {
				dao.update(utilisateur);
			} catch (DALException e) {
				exception.ajoutMessage("Un problème d'accès à la base de donnée est apparu : " + e.getMessage());
			}
		}

		if (!exception.estVide()) {
			throw exception;
		}
	}

	@Override
	public void removeUtilisateur(Utilisateur utilisateur) throws BLLException {
		BLLException exception = new BLLException();
		try {
			dao.delete(utilisateur.getNoUtilisateur());
		} catch (DALException e) {
			exception.ajoutMessage("Un problème d'accès à la base de donnée est apparu : " + e.getMessage());
		}
		if (!exception.estVide()) {
			throw exception;
		}
		throw exception;
	}

	@Override
	public List<Utilisateur> getAllUtilisateurs() throws BLLException {
		BLLException exception = new BLLException();
		try {
			return dao.selectAll();
		} catch (DALException e) {
			exception.ajoutMessage("Un problème d'accès à la base de donnée est apparu : " + e.getMessage());
		}
		throw exception;
	}

	@Override
	public Utilisateur getUtilisateur(Utilisateur utilisateur) throws BLLException {
		BLLException exception = new BLLException();
		try {
			return dao.selectById(utilisateur.getNoUtilisateur());
		} catch (DALException e) {
			exception.ajoutMessage("Un problème d'accès à la base de donnée est apparu : " + e.getMessage());
		}
		throw exception;
	}

	@Override
	public boolean logAndPassChecked(Utilisateur utilisateur) throws BLLException {
		BLLException exception = new BLLException();
		try {
			for (Utilisateur util : dao.selectAll()) {
				if ((util.getPseudo().equals(utilisateur.getPseudo()) || util.getEmail().equals(utilisateur.getEmail()))
						&& util.getMotDePasse().equals(utilisateur.getMotDePasse()) && util.getActif()) {
					utilisateur.setNoUtilisateur(util.getNoUtilisateur());
					return true;
				}
			}
		} catch (DALException e) {
			exception.ajoutMessage("Un problème d'accès à la base de donnée est apparu : " + e.getMessage());
		}
		exception.ajoutMessage("Identifiant ou mot de passe incorrecte(s)");
		throw exception;
	}

	@Override
	public boolean passChecked(Utilisateur utilisateur) throws BLLException {
		BLLException exception = new BLLException();
		try {
			for (Utilisateur util : dao.selectAll()) {
				if (util.getMotDePasse().equals(utilisateur.getMotDePasse())
						&& util.getNoUtilisateur().equals(utilisateur.getNoUtilisateur()) && util.getActif()) {
					return true;
				}
			}
		} catch (DALException e) {
			exception.ajoutMessage("Un problème d'accès à la base de donnée est apparu : " + e.getMessage());
		}
		exception.ajoutMessage("Mot de passe incorrecte");
		throw exception;
	}

	@Override
	public boolean newPassChecked(String newPass, String confPass) throws BLLException {
		BLLException exception = new BLLException();
		if (newPass.equals(confPass)) {
			return true;
		}
		exception.ajoutMessage("Le nouveau mot de passe ne correspond pas à la confirmation");
		throw exception;
	}

	@Override
	public boolean articleRemporteChecked(Utilisateur utilisateur, ArticleVendu articleRemporte) throws BLLException {
		BLLException exception = new BLLException();
		if (articleRemporte.getUtilisateurAchete().getNoUtilisateur().equals(utilisateur.getNoUtilisateur())) {
			return true;
		}
		exception.ajoutMessage("Vous n'êtes pas l'utilisateur qui a remporté l'enchère");
		throw exception;
	}

	@Override
	public boolean retraitValideChecked(Utilisateur utilisateur, ArticleVendu articleValide) throws BLLException {
		BLLException exception = new BLLException();
		if (articleValide.getUtilisateurVend().getNoUtilisateur().equals(utilisateur.getNoUtilisateur())) {
			return true;
		}
		exception.ajoutMessage("Vous n'êtes pas l'utilisateur qui a lancé l'enchère");
		throw exception;
	}

	@Override
	public boolean pointsSuffisantsChecked(Utilisateur utilisateur, Integer points, List<Enchere> encheres)
			throws BLLException {
		BLLException exception = new BLLException();
		for (Enchere enchere : encheres) {
			if (enchere.getUtilisateurEncherit().getNoUtilisateur().equals(utilisateur.getNoUtilisateur())) {
				points -= enchere.getMontant_enchere();
			}
		}
		if (utilisateur.getCredit() >= points) {
			return true;
		}
		exception.ajoutMessage("Impossible, vous n'avez pas assez de point");
		throw exception;
	}

	@Override
	public void rendPointUtilisateur(Utilisateur utilisateur, Integer credit) throws BLLException {
		utilisateur.addCredit(credit);
		setUtilisateur(utilisateur);
	}

	@Override
	public void prendPointUtilisateur(Utilisateur utilisateur, Integer credit, List<Enchere> encheres)
			throws BLLException {
		for (Enchere enchere : encheres) {
			if (enchere.getUtilisateurEncherit().getNoUtilisateur().equals(utilisateur.getNoUtilisateur())) {
				rendPointUtilisateur(utilisateur, enchere.getMontant_enchere());
				break;
			}
		}
		utilisateur.removeCredit(credit);
		setUtilisateur(utilisateur);
	}
}
