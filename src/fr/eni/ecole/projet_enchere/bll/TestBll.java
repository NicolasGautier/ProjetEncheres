package fr.eni.ecole.projet_enchere.bll;

import fr.eni.ecole.projet_enchere.bo.Utilisateur;
import fr.eni.ecole.projet_enchere.dal.UtilisateurDAO;
import fr.eni.ecole.projet_enchere.dal.UtilisateurDAOFact;

public class TestBll {

	public static void main(String[] args) throws Exception {
		
		UtilisateurManager manager = UtilisateurManagerFact.getInstance();
		
		System.out.println("TEST 1 : INSERTION DE 3 USERS");
		Utilisateur user1 = new Utilisateur(1,"jaja", "Dupont", "Jeannine", "jaja43@omail.com", "0650505050", "rue du port", "44400","reze", "123456", 0, false);
		Utilisateur user2 = new Utilisateur(2, "guili", "Denemours", "Guillaume", "guiliguili508@omail.com", "0651515151", "rue du géant", "44000","nantes", "guili123", 0, false);
		Utilisateur user3 = new Utilisateur(3, "stephlapoisse", "LeChat", "Stephane", "steph39@omail.com", "0652525252", "rue de l'échelle", "44500","machecoul", "lechat456", 0, false);

		
		manager.insert(user1);
		manager.insert(user2);
		manager.insert(user3);
		
		System.out.println(manager.selectAll());
		
//		System.out.println("TEST 2 : INSERTION D'un user avec le même login et mail");
//		manager.insert(new Utilisateur(4,"jaja", "Dupont", "Jeannine", "jaja43@omail.com", "0650505050", "rue du port", "44400","reze", "123456", 0, false));
//		
//		System.out.println("TEST 3 : INSERTION D'un user avec un login non alphanumérique");
//		manager.insert(new Utilisateur(5,"§/%/", "DOpont", "JeanninOe", "jajOa43@omail.com", "06505505050", "rOue du port", "40400","rezOe", "1234567", 0, false));
//		
		System.out.println("TEST 4 : verif de l'existence du login et du mot de passe en base de donnée");
		System.out.println(manager.logAndPassChecked(user3));
		
		System.out.println("TEST 5 : vérif de l'existence d'identifiant utilisateur et mot de passe base de donnée");
		System.out.println(manager.logAndPassChecked(user3));
		
	}

}
