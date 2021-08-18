package fr.eni.ecole.projet_enchere.ihm;

import java.util.ArrayList;
import java.util.List;
import fr.eni.ecole.projet_enchere.bo.Enchere;

public class AccueilModel {

private List<Enchere> lstEnchere = new ArrayList<Enchere>(); 

public AccueilModel() {
}

public AccueilModel(List<Enchere> lstEnchere) {
	super();
	this.lstEnchere = lstEnchere;
}

public List<Enchere> getLstEnchere() {
	return lstEnchere;
}

public void setLstEnchere(List<Enchere> lstEnchere) {
	this.lstEnchere = lstEnchere;
}

@Override
public String toString() {
	return "AccueilModel [lstEnchere=" + lstEnchere + "]";
}

}
