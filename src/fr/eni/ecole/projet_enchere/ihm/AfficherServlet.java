package fr.eni.ecole.projet_enchere.ihm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.projet_enchere.bll.UtilisateurManager;
import fr.eni.ecole.projet_enchere.bll.UtilisateurManagerSingl;
import fr.eni.ecole.projet_enchere.bo.Utilisateur;

/**
 * Servlet implementation class AfficherServlet
 */
@WebServlet("/AfficherServlet")
public class AfficherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UtilisateurManager manager  = UtilisateurManagerSingl.getInstance();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AfficherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	AfficherModel model= null;
		try {
			model = new AfficherModel (new AfficherModel(), manager.getAllUtilisateurs());
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if (request.getParameter("nom") != null) {
			model.getafficher().setPseudo(request.getParameter("Pseudo"));
			model.getafficher().setPrenom(request.getParameter("Prenom"));
			model.getafficher().setTelephone(request.getParameter("Telephone"));
			model.getafficher().setCodePostal(request.getParameter("CodePostal"));
			model.getafficher().setNom(request.getParameter("Nom"));
			model.getafficher().setEmail(request.getParameter("Email"));
			model.getafficher().setRue(request.getParameter("rue"));
			model.getafficher().setVille(request.getParameter("Ville"));
		
			try {
				manager.addUtilisateur(model.getafficher());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				model.setLstUtilisateur(manager.getAllUtilisateurs());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				}
		
	request.setAttribute("model" , model);
	request.getRequestDispatcher("/WEB-INF/Afficher.jsp").forward(request, response);
			}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
