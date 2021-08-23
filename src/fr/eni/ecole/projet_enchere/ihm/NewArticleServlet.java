package fr.eni.ecole.projet_enchere.ihm;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.projet_enchere.bll.ArticleVenduManager;
import fr.eni.ecole.projet_enchere.bll.BLLException;
import fr.eni.ecole.projet_enchere.bll.BllFactory;
import fr.eni.ecole.projet_enchere.bll.CategorieManager;
import fr.eni.ecole.projet_enchere.bo.ArticleVendu;
import fr.eni.ecole.projet_enchere.bo.Categorie;
import fr.eni.ecole.projet_enchere.bo.EtatsVente;
import fr.eni.ecole.projet_enchere.bo.Retrait;

/**
 * Servlet implementation class NewVenteServlet
 */
@WebServlet("/NewArticleServlet")
public class NewArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleVenduManager artManager = BllFactory.getUniqueArticleVenduManager();
	private CategorieManager catManager = BllFactory.getUniqueCategorieManager();

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public NewArticleServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// IHM nouvelle vente en MVC
		String nextPage = "/WEB-INF/newarticle.jsp";

		// Initialisatiion des modèles et de la page
		ErreurModel errModel = new ErreurModel();
		LoginModel logModel = (LoginModel) request.getSession().getAttribute("logModel");
		NewArticleModel newArtModel = null;

		// TODO récupération de l'identifiant utilisateur
		Integer noUtilisateur = 1;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		// Elements du modele à setter après récupération des donéées
		LocalDate date = LocalDate.now();
		Categorie informatique = new Categorie(1, "Informatique");
		Retrait retrait = new Retrait(" ", " ", " ");

		// Model initialisé avec des données à setter
		try {
			newArtModel = new NewArticleModel(
				new ArticleVendu("", "", date, date, 120, 120, EtatsVente.CREEE, logModel.getUtilisateur(), logModel.getUtilisateur(), informatique, retrait), catManager.getAllCategorie());
		} catch (BLLException e) {
			errModel.setErrMessage("errCha", e.getMessage());
		}
		
		if (request.getParameter("annuler") != null) {

			nextPage = (String) request.getSession().getAttribute("previousPage");;

		} 
		if ("enregistrer".equals(request.getParameter("enregistrer"))) {
			
			// Set du newArtModel
			newArtModel.getArticleVendu().setNomArticle(request.getParameter("nomarticle"));
			newArtModel.getArticleVendu().setDescription(request.getParameter("description"));
			newArtModel.getArticleVendu().setMiseAPrix(Integer.parseInt((request.getParameter("sprix"))));
			 newArtModel.getArticleVendu().setDateDebutEncheres(LocalDate.parse(request.getParameter("datedebutencheres")));
			// TODO FAIRE CATEGORIE
			newArtModel.getArticleVendu().getRetrait().setRue(request.getParameter("rue"));
			newArtModel.getArticleVendu().getRetrait().setCode_postal(request.getParameter("code_postal"));
			newArtModel.getArticleVendu().getRetrait().setVille(request.getParameter("ville"));
			System.out.println(newArtModel.toString());
			
//			try {
//			artManager.addArticleVendu(newArtModel.getArticleVendu());
//		} catch (BLLException e) {
//			// TODO Auto-generated catch block
//			errModel.setErrMessage("ErrIns", e.getMessage());
//		}
		}


		
		request.setAttribute("errModel", errModel);
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
