package fr.eni.ecole.projet_enchere.ihm;

public class NewPasswordModel {

	private String mail;
	private String password;
	private String confirmation;
	
	public NewPasswordModel() {
		super();
	}

	public NewPasswordModel(String mail, String password, String confirmation) {
		super();
		this.mail = mail;
		this.password = password;
		this.confirmation = confirmation;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}

	@Override
	public String toString() {
		return "NewPasswordModel [mail=" + mail + ", password=" + password + ", confirmation=" + confirmation + "]";
	}

}
