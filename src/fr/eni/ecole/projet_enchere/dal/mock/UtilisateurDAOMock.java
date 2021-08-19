package fr.eni.ecole.projet_enchere.dal.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import fr.eni.ecole.projet_enchere.bo.Utilisateur;
import fr.eni.ecole.projet_enchere.dal.DALException;
import fr.eni.ecole.projet_enchere.dal.UtilisateurDAO;

public class UtilisateurDAOMock implements UtilisateurDAO {
	private static int cpt = 1;
	public static List<Utilisateur> lst = new ArrayList<Utilisateur>();
	
	@Override
	public void insert(Utilisateur utilisateur) throws DALException {
		lst.add(utilisateur);
		utilisateur.setNoUtilisateur(cpt++);
	}

	@Override
	public void update(Utilisateur utilisateur) throws DALException {
		Integer index = IntStream.range(0, lst.size()).filter(i -> lst.get(i).getNoUtilisateur() == utilisateur.getNoUtilisateur())
				.findFirst().orElse(-1);
		if (index != -1)
			lst.set(index, utilisateur);
	}

	@Override
	public void delete(Integer id) throws DALException {
		lst.removeIf(c -> c.getNoUtilisateur().equals(id));
	}

	@Override
	public List<Utilisateur> selectAll() throws DALException {
		return lst;
	}

	@Override
	public Utilisateur selectById(Integer id) throws DALException {
		return lst.stream().filter(u -> u.getNoUtilisateur() == id).collect(Collectors.toList()).get(0);
	}

}
