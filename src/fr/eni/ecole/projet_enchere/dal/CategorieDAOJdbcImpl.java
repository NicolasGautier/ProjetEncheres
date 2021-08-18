package fr.eni.ecole.projet_enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.projet_enchere.bo.Categorie;
import fr.eni.ecole.projet_enchere.dal.jdbc.JdbcTools;

public class CategorieDAOJdbcImpl implements CategorieDAO {
	private final String INSERT = "INSERT INTO categories(libelle) VALUES (?)";
	private final String UPDATE = "UPDATE categories SET libelle=? WHERE no_categorie = ?";
	private final String DELETE = "DELETE FROM categories WHERE no_categorie =?";
	private final String SELECT = "SELECT no_categorie, libelle FROM categories";
	private final String FROM = "SELECT no_categorie, libelle FROM categories WHERE no_categorie = ?";
	
	@Override
	public void insert(Categorie categorie) throws DALException {
		try (Connection con = JdbcTools.getConnection()) {//ConnectionProvider.getConnection()) { 
			PreparedStatement stmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, categorie.getLibelle());
			int nb = stmt.executeUpdate();
			if (nb > 0) {
				ResultSet rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					categorie.setNoCategorie(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Problème SQL");
		}
	}

	@Override
	public void update(Categorie categorie) throws DALException {
		try (Connection con = JdbcTools.getConnection()) {//ConnectionProvider.getConnection()) { 
			PreparedStatement stmt = con.prepareStatement(UPDATE);
			stmt.setString(1, categorie.getLibelle());
			stmt.setInt(2, categorie.getNoCategorie());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Problème SQL");
		}
	}

	@Override
	public void delete(Integer id) throws DALException {
		try (Connection con = JdbcTools.getConnection()) {//ConnectionProvider.getConnection()) { 
			PreparedStatement stmt = con.prepareStatement(DELETE);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Problème SQL");
		}
	}
	
	@Override
	public List<Categorie> selectAll() throws DALException {
		List<Categorie> result = new ArrayList<Categorie>();
		try (Connection con = JdbcTools.getConnection()) {//ConnectionProvider.getConnection()) { 
			PreparedStatement stmt = con.prepareStatement(SELECT);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Categorie categorie = new Categorie();
				categorie.setNoCategorie(rs.getInt("no_categorie"));
				categorie.setLibelle(rs.getString("libelle"));
				result.add(categorie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Problème SQL");
		}
		return result;
	}

	@Override
	public Categorie selectById(Integer id) throws DALException {
		Categorie categorie = new Categorie();
		try (Connection con = JdbcTools.getConnection()) {//ConnectionProvider.getConnection()) { 
			PreparedStatement stmt = con.prepareStatement(FROM);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				categorie.setNoCategorie(rs.getInt("no_categorie"));
				categorie.setLibelle(rs.getString("libelle"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Problème SQL");
		}
		return categorie;
	}

}
