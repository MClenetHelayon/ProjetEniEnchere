package org.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eni.encheres.BusinessException;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.utilitaire.FicheMethodeBool;

public class UtilisateurDAOJdbcImpl implements DAOUser {

	private static final String SELECT_ALL = "select * from UTILISATEURS;";
	private static final String SELECT_BY_ID = "select * from UTILISATEURS WHERE no_utilisateur = ?;";
	private static final String SELECT_BY_PSEUDO_OR_EMAIL = "select * from UTILISATEURS WHERE pseudo like ? OR email like ?;";
	private final static String DELETE = "DELETE FROM UTILISATEURS WHERE no_utilisateur=?;";
	
	
	@Override
	public List<Utilisateur> selectAll() throws BusinessException {
		List<Utilisateur> vretour = new ArrayList<>();
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			
			Statement stmt = cnx.createStatement();
			
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			while(rs.next()) {
				Utilisateur unUser = new Utilisateur(	rs.getInt("no_utilisateur"),
														rs.getString("pseudo"),
														rs.getString("nom"),
														rs.getString("prenom"),
														rs.getString("email"),
														rs.getString("telephone"),
														rs.getString("rue"),
														rs.getString("code_postal"),
														rs.getString("ville"),
														rs.getString("mot_de_passe"),
														rs.getInt("credit"),
														FicheMethodeBool.bitToBool(rs.getByte("administrateur")));
				vretour.add(unUser);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vretour;
	}
	public Utilisateur selectById(int id) throws BusinessException{
		Utilisateur vretour = null;
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1,id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				vretour = new Utilisateur(	rs.getInt("no_utilisateur"),
											rs.getString("pseudo"),
											rs.getString("nom"),
											rs.getString("prenom"),
											rs.getString("email"),
											rs.getString("telephone"),
											rs.getString("rue"),
											rs.getString("code_postal"),
											rs.getString("ville"),
											rs.getString("mot_de_passe"),
											rs.getInt("credit"),
											FicheMethodeBool.bitToBool(rs.getByte("administrateur")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vretour;
	}
	public Utilisateur selectByNameOrEmail(String emailOrName) throws BusinessException {
		Utilisateur vretour = null;
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_PSEUDO_OR_EMAIL);
			pstmt.setString(1,emailOrName);
			pstmt.setString(2,emailOrName);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				vretour = new Utilisateur(	rs.getInt("no_utilisateur"),
											rs.getString("pseudo"),
											rs.getString("nom"),
											rs.getString("prenom"),
											rs.getString("email"),
											rs.getString("telephone"),
											rs.getString("rue"),
											rs.getString("code_postal"),
											rs.getString("ville"),
											rs.getString("mot_de_passe"),
											rs.getInt("credit"),
											FicheMethodeBool.bitToBool(rs.getByte("administrateur")));
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vretour;
	}
	@Override
	public void delete(int id) {
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(DELETE);
			pStmt.setInt(1, id);
			pStmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
