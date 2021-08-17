package fr.eni.ecole.projet_enchere.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.projet_enchere.bo.Utilisateur;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		ErreurModel errModel = new ErreurModel();
		LoginModel logModel = (LoginModel) request.getSession().getAttribute("modelLog");
		if (logModel==null) {
			logModel = new LoginModel(new Utilisateur("","","","","","","","","",0,false));
		}
		String nextPage = "/WEB-INF/login.jsp";
		
		if ("Connexion".equals(request.getParameter("formulaireLogin"))) {
			logModel.getUtilisateur().setPseudo(request.getParameter("identifiant"));
			logModel.getUtilisateur().setMotDePasse(request.getParameter("password"));
			
			/*try {
				if(loginManager.authentification(logModel.getUtilisateur()))
				nextPage = "/AjouterTodoServlet";
			} catch (BLLException e) {
				errModel.setErrMessage("ErrLog",e.getMessage());
			}*/
		}
		if("Creer".equals(request.getParameter("formulaireCreate"))) {
			
		}
		request.setAttribute("modelErr", errModel);
		request.getSession().setAttribute("modelLog", logModel);
		
		request.getRequestDispatcher(nextPage).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
