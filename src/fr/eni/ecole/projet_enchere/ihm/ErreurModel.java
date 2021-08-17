package fr.eni.ecole.projet_enchere.ihm;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ErreurModel {
	private Map<String, String> errMessage = new HashMap<String, String>();

	public ErreurModel() {
		super();
	}

	public Map<String, String> getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String key, String errMessage) {
		this.errMessage.put(key, errMessage);
	}
	
	public Set<String> getErr() {
		return this.errMessage.keySet();
	}
	
}
