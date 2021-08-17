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


@WebServlet("/UtilisateurServlet")
public class UtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager manager = UtilisateurManagerSingl.getInstance();
	
	 public UtilisateurServlet() {
	        super();
	    }
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {	
System.out.println("hello");
		UtilisateurModel model= null;
		try {
			model = new UtilisateurModel (new Utilisateur(), manager.selectAll());
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	
		if (request.getParameter("nom") != null) {
			model.getUtilisateur().setPseudo(request.getParameter("Pseudo"));
			model.getUtilisateur().setPrenom(request.getParameter("Prenom"));
			model.getUtilisateur().setTelephone(request.getParameter("Telephone"));
			model.getUtilisateur().setCodePostal(request.getParameter("CodePostal"));
			model.getUtilisateur().setMotDePasse(request.getParameter("MotDePasse"));
			model.getUtilisateur().setNom(request.getParameter("Nom"));
			model.getUtilisateur().setEmail(request.getParameter("Email"));
			model.getUtilisateur().setRue(request.getParameter("rue"));
			model.getUtilisateur().setVille(request.getParameter("Ville"));
			//model.getUtilisateur().setConfirmation(request.getParameter("Confirmation"));
			try {
				manager.insert(model.getUtilisateur());
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
	request.getRequestDispatcher("/WEB-INF/insertion.jsp").forward(request, response);


}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(req.getParameter("Pseudo"));
	}

}
