package fr.eni.ecole.projet_enchere.ihm;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

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
@MultipartConfig
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
		Boolean retourAccueil = false;
		// Model initialisé avec des données à setter

		if (request.getParameter("id") == null) {
			try {
				newArtModel = new NewArticleModel(
						new ArticleVendu("", "", LocalDateTime.now(), LocalDateTime.now(), 0, 0, EtatsVente.CREEE,
								logModel.getUtilisateur(), logModel.getUtilisateur(), null, null),
						new Retrait(logModel.getUtilisateur().getRue(), logModel.getUtilisateur().getCodePostal(),
								logModel.getUtilisateur().getVille()),
						catManager.getAllCategorie(), new Categorie(-1, ""), false);
			} catch (BLLException e) {
				errModel.setErrMessages("errCha", e.getMessages());
			}
		} else {
			Integer id = null;
			try {
				id = Integer.parseInt(request.getParameter("id"));
				try {
					newArtModel = new NewArticleModel(artManager.getArticleVendu(id), retManager.getRetrait(id),
							catManager.getAllCategorie(), artManager.getArticleVendu(id).getCategorie(), true);
				} catch (BLLException e) {
					errModel.setErrMessages("errCha", e.getMessages());
					exception.ajoutMessages(e.getMessages());
				}
			} catch (NumberFormatException e) {
				exception.ajoutMessage("id incorrecte");
				try {
					newArtModel = new NewArticleModel(
							new ArticleVendu("", "", LocalDateTime.now(), LocalDateTime.now(), 0, 0, EtatsVente.CREEE,
									logModel.getUtilisateur(), logModel.getUtilisateur(), null, null),
							new Retrait("", "", ""), catManager.getAllCategorie(), new Categorie(-1, ""), false);
				} catch (BLLException e1) {
					errModel.setErrMessages("errCha", e1.getMessages());
					exception.ajoutMessages(e1.getMessages());
				}
			}
		}

		if ("annuler".equals(request.getParameter("annuler"))) {
			retourAccueil = true;
		}

		if ("enregistrer".equals(request.getParameter("enregistrer"))) {
			Integer sprix = null;
			try {
				sprix = Integer.parseInt((request.getParameter("sprix")));
			} catch (NumberFormatException e) {
				exception.ajoutMessage("Vous devez rentrer un nombre");
			}
			Integer catSel = null;
			if (request.getParameter("categorieSelect") != null) {
				catSel = Integer.parseInt(request.getParameter("categorieSelect"));
			} else {
				catSel = -1;
			}

			newArtModel.getArticleVendu().setNomArticle(request.getParameter("nomArticle"));
			newArtModel.getArticleVendu().setDescription(request.getParameter("description").trim());
			newArtModel.getArticleVendu().setMiseAPrix(sprix);
			newArtModel.getArticleVendu().setPrixVente(sprix);
			newArtModel.getArticleVendu()
					.setDateDebutEncheres(LocalDateTime.parse(request.getParameter("dateDebutEncheres")));
			newArtModel.getArticleVendu()
					.setDateFinEncheres(LocalDateTime.parse(request.getParameter("dateFinEncheres")));
			for (Categorie categorie : newArtModel.getLstCategorie()) {
				if (categorie.getNoCategorie().equals(catSel)) {
					newArtModel.getArticleVendu().setCategorie(categorie);
				}
			}
			
			
			
			
			
			
			
			
			Part filePart = request.getPart("photoarticle"); // Retrieves <input type="file" name="file">
		    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
		    InputStream fileContent = filePart.getInputStream();
			
			

			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			newArtModel.getRetrait().setRue(request.getParameter("rue"));
			newArtModel.getRetrait().setCode_postal(request.getParameter("code_postal"));
			newArtModel.getRetrait().setVille(request.getParameter("ville"));

			if (exception.estVide()) {
				try {
					if (newArtModel.getArticleVendu().getNoArticle() != null) {
						artManager.setArticleVendu(newArtModel.getArticleVendu());
					} else {
						artManager.addArticleVendu(newArtModel.getArticleVendu());
					}
					if (newArtModel.getRetrait().getArticleVendu() != null) {
						retManager.setRetrait(newArtModel.getRetrait());
					} else {
						newArtModel.getRetrait().setArticleVendu(newArtModel.getArticleVendu());
						retManager.addRetrait(newArtModel.getRetrait());
					}
					retourAccueil = true;
				} catch (BLLException e) {
					exception.ajoutMessages(e.getMessages());
				}
			}
		}

		if ("annulerLaVente".equals(request.getParameter("annulerLaVente"))) {
			if (exception.estVide()) {
				try {
					retManager.removeRetrait(newArtModel.getRetrait());
					artManager.removeArticleVendu(newArtModel.getArticleVendu());
					retourAccueil = true;
				} catch (BLLException e) {
					exception.ajoutMessages(e.getMessages());
				}
			}
		}

		if (!exception.estVide()) {
			errModel.setErrMessages("ErrIns", exception.getMessages());
		}
		
		request.setAttribute("errModel", errModel);
		request.setAttribute("newArtModel", newArtModel);

		if (retourAccueil) {
			response.sendRedirect("AccueilServlet");
		} else {
			request.getRequestDispatcher(nextPage).forward(request, response);
		}
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
