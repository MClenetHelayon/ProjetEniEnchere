package org.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.eni.encheres.BusinessException;
import org.eni.encheres.bll.CategorieManager;
import org.eni.encheres.bll.EnchereManager;
import org.eni.encheres.bll.RetraitManager;
import org.eni.encheres.bll.UtilisateurManager;
import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Enchere;
import org.eni.encheres.bo.Retrait;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.utilitaire.FicheMethodeTemps;

public class ArticleVenduDAOJdbcImpl implements DAOArticle {
	private static final String SELECT_ALL = "select * from ARTICLES_VENDUS av INNER JOIN CATEGORIES c ON c.no_categorie = av.no_categorie;";
	private static final String SELECT_ALL_BY_NAME = "select * from ARTICLES_VENDUS WHERE nom_article like ?;";
	private static final String SELECT_BY_ID = "select * from ARTICLES_VENDUS WHERE no_article = ?;";
	private static final String SELECT_BY_IDCATEG = "select * from ARTICLES_VENDUS av INNER JOIN CATEGORIES c ON c.no_categorie = av.no_categorie WHERE av.no_categorie = ?;";
	private final static String DELETE = "DELETE FROM ARTICLES_VENDUS WHERE no_article=?;";
	private final static String INSERT = "INSERT INTO ARTICLES_VENDUS(nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie, etat,img_data) VALUES(?,?,?,?,?,?,?,?,?);";
	private static final String UPDATE = "UPDATE ARTICLES_VENDUS SET nom_article=?,description=?,date_debut_encheres=?,date_fin_encheres=?,prix_initial=?,prix_vente=?,no_utilisateur=?,no_categorie=?, etat=?,img_data=? WHERE no_article=?;";
	
	@Override
	public List<ArticleVendu> selectAll() throws BusinessException {
		List<ArticleVendu> vretour = new ArrayList<>();
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			
			Statement stmt = cnx.createStatement();
			
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			while(rs.next()) {
				vretour.add(simplyCreator(rs));
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
	public List<ArticleVendu> selectAllByText(String nom) throws BusinessException {
			List<ArticleVendu> vretour = new ArrayList<ArticleVendu>();
			try(Connection cnx = ConnectionProvider.getConnection()){
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_BY_NAME);
				pstmt.setString(1,"%"+nom+"%");
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					vretour.add(simplyCreator(rs));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return vretour;
	}
	@Override
	public List<ArticleVendu> selectAllByCateg(int idCat) throws BusinessException {
		List<ArticleVendu> vretour = new ArrayList<>();
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_IDCATEG);
			pstmt.setInt(1,idCat);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				vretour.add(simplyCreator(rs));
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
			PreparedStatement pStmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pStmt.setString(1, lObjet.getNom());
			pStmt.setString(2, lObjet.getDescription());
			pStmt.setDate(3,FicheMethodeTemps.dateToLocalDate(lObjet.getDateDeb()));
			pStmt.setDate(4,FicheMethodeTemps.dateToLocalDate(lObjet.getDateFin()));
			pStmt.setInt(5, lObjet.getPrixInit());
			pStmt.setInt(6, lObjet.getPrixVente());
			pStmt.setInt(7, lObjet.getUser().getIdUser());
			pStmt.setInt(8, lObjet.getCateg().getNumCat());
			pStmt.setInt(9, lObjet.getEtatVente());
			pStmt.setString(10,lObjet.getImgData());
			int rt = pStmt.executeUpdate();
			ResultSet rs = pStmt.getGeneratedKeys();
			
			if(rt == 1) {
				if(rs.next()) {
					ArticleVendu article = selectById(rs.getInt(1));
					
					if((!lObjet.getUser().getRue().isEmpty() || !lObjet.getUser().getRue().isBlank()) && (!lObjet.getUser().getCodePostal().isEmpty() || !lObjet.getUser().getCodePostal().isBlank()) && (!lObjet.getUser().getVille().isEmpty() || !lObjet.getUser().getVille().isBlank())) {
						Retrait addRetrait = new Retrait(article, lObjet.getUser().getRue(), lObjet.getUser().getCodePostal(), lObjet.getUser().getVille());
						RetraitManager.getInstance().update(addRetrait);
					}
				}
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
			pStmt.setInt(9, lObjet.getEtatVente());
			pStmt.setString(10,lObjet.getImgData());
			pStmt.setInt(11, lObjet.getNumArticle());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	private ArticleVendu simplyCreator(ResultSet rs) {
		ArticleVendu vretour = null;
		try {
			Utilisateur user = UtilisateurManager.getInstance().selectById(rs.getInt("no_utilisateur"));

				vretour = new ArticleVendu(	rs.getInt("no_article"),
						rs.getString("nom_article"),
						rs.getString("description"),
						FicheMethodeTemps.LocalDateToDate(rs.getDate("date_debut_encheres")),
						FicheMethodeTemps.LocalDateToDate(rs.getDate("date_fin_encheres")),
						rs.getInt("prix_initial"),
						rs.getInt("prix_vente"),
						user,
						CategorieManager.getInstance().selectById(rs.getInt("no_categorie")),
						rs.getInt("etat"),
						rs.getString("img_data"));

			
			//verification des etat de vente
			LocalDate today = LocalDate.now();
			if((today.isAfter(FicheMethodeTemps.LocalDateToDate(rs.getDate("date_debut_encheres"))) || today.isEqual(FicheMethodeTemps.LocalDateToDate(rs.getDate("date_debut_encheres")))) && rs.getInt("etat") == 0) {
				vretour.setEtatVente(1);
				update(vretour);
			} else if((today.isAfter(FicheMethodeTemps.LocalDateToDate(rs.getDate("date_fin_encheres"))) || today.isEqual(FicheMethodeTemps.LocalDateToDate(rs.getDate("date_fin_encheres")))) && rs.getInt("etat") == 1) {
				vretour.setEtatVente(2);
				
				Enchere miseMax = EnchereManager.getInstance().selectMiseMax(vretour.getNumArticle());
				
				vretour.getUser().setCredit(vretour.getUser().getCredit() - miseMax.getMontant());
				
				update(vretour);
				
			}
		} catch (SQLException | BusinessException e) {
			e.printStackTrace();
		}

		
		return vretour;
	}

}
