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
import fr.eni.ecole.projet_enchere.bo.Utilisateur;

/**
 * Servlet implementation class NewPasswordServlet
 */
@WebServlet("/NewPasswordServlet")
public class NewPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager utilManager = BllFactory.getUniqueUtilisateurManager();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewPasswordServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/newPassword.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ErreurModel errModel = new ErreurModel();
		NewPasswordModel newPWModel = (NewPasswordModel) request.getAttribute("newPWModel");
		if (newPWModel == null) {
			newPWModel = new NewPasswordModel("", "", "");
		}
		String nextPage = "/WEB-INF/newPassword.jsp";

		if ("envoyer".equals(request.getParameter("formulaireNewPassword"))) {

			try {
				if (utilManager.newPassChecked(request.getParameter("password"),
						request.getParameter("confirmation"))) {
					utilManager.setUtilisateurPW(new Utilisateur("", "", "", request.getParameter("mail"), "", "", "",
							"", request.getParameter("password"), 0, false, true));
					nextPage = "AccueilServlet";
				}
			} catch (BLLException e) {
				errModel.setErrMessages("ErrMod", e.getMessages());
			}
		}

		request.getSession().setAttribute("newPWModel", newPWModel);
		request.getSession().setAttribute("errModel", errModel);
		request.getRequestDispatcher(nextPage).forward(request, response);
	}

}
