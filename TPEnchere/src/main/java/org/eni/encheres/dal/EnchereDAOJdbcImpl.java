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
import org.eni.encheres.bll.UtilisateurManager;
import org.eni.encheres.bo.Enchere;
import org.eni.encheres.bo.Utilisateur;

public class EnchereDAOJdbcImpl implements DAOEnchere {
	private static final String SELECT_ALL = "select * from ENCHERES;";
	private static final String SELECT_BY_ID = "select * from ENCHERES WHERE no_enchere = ?;";
	private final static String DELETE = "DELETE FROM ENCHERES WHERE no_enchere=?;";
	
	@Override
	public List<Enchere> selectAll() throws BusinessException {
		List<Enchere> vretour = new ArrayList<>();

		//1. connexion
		try(Connection cnx = ConnectionProvider.getConnection()) {
			//2. requête
			Statement stmt = cnx.createStatement();
			//3. résultat
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			
			int idEncherePrecedent = 0,idUserPrecedent = 0; //permet de savoir s'il s'agit du même repas (dans la boucle)
			Enchere uneEnchere = null;
			Utilisateur unUser = null;
			while(rs.next()) { //4. parcours du résultat
				int idEnchere = rs.getInt("idRepas");
				
				if(idEnchere != idEncherePrecedent) {
					
					uneEnchere = new Enchere(idEnchere,
											rs.getTimestamp("date_enchere").toLocalDateTime(), 
											rs.getInt("montant"));
					vretour.add(uneEnchere);
					
					idEncherePrecedent = idEnchere;
				}
				int idUser = rs.getInt("no_utilisateur");
				if(idUser != idUserPrecedent) {
					
					unUser = UtilisateurManager.getInstance().selectById(idUser);
					uneEnchere.addLUser(unUser);
					
					idUserPrecedent = idUser;
				}
				uneEnchere.addLArtVendu(ArticleVenduManager.getInstance().selectById(rs.getInt("no_article")));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vretour;
	}

	@Override
	public List<Enchere> selectAllById(int id) throws BusinessException {
		List<Enchere> vretour = new ArrayList<>();
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1,id);
			ResultSet rs = pstmt.executeQuery();
			
			int idEncherePrecedent = 0,idUserPrecedent = 0; //permet de savoir s'il s'agit du même repas (dans la boucle)
			Enchere uneEnchere = null;
			Utilisateur unUser = null;
			while(rs.next()) { //4. parcours du résultat
				int idEnchere = rs.getInt("idRepas");
				
				if(idEnchere != idEncherePrecedent) {
					
					uneEnchere = new Enchere(idEnchere,
											rs.getTimestamp("date_enchere").toLocalDateTime(), 
											rs.getInt("montant"));
					vretour.add(uneEnchere);
					
					idEncherePrecedent = idEnchere;
				}
				int idUser = rs.getInt("no_utilisateur");
				if(idUser != idUserPrecedent) {
					
					unUser = UtilisateurManager.getInstance().selectById(idUser);
					uneEnchere.addLUser(unUser);
					
					idUserPrecedent = idUser;
				}
				uneEnchere.addLArtVendu(ArticleVenduManager.getInstance().selectById(rs.getInt("no_article")));
				
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
	public Enchere selectById(int id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
}
