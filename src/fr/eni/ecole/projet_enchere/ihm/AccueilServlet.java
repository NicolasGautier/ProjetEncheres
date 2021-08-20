package fr.eni.ecole.projet_enchere.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.projet_enchere.bll.BLLException;
import fr.eni.ecole.projet_enchere.bll.BllFactory;
import fr.eni.ecole.projet_enchere.bll.CategorieManager;
import fr.eni.ecole.projet_enchere.bll.EnchereManager;
import fr.eni.ecole.projet_enchere.bo.Categorie;
import fr.eni.ecole.projet_enchere.bo.Utilisateur;

/**
 * Servlet implementation class AccueilServlet
 */
@WebServlet({ "/AccueilServlet" })
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EnchereManager enchManager = BllFactory.getUniqueEnchereManager();
	private CategorieManager catManager = BllFactory.getUniqueCategorieManager();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccueilServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = "/WEB-INF/accueil.jsp";
		ErreurModel errModel = new ErreurModel();
		LoginModel logModel = (LoginModel) request.getSession().getAttribute("logModel");
		if (logModel == null) {
			logModel = new LoginModel(new Utilisateur("", "", "", "", "", "", "", "", "", 0, false, true));
		}
		AccueilModel accModel = (AccueilModel) request.getAttribute("accModel");
		if (accModel == null) {
			try {
				accModel = new AccueilModel("", new Categorie(), catManager.getAllCategorie());
			} catch (BLLException e) {
				errModel.setErrMessage("ErrAcc", e.getMessage());
			}
		}
		if (request.getParameter("filtre") != null) {
			accModel.setFiltre(request.getParameter("filtre"));
		}
		
		if (logModel.getUtilisateur().getNoUtilisateur() == null) {
			try {
				Boolean catChoisie = false;
				for(Categorie categorie : accModel.getLstCategorie()) {
					if (categorie.getLibelle().equals(request.getParameter("categorieSelect"))) {
						accModel.setLstEnchere(enchManager.getEnchereCategorieFiltre(categorie, accModel.getFiltre()));
						accModel.setCategorie(categorie);
						catChoisie = true;
						break;
					}
				}
				if(!catChoisie) accModel.setLstEnchere(enchManager.getEnchereFiltre(accModel.getFiltre()));
			} catch (BLLException e) {
				errModel.setErrMessage("ErrAcc", e.getMessage());
			}
		} else {	// TODO Faire la fenêtre en connexion
			try {
				Boolean catChoisie = false;
				for(Categorie categorie : accModel.getLstCategorie()) {
					if (categorie.getLibelle().equals(request.getParameter("categorieSelect"))) {
						accModel.setLstEnchere(enchManager.getEnchereCategorieFiltre(categorie, accModel.getFiltre()));
						accModel.setCategorie(categorie);
						catChoisie = true;
						break;
					}
				}
				if(!catChoisie) accModel.setLstEnchere(enchManager.getEnchereFiltre(accModel.getFiltre()));
			} catch (BLLException e) {
				errModel.setErrMessage("ErrAcc", e.getMessage());
			}
		}

		request.setAttribute("accModel", accModel);
		request.setAttribute("errModel", errModel);
		request.getSession().setAttribute("previousPage", "AccueilServlet");
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
