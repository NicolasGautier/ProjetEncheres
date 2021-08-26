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
		String nextPage = "/WEB-INF/modifierProfil.jsp";
		
		if ("enregistrer".equals(request.getParameter("formulaireProfil"))) {
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
				errModel.setErrMessages("ErrLog", e.getMessages());
				
			}
		}

		if ("supprimer".equals(request.getParameter("formulaireProfil"))) {
			try {
				if (utilManager.passChecked(modProfModel.getUtilisateur())) {
					try {
						utilManager.removeUtilisateur(logModel.getUtilisateur());
					} catch (BLLException e) {
						logModel.getUtilisateur().setActif(false);
						utilManager.setUtilisateur(logModel.getUtilisateur());
					}
					nextPage = "/LogoutServlet";
				}
			} catch (BLLException e) {
				errModel.setErrMessages("ErrLog", e.getMessages());
			}
		}

		request.setAttribute("errModel", errModel);
		request.setAttribute("modProfModel", modProfModel);
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
