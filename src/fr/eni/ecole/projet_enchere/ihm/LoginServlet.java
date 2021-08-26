package fr.eni.ecole.projet_enchere.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.projet_enchere.bll.BLLException;
import fr.eni.ecole.projet_enchere.bll.BllFactory;
import fr.eni.ecole.projet_enchere.bll.UtilisateurManager;
import fr.eni.ecole.projet_enchere.bo.Utilisateur;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager utilisateurManager = BllFactory.getUniqueUtilisateurManager();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		if (logModel == null) {
			logModel = new LoginModel(new Utilisateur("", "", "", "", "", "", "", "", "", 0, false, true));
		}
		String nextPage = "/WEB-INF/login.jsp";

		if ("Connexion".equals(request.getParameter("formulaireLogin"))) {
			if (request.getParameter("identifiant").matches("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+")) {
				logModel.getUtilisateur().setEmail(request.getParameter("identifiant"));
			} else {
				logModel.getUtilisateur().setPseudo(request.getParameter("identifiant"));
			}

			logModel.getUtilisateur().setMotDePasse(request.getParameter("password"));

			try {
				if (utilisateurManager.logAndPassChecked(logModel.getUtilisateur())) {
					nextPage = (String) request.getSession().getAttribute("previousPage");
					logModel.setUtilisateur(utilisateurManager.getUtilisateur(logModel.getUtilisateur()));
					if ("true".equals(request.getParameter("checkRememberMe"))) {
						Cookie identCookie = new Cookie("identifiant", request.getParameter("identifiant"));
						Cookie passwCookie = new Cookie("password", request.getParameter("password"));

						identCookie.setMaxAge(3600);
						passwCookie.setMaxAge(3600);

						response.addCookie(identCookie);
						response.addCookie(passwCookie);
					}
				}
			} catch (BLLException e) {
				errModel.setErrMessages("ErrLog", e.getMessages());
			}
		}
		if ("Creer".equals(request.getParameter("formulaireCreate"))) {
			nextPage = "/InsererProfilServlet";
		}

		request.setAttribute("errModel", errModel);
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
