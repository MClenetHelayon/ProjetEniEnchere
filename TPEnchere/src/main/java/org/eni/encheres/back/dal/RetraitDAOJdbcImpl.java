package org.eni.encheres.back.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eni.encheres.back.BusinessException;
import org.eni.encheres.back.bll.ArticleVenduManager;
import org.eni.encheres.back.bo.Retrait;

public class RetraitDAOJdbcImpl implements DAO<Retrait> {

	private final static String SELECT_ALL = "SELECT * FROM RETRAITS;";
	private static final String SELECT_BY_ID = "select * from RETRAITS WHERE no_article = ?;";
	private final static String DELETE = "DELETE FROM RETRAITS WHERE no_article=?;";
	private final static String INSERT = "INSERT INTO RETRAITS(no_article,rue,code_postal,ville) VALUES(?,?,?,?);";
	private static final String UPDATE = "UPDATE RETRAITS SET rue=?,code_postal=?,ville=? WHERE no_article=?;";
	
	
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

	@Override
	public void insert(Retrait lObjet) throws BusinessException {
		if(lObjet.getVille()==null||lObjet.getRue()==null||lObjet.getCodePostal()==null||lObjet.getArtVendu()==null)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(INSERT);
			pStmt.setInt(1,lObjet.getArtVendu().getNumArticle());
			pStmt.setString(2, lObjet.getRue());
			pStmt.setString(3, lObjet.getCodePostal());
			pStmt.setString(4, lObjet.getVille());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Retrait lObjet) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(UPDATE);
			pStmt.setString(1, lObjet.getRue());
			pStmt.setString(2, lObjet.getCodePostal());
			pStmt.setString(3, lObjet.getVille());
			pStmt.setInt(4,lObjet.getArtVendu().getNumArticle());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}




