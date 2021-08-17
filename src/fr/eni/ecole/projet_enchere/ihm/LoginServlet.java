package fr.eni.ecole.projet_enchere.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.projet_enchere.bll.BLLException;
import fr.eni.ecole.projet_enchere.bll.UtilisateurManager;
import fr.eni.ecole.projet_enchere.bll.UtilisateurManagerFact;
import fr.eni.ecole.projet_enchere.bo.Utilisateur;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager utilisateurManager = UtilisateurManagerFact.getInstance();

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
		LoginModel logModel = (LoginModel) request.getSession().getAttribute("modelLog");
		if (logModel == null) {
			logModel = new LoginModel(new Utilisateur("", "", "", "", "", "", "", "", "", 0, false));
		}
		String nextPage = "/WEB-INF/login.jsp";

		if ("Connexion".equals(request.getParameter("formulaireLogin"))) {
			// TODO Vérifier si l'identifiant est un pseudo ou un email
			if(request.getParameter("identifiant").matches("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+")) {
				logModel.getUtilisateur().setEmail(request.getParameter("identifiant"));
			} else {
				logModel.getUtilisateur().setPseudo(request.getParameter("identifiant"));
			}
			
			logModel.getUtilisateur().setMotDePasse(request.getParameter("password"));

			try {
				if (utilisateurManager.logAndPassChecked(logModel.getUtilisateur())) {
					nextPage = "#"; // TODO Renvoyer à la page d'acceuille
					logModel.setUtilisateur(utilisateurManager.getUtilisateur(logModel.getUtilisateur()));
					if ("true".equals(request.getParameter("checkRememberMe"))) {
						Cookie identCookie = new Cookie("identifiant", request.getParameter("identifiant"));
						Cookie passwCookie = new Cookie("password", request.getParameter("password"));

						identCookie.setMaxAge(30); // TODO augmenter le temps de vie des cookies
						passwCookie.setMaxAge(30);

						response.addCookie(identCookie);
						response.addCookie(passwCookie);
					}
				}
			} catch (BLLException e) {
				errModel.setErrMessage("ErrLog", e.getMessage());
			}
		}
		if ("Creer".equals(request.getParameter("formulaireCreate"))) {

		}
		System.out.println(logModel);
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
