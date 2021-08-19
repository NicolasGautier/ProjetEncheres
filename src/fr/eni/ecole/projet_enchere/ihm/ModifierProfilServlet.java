package fr.eni.ecole.projet_enchere.ihm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.projet_enchere.bll.BLLException;
import fr.eni.ecole.projet_enchere.bll.UtilisateurManager;
import fr.eni.ecole.projet_enchere.bll.UtilisateurManagerSingl;
import fr.eni.ecole.projet_enchere.bo.Utilisateur;

/**
 * Servlet implementation class afficherProfilServlet
 */
@WebServlet("/ModifierProfilServlet")
public class ModifierProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager manager = UtilisateurManagerSingl.getInstance();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifierProfilServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		InsererProfilModel model = null;

		try {
			model = new InsererProfilModel(new Utilisateur(), manager.getAllUtilisateurs());
		} catch (BLLException e2) {
			e2.printStackTrace();
		}

		if (request.getParameter("nom") != null) {
			model.getModifier().setPseudo(request.getParameter("Pseudo"));
			model.getModifier().setPrenom(request.getParameter("Prenom"));
			model.getModifier().setTelephone(request.getParameter("Telephone"));
			model.getModifier().setCodePostal(request.getParameter("CodePostal"));
			model.getModifier().setMotDePasse(request.getParameter("MotDePasse"));
			model.getModifier().setMotDePasse(request.getParameter("NouveauMotDePasse"));
			model.getModifier().setNom(request.getParameter("Nom"));
			model.getModifier().setEmail(request.getParameter("Email"));
			model.getModifier().setRue(request.getParameter("rue"));
			model.getModifier().setVille(request.getParameter("Ville"));
			// model.getUtilisateur().setConfirmation(request.getParameter("Confirmation"));

			try {
				manager.addUtilisateur(model.getModifier());
				model.setLstUtilisateur(manager.getAllUtilisateurs());
			} catch (BLLException e) {
				e.printStackTrace();
			}

		}

		request.setAttribute("model", model);
		request.getRequestDispatcher("/WEB-INF/ModifierProfil.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
