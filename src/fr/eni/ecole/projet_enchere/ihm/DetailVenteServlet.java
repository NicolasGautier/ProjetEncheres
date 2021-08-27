package fr.eni.ecole.projet_enchere.ihm;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.projet_enchere.bll.ArticleVenduManager;
import fr.eni.ecole.projet_enchere.bll.BLLException;
import fr.eni.ecole.projet_enchere.bll.BllFactory;
import fr.eni.ecole.projet_enchere.bll.EnchereManager;
import fr.eni.ecole.projet_enchere.bll.RetraitManager;
import fr.eni.ecole.projet_enchere.bll.UtilisateurManager;
import fr.eni.ecole.projet_enchere.bo.Enchere;
import fr.eni.ecole.projet_enchere.bo.EtatsVente;

/**
 * Servlet implementation class DetailVenteServlet
 */
@WebServlet("/DetailVenteServlet")
public class DetailVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleVenduManager artVendManager = BllFactory.getUniqueArticleVenduManager();
	private RetraitManager retManager = BllFactory.getUniqueRetraitManager();
	private UtilisateurManager utilManager = BllFactory.getUniqueUtilisateurManager();
	private EnchereManager enchManager = BllFactory.getUniqueEnchereManager();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DetailVenteServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		IHMException exception = new IHMException();
		ErreurModel errModel = new ErreurModel();
		LoginModel logModel = (LoginModel) request.getSession().getAttribute("logModel");
		DetailVenteModel detVentModel = null;
		try {
			detVentModel = new DetailVenteModel(
					artVendManager.getArticleVendu(Integer.parseInt(request.getParameter("id"))),
					retManager.getRetrait(Integer.parseInt(request.getParameter("id"))));
			if (detVentModel.getArticleVendu().getEncheres().size() > 0) {
				detVentModel.setMeilleurEnchere(detVentModel.getArticleVendu().getEncheres().stream()
						.sorted((e1, e2) -> e2.getMontant_enchere().compareTo(e1.getMontant_enchere()))
						.collect(Collectors.toList()).get(0));
			} else {
				detVentModel.setMeilleurEnchere(new Enchere(null, detVentModel.getArticleVendu().getMiseAPrix(),
						detVentModel.getArticleVendu().getUtilisateurVend(), detVentModel.getArticleVendu()));
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BLLException e) {
			errModel.setErrMessages("ErrInit", e.getMessages());
		}
		String nextPage = "/WEB-INF/detailvente.jsp";
		Boolean retourAccueil = false;
		if ("encherir".equals(request.getParameter("formulaireEncherir"))) {
			
			Integer proposition = null;
			try {
				proposition = Integer.parseInt(request.getParameter("proposition"));
			} catch (NumberFormatException e) {
				exception.ajoutMessage("Vous devez rentrer un nombre");
			}

			if (exception.estVide()) {
				try {
					if (utilManager.pointsSuffisantsChecked(logModel.getUtilisateur(), proposition,
							detVentModel.getArticleVendu().getEncheres())) {
						utilManager.prendPointUtilisateur(logModel.getUtilisateur(), proposition,
								detVentModel.getArticleVendu().getEncheres());
						enchManager.addEnchere(new Enchere(LocalDateTime.now(), proposition, logModel.getUtilisateur(),
								detVentModel.getArticleVendu()));
						retourAccueil = true;
					}
				} catch (BLLException e) {
					// TODO Auto-generated catch block
					errModel.setErrMessages("ErrIns", e.getMessages());
				}
			} else {
				errModel.setErrMessages("ErrIns", exception.getMessages());
			}
		}

		if (detVentModel.getArticleVendu().getUtilisateurVend().getNoUtilisateur()
				.equals(logModel.getUtilisateur().getNoUtilisateur())
				&& detVentModel.getArticleVendu().getEtatVente().equals(EtatsVente.CREEE)) {
			nextPage = "NewArticleServlet?id=" + request.getParameter("id");
		}

		request.setAttribute("errModel", errModel);
		request.setAttribute("detailVente", detVentModel);

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
