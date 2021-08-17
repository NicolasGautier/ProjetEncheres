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
 * Servlet implementation class afficherProfilServlet
 */
@WebServlet("/ModifierProfilServlet")
public class ModifierProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager manager = UtilisateurManagerSingl.getInstance();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierProfilServlet() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UtilisateurModel model= null;
		try {
			model = new UtilisateurModel (new Utilisateur(), manager.selectAll());
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	
		if (request.getParameter("nom") != null) {
			model.getModifier().setPseudo(request.getParameter("Pseudo"));
			model.getModifier().setPrenom(request.getParameter("Prenom"));
			model.getModifier().setTelephone(request.getParameter("Telephone"));
			model.getModifier().setCodePostal(request.getParameter("CodePostal"));
			model.getModifier().setMotDePasse(request.getParameter("MotDePasse"));
			model.getModifier().setMotDePasse(request.getParameter("NouveauMotDePasse"));
			model.getModifier().setNom(request.getParameter("Nom"));
			model.getModifier().setEmail(request.getParameter("Email"));
			model.getModifier().setRue(request.getParameter("rue"));
			model.getModifier().setVille(request.getParameter("Ville"));
			//model.getUtilisateur().setConfirmation(request.getParameter("Confirmation"));
			try {
				manager.insert(model.getModifier());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				model.setLstUtilisateur(manager.selectAll());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	}
		
	request.setAttribute("model" , model);
	request.getRequestDispatcher("/WEB-INF/ModifierProfil.jsp").forward(request, response);


		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
