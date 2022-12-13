package org.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eni.encheres.BusinessException;
import org.eni.encheres.bll.UtilisateurManager;
import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Categorie;
import org.eni.encheres.utilitaire.FicheMethodeTemps;

public class ArticleVenduDAOJdbcImpl implements DAO<ArticleVendu> {
	private static final String SELECT_ALL = "select * from ARTICLES_VENDUS av INNER JOIN CATEGORIES c ON c.no_categorie = av.no_categorie;";
	private static final String SELECT_BY_ID = "select * from ARTICLES_VENDUS av INNER JOIN CATEGORIES c ON c.no_categorie = av.no_categorie WHERE av.no_article = ?;";
	private final static String DELETE = "DELETE FROM ARTICLES_VENDUS WHERE no_article=?;";
	@Override
	public List<ArticleVendu> selectAll() throws BusinessException {
		List<ArticleVendu> vretour = new ArrayList<>();
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			
			Statement stmt = cnx.createStatement();
			
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			while(rs.next()) {
				ArticleVendu unArticle = new ArticleVendu(	rs.getInt("no_article"),
															rs.getString("nom_article"),
															rs.getString("description"),
															FicheMethodeTemps.asLocalDate(rs.getDate("date_debut_encheres")),
															FicheMethodeTemps.asLocalDate(rs.getDate("date_fin_encheres")),
															rs.getInt("prix_initial"),
															rs.getInt("prix_vente"),
															UtilisateurManager.getInstance().selectById(rs.getInt("no_utilisateur")),
															new Categorie(	rs.getInt("c.no_categorie"),
																			rs.getString("libelle")));
				vretour.add(unArticle);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vretour;
	}
	@Override
	public ArticleVendu selectById(int id) throws BusinessException {
			ArticleVendu vretour = null;
			try(Connection cnx = ConnectionProvider.getConnection()){
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
				pstmt.setInt(1,id);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					vretour = new ArticleVendu(	rs.getInt("no_article"),
							rs.getString("nom_article"),
							rs.getString("description"),
							FicheMethodeTemps.asLocalDate(rs.getDate("date_debut_encheres")),
							FicheMethodeTemps.asLocalDate(rs.getDate("date_fin_encheres")),
							rs.getInt("prix_initial"),
							rs.getInt("prix_vente"),
							UtilisateurManager.getInstance().selectById(rs.getInt("no_utilisateur")),
							new Categorie(	rs.getInt("c.no_categorie"),
											rs.getString("libelle")));
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
