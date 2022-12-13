package org.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eni.encheres.BusinessException;
import org.eni.encheres.bll.ArticleVenduManager;
import org.eni.encheres.bo.Retrait;

public class RetraitDAOJdbcImpl implements DAO<Retrait> {

	private final static String SELECT_ALL = "SELECT * FROM RETRAITS;";
	private static final String SELECT_BY_ID = "select * from RETRAITS WHERE no_article = ?;";
	private final static String DELETE = "DELETE FROM RETRAITS WHERE no_article=?;";
	
	@Override
	public List<Retrait> selectAll() {
		List<Retrait> vretour = new ArrayList<>();
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			
			Statement stmt = cnx.createStatement();
			
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			while(rs.next()) {
				Retrait unRetrait = new Retrait(ArticleVenduManager.getInstance().selectById(rs.getInt("no_article")),
												rs.getString("rue"),
												rs.getString("code_postal"),
												rs.getString("ville"));
				vretour.add(unRetrait);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vretour;
	}

	@Override
	public Retrait selectById(int id) throws BusinessException {
		Retrait vretour = null;
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1,id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				vretour = new Retrait(ArticleVenduManager.getInstance().selectById(rs.getInt("no_article")),
						rs.getString("rue"),
						rs.getString("code_postal"),
						rs.getString("ville"));
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




