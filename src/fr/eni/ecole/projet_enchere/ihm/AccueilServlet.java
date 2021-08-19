package fr.eni.ecole.projet_enchere.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.projet_enchere.bll.BLLException;
import fr.eni.ecole.projet_enchere.bll.BllFactory;
import fr.eni.ecole.projet_enchere.bll.EnchereManager;

/**
 * Servlet implementation class AccueilServlet
 */
@WebServlet({"/AccueilServlet"})
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EnchereManager manager = BllFactory.getUniqueEnchereManager();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccueilServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ErreurModel errModel = new ErreurModel();
		//Initialisation de model
		AccueilModel model = null;
		String nextPage = "/WEB-INF/accueil.jsp";
		
		try {
			model = new AccueilModel(manager.getAllEnchere());
		} catch (BLLException e) {
			errModel.setErrMessage("ErrAcc", e.getMessage());
		}
		
		request.setAttribute("model", model);
		request.setAttribute("errModel", errModel);
		request.getSession().setAttribute("previousPage", nextPage);
		request.getRequestDispatcher(nextPage).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
