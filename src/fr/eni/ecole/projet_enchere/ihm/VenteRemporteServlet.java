package fr.eni.ecole.projet_enchere.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.projet_enchere.bll.BLLException;
import fr.eni.ecole.projet_enchere.bll.BllFactory;
import fr.eni.ecole.projet_enchere.bll.RetraitManager;
import fr.eni.ecole.projet_enchere.bll.UtilisateurManager;
import fr.eni.ecole.projet_enchere.bo.EtatsVente;

/**
 * Servlet implementation class VenteRemporteServlet
 */
@WebServlet("/VenteRemporteServlet")
public class VenteRemporteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RetraitManager retManager = BllFactory.getUniqueRetraitManager();
	//private ArticleVenduManager artVendManager = BllFactory.getUniqueArticleVenduManager();
	private UtilisateurManager utilManager = BllFactory.getUniqueUtilisateurManager();
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VenteRemporteServlet() {
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
		VenteRemporteModel ventRempModel = null;
		try {
			ventRempModel = new VenteRemporteModel(retManager.getRetrait(Integer.parseInt(request.getParameter("id"))));
			//ventRempModel.setArticleVendu(artVendManager.getArticleVendu(ventRempModel.getRetrait().getArticleVendu()));
		} catch (NumberFormatException e) {
			errModel.setErrMessage("ErrPar", "Paramètre incorrecte");
		} catch (BLLException e) {
			errModel.setErrMessage("ErrGet", e.getMessage());
		}
		String nextPage = "/WEB-INF/venteRemporte.jsp";
		
		try {
			utilManager.articleRemporteChecked(logModel.getUtilisateur(), ventRempModel.getRetrait().getArticleVendu());
		} catch (BLLException e) {
			nextPage = "/AccueilServlet";
		} catch (Exception e) {
			nextPage = "/AccueilServlet";
		}
		
		if ("back".equals(request.getParameter("formulaireBack"))) {
			nextPage = (String) request.getSession().getAttribute("previousPage");
		}
		
		request.setAttribute("errModel", errModel);
		request.setAttribute("ventRempModel", ventRempModel);
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
