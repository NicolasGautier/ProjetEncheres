package fr.eni.ecole.projet_enchere.dal.context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.projet_enchere.bo.Utilisateur;
import fr.eni.ecole.projet_enchere.dal.DALException;
import fr.eni.ecole.projet_enchere.dal.UtilisateurDAO;

public class UtilisateurDAOImpl implements UtilisateurDAO {
	private final String INSERT = "INSERT INTO utilisateurs(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur, actif) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
	private final String UPDATE = "UPDATE utilisateurs SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=?, credit=?, administrateur=?, actif=? WHERE no_utilisateur = ?";
	private final String UPDATEPW = "UPDATE utilisateurs SET mot_de_passe=? WHERE email = ?";
	private final String DELETE = "DELETE FROM utilisateurs WHERE no_utilisateur =?";
	private final String SELECT = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur, actif FROM utilisateurs";
	private final String FROM = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur, actif FROM utilisateurs WHERE no_utilisateur = ?";
	
	@Override
	public void insert(Utilisateur utilisateur) throws DALException {
		try (Connection con = ConnectionProvider.getConnection()) { 
			PreparedStatement stmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, utilisateur.getPseudo());
			stmt.setString(2, utilisateur.getNom());
			stmt.setString(3, utilisateur.getPrenom());
			stmt.setString(4, utilisateur.getEmail());
			stmt.setString(5, utilisateur.getTelephone());
			stmt.setString(6, utilisateur.getRue());
			stmt.setString(7, utilisateur.getCodePostal());
			stmt.setString(8, utilisateur.getVille());
			stmt.setString(9, utilisateur.getMotDePasse());
			stmt.setInt(10, utilisateur.getCredit());
			stmt.setBoolean(11, utilisateur.getAdministrateur());
			stmt.setBoolean(12, utilisateur.getActif());
			int nb = stmt.executeUpdate();
			if (nb > 0) {
				ResultSet rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					utilisateur.setNoUtilisateur(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Problème SQL");
		}
	}

	@Override
	public void update(Utilisateur utilisateur) throws DALException {
		try (Connection con = ConnectionProvider.getConnection()) { 
			PreparedStatement stmt = con.prepareStatement(UPDATE);
			stmt.setString(1, utilisateur.getPseudo());
			stmt.setString(2, utilisateur.getNom());
			stmt.setString(3, utilisateur.getPrenom());
			stmt.setString(4, utilisateur.getEmail());
			stmt.setString(5, utilisateur.getTelephone());
			stmt.setString(6, utilisateur.getRue());
			stmt.setString(7, utilisateur.getCodePostal());
			stmt.setString(8, utilisateur.getVille());
			stmt.setString(9, utilisateur.getMotDePasse());
			stmt.setInt(10, utilisateur.getCredit());
			stmt.setBoolean(11, utilisateur.getAdministrateur());
			stmt.setBoolean(12, utilisateur.getActif());
			stmt.setInt(13, utilisateur.getNoUtilisateur());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Problème SQL");
		}
	}
	//"UPDATE utilisateurs SET mot_de_passe=? WHERE email = ?";
	@Override
	public void updatePW(Utilisateur utilisateur) throws DALException {
		try (Connection con = ConnectionProvider.getConnection()) { 
			PreparedStatement stmt = con.prepareStatement(UPDATEPW);
			stmt.setString(1, utilisateur.getMotDePasse());
			stmt.setString(2, utilisateur.getEmail());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Problème SQL");
		}
	}

	@Override
	public void delete(Integer id) throws DALException {
		try (Connection con = ConnectionProvider.getConnection()) { 
			PreparedStatement stmt = con.prepareStatement(DELETE);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Problème SQL");
		}
	}
	
	@Override
	public List<Utilisateur> selectAll() throws DALException {
		List<Utilisateur> result = new ArrayList<Utilisateur>();
		try (Connection con = ConnectionProvider.getConnection()) { 
			PreparedStatement stmt = con.prepareStatement(SELECT);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCodePostal(rs.getString("code_postal"));
				utilisateur.setVille(rs.getString("ville"));
				utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
				utilisateur.setCredit(rs.getInt("credit"));
				utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
				utilisateur.setActif(rs.getBoolean("actif"));
				result.add(utilisateur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Problème SQL");
		}
		return result;
	}

	@Override
	public Utilisateur selectById(Integer id) throws DALException {
		Utilisateur utilisateur = new Utilisateur();
		try (Connection con = ConnectionProvider.getConnection()) { 
			PreparedStatement stmt = con.prepareStatement(FROM);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCodePostal(rs.getString("code_postal"));
				utilisateur.setVille(rs.getString("ville"));
				utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
				utilisateur.setCredit(rs.getInt("credit"));
				utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
				utilisateur.setActif(rs.getBoolean("actif"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Problème SQL");
		}
		return utilisateur;
	}

}
