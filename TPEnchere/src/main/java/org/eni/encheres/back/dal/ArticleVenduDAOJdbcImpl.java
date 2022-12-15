package org.eni.encheres.back.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eni.encheres.back.BusinessException;
import org.eni.encheres.back.bll.CategorieManager;
import org.eni.encheres.back.bll.UtilisateurManager;
import org.eni.encheres.back.bo.ArticleVendu;
import org.eni.encheres.back.utilitaire.FicheMethodeTemps;

public class ArticleVenduDAOJdbcImpl implements DAO<ArticleVendu> {
	private static final String SELECT_ALL = "select * from ARTICLES_VENDUS av INNER JOIN CATEGORIES c ON c.no_categorie = av.no_categorie;";
	private static final String SELECT_BY_ID = "select * from ARTICLES_VENDUS WHERE no_article = ?;";
	private final static String DELETE = "DELETE FROM ARTICLES_VENDUS WHERE no_article=?;";
	private final static String INSERT = "INSERT INTO ARTICLES_VENDUS(nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie) VALUES(?,?,?,?,?,?,?,?);";
	private static final String UPDATE = "UPDATE ARTICLES_VENDUS SET nom_article=?,description=?,date_debut_encheres=?,date_fin_encheres=?,prix_initial=?,no_utilisateur=?,no_categorie=? WHERE no_article=?;";
	
	@Override
	public List<ArticleVendu> selectAll() throws BusinessException {
		List<ArticleVendu> vretour = new ArrayList<>();
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			
			Statement stmt = cnx.createStatement();
			
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			while(rs.next()) {
				ArticleVendu unArticle = simplyCreator(rs);
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
					vretour = simplyCreator(rs);
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
	public void insert(ArticleVendu lObjet) throws BusinessException {
		if(lObjet.getNom()==null||lObjet.getDescription()==null||lObjet.getDateDeb()==null||lObjet.getDateFin()==null||lObjet.getUser()==null||lObjet.getCateg()==null)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(INSERT);
			pStmt.setString(2, lObjet.getNom());
			pStmt.setString(3, lObjet.getDescription());
			pStmt.setDate(4,FicheMethodeTemps.dateToLocalDate(lObjet.getDateDeb()));
			pStmt.setDate(5,FicheMethodeTemps.dateToLocalDate(lObjet.getDateFin()));
			pStmt.setInt(6, lObjet.getPrixInit());
			pStmt.setInt(7, lObjet.getPrixVente());
			pStmt.setInt(8, lObjet.getUser().getIdUser());
			pStmt.setInt(8, lObjet.getCateg().getNumCat());
			pStmt.executeUpdate();
			ResultSet rs = pStmt.getGeneratedKeys();
			if(rs.next())
			{
				lObjet.setNumArticle(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void update(ArticleVendu lObjet) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(UPDATE);
			pStmt.setString(1, lObjet.getNom());
			pStmt.setString(2, lObjet.getDescription());
			pStmt.setDate(3,FicheMethodeTemps.dateToLocalDate(lObjet.getDateDeb()));
			pStmt.setDate(4,FicheMethodeTemps.dateToLocalDate(lObjet.getDateFin()));
			pStmt.setInt(5, lObjet.getPrixInit());
			pStmt.setInt(6, lObjet.getPrixVente());
			pStmt.setInt(7, lObjet.getUser().getIdUser());
			pStmt.setInt(8, lObjet.getCateg().getNumCat());
			pStmt.setInt(9, lObjet.getNumArticle());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	private ArticleVendu simplyCreator(ResultSet rs) {
		ArticleVendu vretour = null;
		try {
			boolean bool = false;
			if(String.valueOf(rs.getInt("prix_vente"))!=null) {
				bool = true;
			}
			vretour = new ArticleVendu(	rs.getInt("no_article"),
										rs.getString("nom_article"),
										rs.getString("description"),
										FicheMethodeTemps.LocalDateToDate(rs.getDate("date_debut_encheres")),
										FicheMethodeTemps.LocalDateToDate(rs.getDate("date_fin_encheres")),
										rs.getInt("prix_initial"),
										rs.getInt("prix_vente"),
										UtilisateurManager.getInstance().selectById(rs.getInt("no_utilisateur")),
										CategorieManager.getInstance().selectById(rs.getInt("no_categorie")),
										bool);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (BusinessException e) {
			e.printStackTrace();
		};
		
		return vretour;
	}

}
