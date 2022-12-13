package org.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eni.encheres.BusinessException;
import org.eni.encheres.bo.Categorie;

public class CategorieDAOJdbcImpl implements DAO<Categorie> {

	private final static String SELECT_ALL = "SELECT * FROM CATEGORIES;";
	private static final String SELECT_BY_ID = "SELECT * from CATEGORIES WHERE no_categorie = ?;";
	private final static String DELETE = "DELETE FROM CATEGORIES WHERE no_categorie=?;";
	
	public List<Categorie> selectAll() {
		List<Categorie> vretour = new ArrayList<>();
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			
			Statement stmt = cnx.createStatement();
			
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			while(rs.next()) {
				Categorie uneCateg = new Categorie(rs.getInt("no_categorie"),rs.getString("libelle"));
				vretour.add(uneCateg);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vretour;
	}

	@Override
	public Categorie selectById(int id) throws BusinessException {
		Categorie vretour = null;
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1,id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				vretour = new Categorie(rs.getInt("no_categorie"),rs.getString("libelle"));
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




