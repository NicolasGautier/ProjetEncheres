package fr.eni.ecole.projet_enchere.ihm;

import java.util.ArrayList;
import java.util.List;

public class IHMException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	List<String> messages;

	public IHMException() {
		messages = new ArrayList<String>();
	}

	public void ajoutMessage(String msg) {
		messages.add(msg);
	}

	public List<String> getMessages() {
		return messages;
	}

	public boolean estVide() {
		return messages.isEmpty();
	}
	
}
