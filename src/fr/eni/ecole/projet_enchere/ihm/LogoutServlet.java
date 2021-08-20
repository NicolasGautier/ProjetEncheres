package fr.eni.ecole.projet_enchere.ihm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = "AccueilServlet";
		
		Cookie identCookie = new Cookie("identifiant", "");
		Cookie passwCookie = new Cookie("password", "");
		identCookie.setMaxAge(1); // TODO augmenter le temps de vie des cookies
		passwCookie.setMaxAge(1);
		response.addCookie(identCookie);
		response.addCookie(passwCookie);
		
		request.getSession().setAttribute("logModel", null);
		//request.getRequestDispatcher(nextPage).forward(request, response);
		response.sendRedirect(nextPage);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
