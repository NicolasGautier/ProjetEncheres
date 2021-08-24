package fr.eni.ecole.projet_enchere.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.projet_enchere.bo.ArticleVendu;
import fr.eni.ecole.projet_enchere.bo.EtatsVente;
import fr.eni.ecole.projet_enchere.dal.ArticleVenduDAO;
import fr.eni.ecole.projet_enchere.dal.CategorieDAO;
import fr.eni.ecole.projet_enchere.dal.DALException;
import fr.eni.ecole.projet_enchere.dal.DalFactory;
import fr.eni.ecole.projet_enchere.dal.UtilisateurDAO;

public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {
	private final String INSERT = "INSERT INTO articles_vendus(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, etat_vente, no_utilisateurAchete, no_utilisateurVend, no_categorie) VALUES (?,?,?,?,?,?,?,?,?,?)";
	private final String UPDATE = "UPDATE articles_vendus SET nom_article=?, description=?, date_debut_encheres=?, date_fin_encheres=?, prix_initial=?, prix_vente=?, etat_vente=?, no_utilisateurAchete=?, no_utilisateurVend=?, no_categorie=? WHERE no_article = ?";
	private final String DELETE = "DELETE FROM articles_vendus WHERE no_article =?";
	private final String SELECT = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, etat_vente, no_utilisateurAchete, no_utilisateurVend, no_categorie FROM articles_vendus";
	private final String FROM = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, etat_vente, no_utilisateurAchete, no_utilisateurVend, no_categorie FROM articles_vendus WHERE no_article = ?";

	UtilisateurDAO utilisateurDao = DalFactory.getUtilisateurDAO();
	CategorieDAO categorieDao = DalFactory.getCategorieDAO();

	@Override
	public void insert(ArticleVendu articlevendu) throws DALException {
		try (Connection con = JdbcTools.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, articlevendu.getNomArticle());
			stmt.setString(2, articlevendu.getDescription());
			stmt.setTimestamp(3, Timestamp.valueOf(articlevendu.getDateDebutEncheres()));
			stmt.setTimestamp(4, Timestamp.valueOf(articlevendu.getDateFinEncheres()));
			stmt.setInt(5, articlevendu.getMiseAPrix());
			stmt.setInt(6, articlevendu.getPrixVente());
			stmt.setInt(7, articlevendu.getEtatVente().ordinal());
			stmt.setInt(8, articlevendu.getUtilisateurAchete().getNoUtilisateur());
			stmt.setInt(9, articlevendu.getUtilisateurVend().getNoUtilisateur());
			stmt.setInt(10, articlevendu.getCategorie().getNoCategorie());
			int nb = stmt.executeUpdate();
			if (nb > 0) {
				ResultSet rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					articlevendu.setNoArticle(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Problème SQL");
		}
	}

	@Override
	public void update(ArticleVendu articlevendu) throws DALException {
		try (Connection con = JdbcTools.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(UPDATE);
			stmt.setString(1, articlevendu.getNomArticle());
			stmt.setString(2, articlevendu.getDescription());
			stmt.setTimestamp(3, Timestamp.valueOf(articlevendu.getDateDebutEncheres()));
			stmt.setTimestamp(4, Timestamp.valueOf(articlevendu.getDateFinEncheres()));
			stmt.setInt(5, articlevendu.getMiseAPrix());
			stmt.setInt(6, articlevendu.getPrixVente());
			stmt.setInt(7, articlevendu.getEtatVente().ordinal());
			stmt.setInt(8, articlevendu.getUtilisateurAchete().getNoUtilisateur());
			stmt.setInt(9, articlevendu.getUtilisateurVend().getNoUtilisateur());
			stmt.setInt(10, articlevendu.getCategorie().getNoCategorie());
			stmt.setInt(11, articlevendu.getNoArticle());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Problème SQL");
		}
	}

	@Override
	public void delete(Integer id) throws DALException {
		try (Connection con = JdbcTools.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(DELETE);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Problème SQL");
		}
	}

	@Override
	public List<ArticleVendu> selectAll() throws DALException {
		List<ArticleVendu> result = new ArrayList<ArticleVendu>();
		try (Connection con = JdbcTools.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(SELECT);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArticleVendu articleVendu = new ArticleVendu();
				articleVendu.setNoArticle(rs.getInt("no_article"));
				articleVendu.setNomArticle(rs.getString("nom_article"));
				articleVendu.setDescription(rs.getString("description"));
				articleVendu.setDateDebutEncheres(rs.getTimestamp("date_debut_encheres").toLocalDateTime());
				articleVendu.setDateFinEncheres(rs.getTimestamp("date_fin_encheres").toLocalDateTime());
				articleVendu.setMiseAPrix(rs.getInt("prix_initial"));
				articleVendu.setPrixVente(rs.getInt("prix_vente"));
				articleVendu.setEtatVente(EtatsVente.values()[rs.getInt("etat_vente")]);
				articleVendu.setUtilisateurAchete(utilisateurDao.selectById(rs.getInt("no_utilisateurAchete")));
				articleVendu.setUtilisateurVend(utilisateurDao.selectById(rs.getInt("no_utilisateurVend")));
				articleVendu.setCategorie(categorieDao.selectById(rs.getInt("no_categorie")));
				result.add(articleVendu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Problème SQL");
		}
		return result;
	}

	@Override
	public ArticleVendu selectById(Integer id) throws DALException {
		ArticleVendu articleVendu = new ArticleVendu();
		try (Connection con = JdbcTools.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(FROM);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				articleVendu.setNoArticle(rs.getInt("no_article"));
				articleVendu.setNomArticle(rs.getString("nom_article"));
				articleVendu.setDescription(rs.getString("description"));
				articleVendu.setDateDebutEncheres(rs.getTimestamp("date_debut_encheres").toLocalDateTime());
				articleVendu.setDateFinEncheres(rs.getTimestamp("date_fin_encheres").toLocalDateTime());
				articleVendu.setMiseAPrix(rs.getInt("prix_initial"));
				articleVendu.setPrixVente(rs.getInt("prix_vente"));
				articleVendu.setEtatVente(EtatsVente.values()[rs.getInt("etat_vente")]);
				articleVendu.setUtilisateurAchete(utilisateurDao.selectById(rs.getInt("no_utilisateurAchete")));
				articleVendu.setUtilisateurVend(utilisateurDao.selectById(rs.getInt("no_utilisateurVend")));
				articleVendu.setCategorie(categorieDao.selectById(rs.getInt("no_categorie")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Problème SQL");
		}
		return articleVendu;
	}

}
