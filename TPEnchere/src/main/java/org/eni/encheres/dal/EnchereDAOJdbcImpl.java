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
import org.eni.encheres.utilitaire.FicheMethodeTemps;


public class EnchereDAOJdbcImpl implements DAO<Enchere> {
	private static final String SELECT_ALL = "SELECT * FROM ENCHERES;";
	//private static final String SELECT_BY_ID = "SELECT * FROM ENCHERES WHERE no_enchere = ?;";
	private static final String SELECT_BY_ID_ARTICLE = "SELECT * FROM ENCHERES WHERE no_article = ? AND  montant_enchere = (SELECT MAX(montant_enchere) FROM ENCHERES);";
	private final static String DELETE = "DELETE FROM ENCHERES WHERE no_enchere=?;";
	private final static String INSERT = "INSERT INTO ENCHERES(date_enchere,montant_enchere,no_utilisateur,no_article) VALUES(?,?,?,?);";
	private static final String UPDATE = "UPDATE ENCHERES SET date_enchere=?,montant_enchere=?,no_utilisateur=?,no_article=? WHERE no_article=? AND no_utilisateur= ?;";
	
	@Override
	public List<Enchere> selectAll() throws BusinessException {
		List<Enchere> vretour = new ArrayList<>();

		//1. connexion
		try(Connection cnx = ConnectionProvider.getConnection()) {
			//2. requête
			Statement stmt = cnx.createStatement();
			//3. résultat
			ResultSet rs = stmt.executeQuery(SELECT_ALL);

			while(rs.next()) { //4. parcours du résultat
				vretour.add(simplyCreator(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vretour;
	}

	@Override
	public Enchere selectById(int id) throws BusinessException {
		// Custom
		
		Enchere vretour = null;

		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_ID_ARTICLE);
			pStmt.setInt(1, id);
			ResultSet rs = pStmt.executeQuery();
			
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
	public void insert(Enchere lObjet) throws BusinessException {
		if(lObjet.getUser()==null||lObjet.getArtVendu()==null||lObjet.getDate()==null||String.valueOf(lObjet.getMontant())==null||lObjet.getMontant()==0)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(INSERT);
			pStmt.setDate(1, FicheMethodeTemps.localDateTimeToDate(lObjet.getDate()));
			pStmt.setInt(2, lObjet.getMontant());
			pStmt.setInt(3, lObjet.getUser().getIdUser());
			pStmt.setInt(4, lObjet.getArtVendu().getNumArticle());
			pStmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void update(Enchere lObjet) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(UPDATE);
			pStmt.setDate(1, FicheMethodeTemps.localDateTimeToDate(lObjet.getDate()));
			pStmt.setInt(2, lObjet.getMontant());
			pStmt.setInt(3, lObjet.getUser().getIdUser());
			pStmt.setInt(4, lObjet.getArtVendu().getNumArticle());
			pStmt.setInt(5, lObjet.getArtVendu().getNumArticle());
			pStmt.setInt(6, lObjet.getUser().getIdUser());
			int rs = pStmt.executeUpdate();

			if(rs == 0) {
				insert(lObjet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	private Enchere simplyCreator(ResultSet rs) {
		Enchere vretour = null;
		try {
			vretour = new Enchere(	rs.getInt("no_enchere"),
									FicheMethodeTemps.dateToLocalDateTimeWithResultSet(rs,"date_enchere"), 
									rs.getInt("montant_enchere"),
									UtilisateurManager.getInstance().selectById(rs.getInt("no_utilisateur")),
									ArticleVenduManager.getInstance().selectById(rs.getInt("no_article")));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return vretour;
	}

}
