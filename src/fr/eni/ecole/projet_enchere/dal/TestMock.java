package fr.eni.ecole.projet_enchere.dal;

import fr.eni.ecole.projet_enchere.bo.Utilisateur;

public class TestMock {
	
	public static void main(String[] args) throws DALException {
		UtilisateurDAO dao = UtilisateurDAOFact.getInstance();
		
		Utilisateur user1 = new Utilisateur();
		Utilisateur user2 = new Utilisateur();
		Utilisateur user3 = new Utilisateur();
		Utilisateur user4 = new Utilisateur();
		Utilisateur user5 = new Utilisateur();
		
		dao.insert(user1);
		dao.insert(user2);
		dao.insert(user3);
		dao.insert(user4);
		
		System.out.println(dao.selectAll());
		
		user1.setPseudo("CocoLastico");
		
		dao.update(user1);
		
		System.out.println(dao.selectAll());
		
		System.out.println(dao.selectById(user1.getNoUtilisateur()));
		
		dao.delete(user1.getNoUtilisateur());
		
		System.out.println(dao.selectAll());
		
		dao.update(user5);
		
		dao.delete(user5.getNoUtilisateur());
		
	}
		
}
