package fr.eni.ecole.projet_enchere.dal.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import fr.eni.ecole.projet_enchere.bo.Enchere;
import fr.eni.ecole.projet_enchere.dal.DALException;
import fr.eni.ecole.projet_enchere.dal.EnchereDAO;

public class EnchereDAOMock implements EnchereDAO {
	public static List<Enchere> lst = new ArrayList<>();

	@Override
	public void insert(Enchere enchere) throws DALException {
		lst.add(enchere);
	}

	@Override
	public void update(Enchere enchere) throws DALException {
		Integer index = IntStream.range(0, lst.size()).filter(i -> lst.get(i).getUtilisateurEncherit()
				.getNoUtilisateur() == enchere.getUtilisateurEncherit().getNoUtilisateur()
				&& lst.get(i).getArticleConcerne().getNoArticle() == enchere.getArticleConcerne().getNoArticle())
				.findFirst().orElse(-1);
		if (index != -1)
			lst.set(index, enchere);
	}

	@Override
	public void delete(Integer idUtilisateur, Integer idArticle) throws DALException {
		lst.removeIf(c -> c.getUtilisateurEncherit().getNoUtilisateur().equals(idUtilisateur)
				&& c.getArticleConcerne().getNoArticle().equals(idArticle));
	}

	@Override
	public List<Enchere> selectAll() throws DALException {
		return lst;
	}

	@Override
	public Enchere selectById(Integer idUtilisateur, Integer idArticle) throws DALException {
		return lst.stream().filter(u -> u.getUtilisateurEncherit().getNoUtilisateur().equals(idUtilisateur)
				&& u.getArticleConcerne().getNoArticle().equals(idArticle)).collect(Collectors.toList()).get(0);
	}

}
