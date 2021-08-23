package fr.eni.ecole.projet_enchere.ihm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.projet_enchere.bll.BllFactory;
import fr.eni.ecole.projet_enchere.bll.EnchereManager;
import fr.eni.ecole.projet_enchere.bll.UtilisateurManager;
import fr.eni.ecole.projet_enchere.bo.ArticleVendu;
import fr.eni.ecole.projet_enchere.bo.Utilisateur;

/**
 * Servlet implementation class DetailVenteServlet
 */
@WebServlet("/DetailVenteServlet")
public class DetailVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EnchereManager enchereManager = BllFactory.getUniqueEnchereManager();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailVenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ErreurModel errModel = new ErreurModel();
		DetailVenteModel detailvente = null;
		String nextPage = "/WEB-INF/detailvente.jsp";
		
		//detailvente = new DetailVenteModel(new ArticleVendu("", "", "", "", "", "", "", 0, false,true));
		
		if ("enchere".equals(request.getParameter("enchere1"))) {
			
		
//		detailvente.getArticleVendu().setMiseAPrix(request.getParameter("vente"));
			
		
		
			request.setAttribute("errModel", errModel);
			request.setAttribute("detailvente", detailvente);

			request.getRequestDispatcher(nextPage).forward(request, response);
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
