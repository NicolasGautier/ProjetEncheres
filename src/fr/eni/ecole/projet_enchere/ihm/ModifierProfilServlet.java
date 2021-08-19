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
 * Servlet implementation class afficherProfilServlet
 */
@WebServlet("/ModifierProfilServlet")
public class ModifierProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager utilManager = BllFactory.getUniqueUtilisateurManager();

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

		ErreurModel errModel = new ErreurModel();
		LoginModel logModel = (LoginModel) request.getSession().getAttribute("logModel");
		ModifierProfilModel modProfModel = new ModifierProfilModel(logModel.getUtilisateur());
		String nextPage = "/WEB-INF/ModifierProfil.jsp";

		if ("enregistrer".equals(request.getParameter("formuaireProfil"))) {
			modProfModel.getUtilisateur().setPseudo(request.getParameter("pseudo"));
			modProfModel.getUtilisateur().setPrenom(request.getParameter("prenom"));
			modProfModel.getUtilisateur().setTelephone(request.getParameter("telephone"));
			modProfModel.getUtilisateur().setCodePostal(request.getParameter("cp"));
			modProfModel.getUtilisateur().setMotDePasse(request.getParameter("password"));
			modProfModel.getUtilisateur().setNom(request.getParameter("nom"));
			modProfModel.getUtilisateur().setEmail(request.getParameter("email"));
			modProfModel.getUtilisateur().setRue(request.getParameter("rue"));
			modProfModel.getUtilisateur().setVille(request.getParameter("ville"));

			try {
				if (utilManager.passChecked(modProfModel.getUtilisateur())) {
					if (!"".equals(request.getParameter("newPassword"))
							|| !"".equals(request.getParameter("confPassword"))) {
						if (utilManager.newPassChecked(request.getParameter("newPassword"),
								request.getParameter("confPassword"))) {
							modProfModel.getUtilisateur().setMotDePasse(request.getParameter("newPassword"));
						}
					}
				}
				utilManager.setUtilisateur(modProfModel.getUtilisateur());
				logModel.setUtilisateur(utilManager.getUtilisateur(modProfModel.getUtilisateur()));
			} catch (BLLException e) {
				errModel.setErrMessage("ErrLog", e.getMessage());
			}
		}

		if ("supprimer".equals(request.getParameter("formuaireProfil"))) {
			try {
				if (utilManager.passChecked(modProfModel.getUtilisateur())) {
					try {
						utilManager.removeUtilisateur(logModel.getUtilisateur());
					} catch (BLLException e) {
						logModel.getUtilisateur().setActif(false);
						utilManager.setUtilisateur(logModel.getUtilisateur());
					}
					nextPage = "/AccueilServlet";
				}
			} catch (BLLException e) {
				errModel.setErrMessage("ErrLog", e.getMessage());
			}
		}

		request.setAttribute("errModel", errModel);
		request.setAttribute("model", modProfModel);
		request.getSession().setAttribute("logModel", logModel);

		request.getRequestDispatcher(nextPage).forward(request, response);

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
