package fr.eni.ecole.projet_enchere.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.eni.ecole.projet_enchere.bll.BLLException;
import fr.eni.ecole.projet_enchere.bll.EnchereManager;
import fr.eni.ecole.projet_enchere.bll.EnchereManagerSingl;

/**
 * Servlet implementation class AccueilServlet
 */
@WebServlet({"/","/AccueilServlet"})
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EnchereManager manager = EnchereManagerSingl.getInstance();

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
		try {
			model = new AccueilModel(manager.getAllEnchere());
		} catch (BLLException e) {
			errModel.setErrMessage("ErrAcc", e.getMessage());
		}
		
		request.setAttribute("model", model);
		request.setAttribute("errModel", errModel);
		request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
