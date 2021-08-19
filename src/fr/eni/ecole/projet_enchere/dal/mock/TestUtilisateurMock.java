package fr.eni.ecole.projet_enchere.dal.mock;

import fr.eni.ecole.projet_enchere.bo.Utilisateur;
import fr.eni.ecole.projet_enchere.dal.DALException;
import fr.eni.ecole.projet_enchere.dal.DalFactory;
import fr.eni.ecole.projet_enchere.dal.UtilisateurDAO;

public class TestUtilisateurMock {
	
	public static void main(String[] args) throws DALException {
		UtilisateurDAO dao = DalFactory.getUtilisateurDAO();
		
		Utilisateur user1 = new Utilisateur("pseudo1", "nom1","prenom1","email1","telephone1","rue1","cp1","ville1","mot_de_passe1",0,false,true);
		Utilisateur user2 = new Utilisateur("pseudo2", "nom2","prenom2","email2","telephone2","rue2","cp2","ville2","mot_de_passe2",0,false,true);
		Utilisateur user3 = new Utilisateur("pseudo3", "nom3","prenom3","email3","telephone3","rue3","cp3","ville3","mot_de_passe3",0,false,true);
				
		dao.insert(user1);
		dao.insert(user2);
		dao.insert(user3);
		
		System.out.println(dao.selectAll());
		
		user1.setPseudo("CocoLastico");
		
		dao.update(user1);
		
		System.out.println(dao.selectAll());
		
		System.out.println(dao.selectById(user1.getNoUtilisateur()));
		
		dao.delete(user1.getNoUtilisateur());
		
		System.out.println(dao.selectAll());
				
	}
		
}
