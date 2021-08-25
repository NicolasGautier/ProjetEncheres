package fr.eni.ecole.projet_enchere.ihm;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.projet_enchere.bll.ArticleVenduManager;
import fr.eni.ecole.projet_enchere.bll.BLLException;
import fr.eni.ecole.projet_enchere.bll.BllFactory;
import fr.eni.ecole.projet_enchere.bll.CategorieManager;
import fr.eni.ecole.projet_enchere.bll.RetraitManager;
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
	private RetraitManager retManager = BllFactory.getUniqueRetraitManager();

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
		IHMException exception = new IHMException();
		// IHM nouvelle vente en MVC
		String nextPage = "/WEB-INF/newarticle.jsp";

		// Initialisatiion des modèles et de la page
		ErreurModel errModel = new ErreurModel();
		LoginModel logModel = (LoginModel) request.getSession().getAttribute("logModel");
		NewArticleModel newArtModel = null;
		// Model initialisé avec des données à setter

		if (request.getParameter("id") == null) {
			try {
				newArtModel = new NewArticleModel(
						new ArticleVendu("", "", LocalDateTime.now(), LocalDateTime.now(), 0, 0, EtatsVente.CREEE,
								logModel.getUtilisateur(), logModel.getUtilisateur(), null, null),
						new Retrait("", "", ""), catManager.getAllCategorie(), false);
			} catch (BLLException e) {
				errModel.setErrMessages("errCha", e.getMessages());
			}
		} else {
			Integer id = Integer.parseInt(request.getParameter("id"));
			try {
				newArtModel = new NewArticleModel(artManager.getArticleVendu(id), retManager.getRetrait(id),
						catManager.getAllCategorie(),true);
			} catch (BLLException e) {
				errModel.setErrMessages("errCha", e.getMessages());
			}
		}

		if ("annuler".equals(request.getParameter("annuler"))) {
			nextPage = (String) request.getSession().getAttribute("previousPage");
		}

		if ("enregistrer".equals(request.getParameter("enregistrer"))) {

			Integer sprix = null;
			try {
				sprix = Integer.parseInt((request.getParameter("sprix")));
			} catch (NumberFormatException e) {
				exception.ajoutMessage("Vous devez rentrer un nombre");
			}

			System.out.println(request.getParameter("dateDebutEncheres"));
			System.out.println(LocalDateTime.parse(request.getParameter("dateDebutEncheres")));// ,DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm")));

			newArtModel.getArticleVendu().setNomArticle(request.getParameter("nomArticle"));
			newArtModel.getArticleVendu().setDescription(request.getParameter("description").trim());
			newArtModel.getArticleVendu().setMiseAPrix(sprix);
			newArtModel.getArticleVendu().setPrixVente(sprix);
			newArtModel.getArticleVendu()
					.setDateDebutEncheres(LocalDateTime.parse(request.getParameter("dateDebutEncheres")));
			newArtModel.getArticleVendu()
					.setDateFinEncheres(LocalDateTime.parse(request.getParameter("dateFinEncheres")));
			for (Categorie categorie : newArtModel.getLstCategorie()) {
				if (categorie.getLibelle().equals(request.getParameter("categorieSelect"))) {
					newArtModel.getArticleVendu().setCategorie(categorie);
				}
			}
			newArtModel.getRetrait().setRue(request.getParameter("rue"));
			newArtModel.getRetrait().setCode_postal(request.getParameter("code_postal"));
			newArtModel.getRetrait().setVille(request.getParameter("ville"));

			if (exception.estVide()) {
				try {
					artManager.addArticleVendu(newArtModel.getArticleVendu());
					newArtModel.getRetrait().setArticleVendu(newArtModel.getArticleVendu());
					System.out.println(newArtModel.getArticleVendu());
					System.out.println(newArtModel.getRetrait());
					retManager.addRetrait(newArtModel.getRetrait());
				} catch (BLLException e) {
					errModel.setErrMessages("ErrIns", e.getMessages());
				}
			} else {
				errModel.setErrMessages("ErrIns", exception.getMessages());
			}
		}
		
		if ("annulerLaVente".equals(request.getParameter("annulerLaVente"))) {
			
		}
		
		request.setAttribute("errModel", errModel);
		request.setAttribute("newArtModel", newArtModel);
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
