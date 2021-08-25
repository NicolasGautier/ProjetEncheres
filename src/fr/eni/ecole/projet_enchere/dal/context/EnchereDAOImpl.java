package fr.eni.ecole.projet_enchere.dal.context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.projet_enchere.bo.Enchere;
import fr.eni.ecole.projet_enchere.dal.ArticleVenduDAO;
import fr.eni.ecole.projet_enchere.dal.DALException;
import fr.eni.ecole.projet_enchere.dal.DalFactory;
import fr.eni.ecole.projet_enchere.dal.EnchereDAO;
import fr.eni.ecole.projet_enchere.dal.UtilisateurDAO;

public class EnchereDAOImpl implements EnchereDAO {
	private final String INSERT = "INSERT INTO encheres(no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (?,?,?,?)";
	private final String UPDATE = "UPDATE encheres SET date_enchere=?, montant_enchere=? WHERE no_utilisateur=? AND no_article=?";
	private final String DELETE = "DELETE FROM encheres WHERE no_utilisateur=? AND no_article=?";
	private final String SELECT = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM encheres";
	private final String FROMART = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM encheres WHERE no_article=?";
	private final String FROM = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM encheres WHERE no_utilisateur=? AND no_article=?";
	
	private UtilisateurDAO utilDao = DalFactory.getUtilisateurDAO();
	private ArticleVenduDAO artDao = DalFactory.getArticleVenduDAO();
	
	@Override
	public void insert(Enchere enchere) throws DALException {
		try (Connection con = ConnectionProvider.getConnection()) { 
			PreparedStatement stmt = con.prepareStatement(INSERT);
			stmt.setInt(1, enchere.getUtilisateurEncherit().getNoUtilisateur());
			stmt.setInt(2, enchere.getArticleConcerne().getNoArticle());
			stmt.setTimestamp(3, Timestamp.valueOf(enchere.getDateEnchere()));
			stmt.setInt(4, enchere.getMontant_enchere());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Problème SQL");
		}
	}

	@Override
	public void update(Enchere enchere) throws DALException {
		try (Connection con = ConnectionProvider.getConnection()) { 
			PreparedStatement stmt = con.prepareStatement(UPDATE);
			stmt.setTimestamp(1, Timestamp.valueOf(enchere.getDateEnchere()));
			stmt.setInt(2, enchere.getMontant_enchere());
			stmt.setInt(3, enchere.getUtilisateurEncherit().getNoUtilisateur());
			stmt.setInt(4, enchere.getArticleConcerne().getNoArticle());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Problème SQL");
		}
	}

	@Override
	public void delete(Integer idUtilisateur, Integer idArticle) throws DALException {
		try (Connection con = ConnectionProvider.getConnection()) { 
			PreparedStatement stmt = con.prepareStatement(DELETE);
			stmt.setInt(1, idUtilisateur);
			stmt.setInt(2, idArticle);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Problème SQL");
		}
	}

	@Override
	public List<Enchere> selectAll() throws DALException {
		List<Enchere> result = new ArrayList<Enchere>();
		try (Connection con = ConnectionProvider.getConnection()) { 
			PreparedStatement stmt = con.prepareStatement(SELECT);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Enchere enchere = new Enchere();
				enchere.setUtilisateurEncherit(utilDao.selectById(rs.getInt("no_utilisateur")));
				enchere.setArticleConcerne(artDao.selectById(rs.getInt("no_article")));
				enchere.setDateEnchere(rs.getTimestamp("date_enchere").toLocalDateTime());
				enchere.setMontant_enchere(rs.getInt("montant_enchere"));
				result.add(enchere);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Problème SQL");
		}
		return result;
	}
	
	@Override
	public List<Enchere> selectById(Integer idArticle) throws DALException {
		List<Enchere> result = new ArrayList<Enchere>();
		try (Connection con = ConnectionProvider.getConnection()) { 
			PreparedStatement stmt = con.prepareStatement(FROMART);
			stmt.setInt(1, idArticle);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Enchere enchere = new Enchere();
				enchere.setUtilisateurEncherit(utilDao.selectById(rs.getInt("no_utilisateur")));
				enchere.setArticleConcerne(artDao.selectById(rs.getInt("no_article")));
				enchere.setDateEnchere(rs.getTimestamp("date_enchere").toLocalDateTime());
				enchere.setMontant_enchere(rs.getInt("montant_enchere"));
				result.add(enchere);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Problème SQL");
		}
		return result;
	}

	@Override
	public Enchere selectById(Integer idUtilisateur, Integer idArticle) throws DALException {
		Enchere enchere = new Enchere();
		try (Connection con = ConnectionProvider.getConnection()) { 
			PreparedStatement stmt = con.prepareStatement(FROM);
			stmt.setInt(1, idUtilisateur);
			stmt.setInt(2, idArticle);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				enchere.setUtilisateurEncherit(utilDao.selectById(rs.getInt("no_utilisateur")));
				enchere.setArticleConcerne(artDao.selectById(rs.getInt("no_article")));
				enchere.setDateEnchere(rs.getTimestamp("date_enchere").toLocalDateTime());
				enchere.setMontant_enchere(rs.getInt("montant_enchere"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Problème SQL");
		}
		return enchere;
	}

	

}
