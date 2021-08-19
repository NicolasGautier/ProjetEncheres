package fr.eni.ecole.projet_enchere.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.projet_enchere.bo.Retrait;
import fr.eni.ecole.projet_enchere.dal.ArticleVenduDAO;
import fr.eni.ecole.projet_enchere.dal.DALException;
import fr.eni.ecole.projet_enchere.dal.DalFactory;
import fr.eni.ecole.projet_enchere.dal.RetraitDAO;

public class RetraitDAOJdbcImpl implements RetraitDAO {
	private final String INSERT = "INSERT INTO retraits(no_article, rue, code_postal, ville) VALUES (?,?,?,?)";
	private final String UPDATE = "UPDATE retraits SET rue=?, code_postal=?, ville=? WHERE no_article=?";
	private final String DELETE = "DELETE FROM retraits WHERE no_article=?";
	private final String SELECT = "SELECT no_article, rue, code_postal, ville FROM retraits";
	private final String FROM = "SELECT no_article, rue, code_postal, ville FROM retraits WHERE no_article=?";
	
	private ArticleVenduDAO artDao = DalFactory.getArticleVenduDAO();
	
	@Override
	public void insert(Retrait retrait) throws DALException {
		try (Connection con = JdbcTools.getConnection()) { 
			PreparedStatement stmt = con.prepareStatement(INSERT);
			stmt.setInt(1, retrait.getArticleVendu().getNoArticle());
			stmt.setString(2, retrait.getRue());
			stmt.setString(3, retrait.getCode_postal());
			stmt.setString(4, retrait.getVille());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Problème SQL");
		}
	}

	@Override
	public void update(Retrait retrait) throws DALException {
		try (Connection con = JdbcTools.getConnection()) { 
			PreparedStatement stmt = con.prepareStatement(UPDATE);
			stmt.setString(1, retrait.getRue());
			stmt.setString(2, retrait.getCode_postal());
			stmt.setString(3, retrait.getVille());
			stmt.setInt(4, retrait.getArticleVendu().getNoArticle());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Problème SQL");
		}
	}

	@Override
	public void delete(Integer idArticle) throws DALException {
		try (Connection con = JdbcTools.getConnection()) { 
			PreparedStatement stmt = con.prepareStatement(DELETE);
			stmt.setInt(1, idArticle);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Problème SQL");
		}
	}

	@Override
	public List<Retrait> selectAll() throws DALException {
		List<Retrait> result = new ArrayList<Retrait>();
		try (Connection con = JdbcTools.getConnection()) { 
			PreparedStatement stmt = con.prepareStatement(SELECT);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Retrait retrait = new Retrait();
				retrait.setArticleVendu(artDao.selectById(rs.getInt("no_article")));
				retrait.setRue(rs.getString("rue"));
				retrait.setCode_postal(rs.getString("code_postal"));
				retrait.setVille(rs.getString("ville"));
				result.add(retrait);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Problème SQL");
		}
		return result;
	}

	@Override
	public Retrait selectById(Integer idArticle) throws DALException {
		Retrait retrait = new Retrait();
		try (Connection con = JdbcTools.getConnection()) { 
			PreparedStatement stmt = con.prepareStatement(FROM);
			stmt.setInt(1, idArticle);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				retrait.setArticleVendu(artDao.selectById(rs.getInt("no_article")));
				retrait.setRue(rs.getString("rue"));
				retrait.setCode_postal(rs.getString("code_postal"));
				retrait.setVille(rs.getString("ville"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Problème SQL");
		}
		return retrait;
	}

}
