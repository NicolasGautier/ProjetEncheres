package fr.eni.ecole.projet_enchere.bll;

public class TestSingleton {

	public static void main(String[] args) {
		MonSingleton ems1 = MonSingleton.getInstance();
		MonSingleton ems2 = MonSingleton.getInstance();
		
		System.out.println(ems1);
		System.out.println(ems2);
	
	}
}
