package fr.eni.ecole.projet_enchere.ihm;

import java.io.IOException;
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

		// Elements du modele à setter après récupération des donéées
		LocalDate date = LocalDate.now();
		Categorie informatique = new Categorie(1, "Informatique");
		Retrait retrait = new Retrait(" ", " ", " ");

		// Model initialisé avec des données à setter
		try {
			newArtModel = new NewArticleModel(new ArticleVendu("", "", date, date, 120, 120, EtatsVente.CREEE,
					logModel.getUtilisateur(), logModel.getUtilisateur(), informatique, retrait),
					catManager.getAllCategorie());
		} catch (BLLException e) {
			errModel.setErrMessages("errCha", e.getMessages());
		}

		if ("annuler".equals(request.getParameter("annuler"))) {

			nextPage = (String) request.getSession().getAttribute("previousPage");
			;
		}

		if ("enregistrer".equals(request.getParameter("enregistrer"))) {
			Integer sprix = null;
			try {
				sprix = Integer.parseInt((request.getParameter("sprix")));
			} catch (NumberFormatException e) {
				exception.ajoutMessage("Vous devez rentrer un nombre");
			}

			if (exception.estVide()) {
				// Set des nom description et mise à prix du newArtModel
				newArtModel.getArticleVendu().setNomArticle(request.getParameter("nomarticle"));
				newArtModel.getArticleVendu().setDescription(request.getParameter("description").trim());
				newArtModel.getArticleVendu().setMiseAPrix(sprix);

				// Set de la local date début enchère
				newArtModel.getArticleVendu()
						.setDateDebutEncheres(LocalDate.parse(request.getParameter("datedebutencheres")));
				newArtModel.getArticleVendu()
						.setDateFinEncheres(LocalDate.parse(request.getParameter("datefinencheres")));

				// Set de la catégorie
				for (Categorie categorie : newArtModel.getLstCategorie()) {
					if (categorie.getLibelle().equals(request.getParameter("categorieSelect"))) {
						newArtModel.setCategorie(categorie);
					}
				}

				newArtModel.getArticleVendu().getRetrait().setRue(request.getParameter("rue"));
				newArtModel.getArticleVendu().getRetrait().setCode_postal(request.getParameter("code_postal"));
				newArtModel.getArticleVendu().getRetrait().setVille(request.getParameter("ville"));
				System.out.println(newArtModel.toString());

				try {
					artManager.addArticleVendu(newArtModel.getArticleVendu());

					Retrait retrait2 = new Retrait(newArtModel.getArticleVendu().getRetrait().getRue(),
							newArtModel.getArticleVendu().getRetrait().getCode_postal(),
							newArtModel.getArticleVendu().getRetrait().getVille(), newArtModel.getArticleVendu());

					retManager.addRetrait(retrait2);
				} catch (BLLException e) {
					errModel.setErrMessages("ErrIns", e.getMessages());
				}
			} else {
				errModel.setErrMessages("ErrIns", exception.getMessages());
			}
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
