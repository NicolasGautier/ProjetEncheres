package fr.eni.ecole.projet_enchere.bll;

public class MonSingleton {

	private MonSingleton() {
		super();
	}

	private static MonSingleton instance;
	
	public static MonSingleton getInstance() {
		if(instance == null ) {
			instance = new MonSingleton();
		}
		return instance;
	}
	
}
