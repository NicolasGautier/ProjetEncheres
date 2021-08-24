package fr.eni.ecole.projet_enchere.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.projet_enchere.bll.ArticleVenduManager;
import fr.eni.ecole.projet_enchere.bll.BLLException;
import fr.eni.ecole.projet_enchere.bll.BllFactory;
import fr.eni.ecole.projet_enchere.bll.RetraitManager;
import fr.eni.ecole.projet_enchere.bll.UtilisateurManager;
import fr.eni.ecole.projet_enchere.bo.EtatsVente;

/**
 * Servlet implementation class RetraitValideServlet
 */
@WebServlet("/RetraitValideServlet")
public class RetraitValideServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RetraitManager retManager = BllFactory.getUniqueRetraitManager();
	private UtilisateurManager utilManager = BllFactory.getUniqueUtilisateurManager();
	private ArticleVenduManager artVendManager = BllFactory.getUniqueArticleVenduManager();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetraitValideServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ErreurModel errModel = new ErreurModel();
		LoginModel logModel = (LoginModel) request.getSession().getAttribute("logModel");
		RetraitValideModel retValModel = null;
		try {
			retValModel = new RetraitValideModel(retManager.getRetrait(Integer.parseInt(request.getParameter("id"))));
		} catch (NumberFormatException e) {
			//errModel.setErrMessages("ErrPar", "Paramètre incorrecte");
		} catch (BLLException e) {
			errModel.setErrMessages("ErrGet", e.getMessages());
		}
		String nextPage = "/WEB-INF/retraitValide.jsp";
		
		try {
			utilManager.retraitValideChecked(logModel.getUtilisateur(), retValModel.getRetrait().getArticleVendu());
		} catch (BLLException e) {
			nextPage = "/AccueilServlet";
		} catch (Exception e) {
			nextPage = "/AccueilServlet";
		}
		
		if ("retEff".equals(request.getParameter("formulaireRetEff"))) {
			retValModel.getRetrait().getArticleVendu().setEtatVente(EtatsVente.RETRAIT_EFFECTUE);
						try {
				artVendManager.setArticleVendu(retValModel.getRetrait().getArticleVendu());
			} catch (BLLException e) {
				e.printStackTrace();
			}
			nextPage = (String) request.getSession().getAttribute("previousPage");
		}
		
		request.setAttribute("errModel", errModel);
		request.setAttribute("retValModel", retValModel);
		request.getRequestDispatcher(nextPage).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
