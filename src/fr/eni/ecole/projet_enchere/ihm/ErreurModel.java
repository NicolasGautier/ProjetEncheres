package fr.eni.ecole.projet_enchere.ihm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ErreurModel {
	private Map<String, List<String>> errMessage = new HashMap<String, List<String>>();

	public ErreurModel() {
		super();
	}

	public Map<String, List<String>> getErrMessages() {
		return errMessage;
	}

	public void setErrMessages(String key, List<String> errMessages) {
		this.errMessage.put(key, errMessages);
	}
	
	public Set<String> getErr() {
		return this.errMessage.keySet();
	}
	
}
