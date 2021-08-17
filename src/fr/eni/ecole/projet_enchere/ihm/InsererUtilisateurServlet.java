package fr.eni.ecole.projet_enchere.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.projet_enchere.bll.BLLException;
import fr.eni.ecole.projet_enchere.bll.UtilisateurManager;
import fr.eni.ecole.projet_enchere.bll.UtilisateurManagerSingl;
import fr.eni.ecole.projet_enchere.bo.Utilisateur;

@WebServlet("/InsererUtilisateurServlet")
public class InsererUtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager manager = UtilisateurManagerSingl.getInstance();

	public InsererUtilisateurServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ErreurModel errModel = new ErreurModel();
		InsererUtilisateurModel insModel = null;
		try {
			insModel = new InsererUtilisateurModel(new Utilisateur(), manager.getAllUtilisateurs());
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		if (request.getParameter("nom") != null) {
			insModel.getUtilisateur().setPseudo(request.getParameter("Pseudo"));
			insModel.getUtilisateur().setPrenom(request.getParameter("Prenom"));
			insModel.getUtilisateur().setTelephone(request.getParameter("Telephone"));
			insModel.getUtilisateur().setCodePostal(request.getParameter("CodePostal"));
			insModel.getUtilisateur().setMotDePasse(request.getParameter("MotDePasse"));
			insModel.getUtilisateur().setNom(request.getParameter("Nom"));
			insModel.getUtilisateur().setEmail(request.getParameter("Email"));
			insModel.getUtilisateur().setRue(request.getParameter("rue"));
			insModel.getUtilisateur().setVille(request.getParameter("Ville"));
			// model.getUtilisateur().setConfirmation(request.getParameter("Confirmation"));

			try {
				manager.addUtilisateur(insModel.getUtilisateur());
				insModel.setLstUtilisateur(manager.getAllUtilisateurs());
			} catch (BLLException e) {
				errModel.setErrMessage("ErrIns", e.getMessage());
			}
		}

		request.setAttribute("errModel", errModel);
		request.setAttribute("insModel", insModel);
		
		request.getRequestDispatcher("/WEB-INF/insertion.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
