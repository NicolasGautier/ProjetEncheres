package fr.eni.ecole.projet_enchere.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.projet_enchere.bll.BLLException;
import fr.eni.ecole.projet_enchere.bll.BllFactory;
import fr.eni.ecole.projet_enchere.bll.UtilisateurManager;

/**
 * Servlet implementation class AfficherServlet
 */
@WebServlet("/AfficherProfilServlet")
public class AfficherProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager manager = BllFactory.getUniqueUtilisateurManager();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AfficherProfilServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AfficherProfilModel model = null;
		try {
			model = new AfficherProfilModel(new AfficherProfilModel(), manager.getAllUtilisateurs());
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		if (request.getParameter("nom") != null) {
			model.getafficher().setPseudo(request.getParameter("Pseudo"));
			model.getafficher().setPrenom(request.getParameter("Prenom"));
			model.getafficher().setTelephone(request.getParameter("Telephone"));
			model.getafficher().setCodePostal(request.getParameter("CodePostal"));
			model.getafficher().setNom(request.getParameter("Nom"));
			model.getafficher().setEmail(request.getParameter("Email"));
			model.getafficher().setRue(request.getParameter("rue"));
			model.getafficher().setVille(request.getParameter("Ville"));

			try {
				manager.addUtilisateur(model.getafficher());
				model.setLstUtilisateur(manager.getAllUtilisateurs());
			} catch (BLLException e) {
				e.printStackTrace();
			}

		}

		request.setAttribute("model", model);
		request.getRequestDispatcher("/WEB-INF/Afficher.jsp").forward(request, response);
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
