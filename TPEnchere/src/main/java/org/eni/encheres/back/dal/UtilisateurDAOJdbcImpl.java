package org.eni.encheres.back.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eni.encheres.back.BusinessException;
import org.eni.encheres.back.bo.Utilisateur;
import org.eni.encheres.back.utilitaire.FicheMethodeBool;



public class UtilisateurDAOJdbcImpl implements DAOUser {

	private static final String SELECT_ALL = "select * from UTILISATEURS;";
	private static final String SELECT_BY_ID = "select * from UTILISATEURS WHERE no_utilisateur = ?;";
	private static final String SELECT_BY_EMAIL = "select * from UTILISATEURS WHERE email = ?;";
	private static final String SELECT_BY_PSEUDO_OR_EMAIL_AND_MDP = "select * from UTILISATEURS WHERE (pseudo like ? OR email like ?) AND mot_de_passe like ?;";
	private final static String DELETE = "DELETE FROM UTILISATEURS WHERE no_utilisateur=?;";
	private final static String INSERT = "INSERT INTO UTILISATEURS(pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur,bloque) VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
	private final static String UPDATE = "UPDATE UTILISATEURS SET pseudo=?,nom=?,prenom=?,email=?,telephone=?,rue=?,code_postal=?,ville=?,mot_de_passe=?,bloque=? WHERE no_utilisateur=?;";
	
	
	@Override
	public List<Utilisateur> selectAll() throws BusinessException {
		List<Utilisateur> vretour = new ArrayList<>();
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			
			Statement stmt = cnx.createStatement();
			
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			while(rs.next()) {
				Utilisateur unUser = simplyCreator(rs);
				vretour.add(unUser);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vretour;
	}
	@Override
	public Utilisateur selectById(int id) throws BusinessException{
		Utilisateur vretour = null;
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
	public Utilisateur connection(String emailOrName,String mdp) throws BusinessException {
		Utilisateur vretour = null;
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_PSEUDO_OR_EMAIL_AND_MDP);
			pstmt.setString(1,emailOrName);
			pstmt.setString(2,emailOrName);
			pstmt.setString(3,mdp);
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
	public Utilisateur mdpOublier(String email) throws BusinessException {
		Utilisateur vretour = null;
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_EMAIL);
			pstmt.setString(1,email);
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
	public void insert(Utilisateur lObjet) throws BusinessException {
		if(lObjet.getPseudo()==null||lObjet.getNom()==null||lObjet.getPrenom()==null||lObjet.getEmail()==null||lObjet.getRue()==null||lObjet.getVille()==null||lObjet.getCodePostal()==null||lObjet.getMdp()==null)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			pStmt.setString(1, lObjet.getPseudo());
			pStmt.setString(2, lObjet.getNom());
			pStmt.setString(3, lObjet.getPrenom());
			pStmt.setString(4, lObjet.getEmail());
			pStmt.setString(5, lObjet.getTelephone());
			pStmt.setString(6, lObjet.getRue());
			pStmt.setString(7, lObjet.getCodePostal());
			pStmt.setString(8, lObjet.getVille());
			pStmt.setString(9, lObjet.getMdp());
			pStmt.setInt(10, lObjet.getCredit());
			pStmt.setByte(11,FicheMethodeBool.boolToBit(lObjet.isAdmin()));
			pStmt.setByte(12,FicheMethodeBool.boolToBit(lObjet.isBloque()));
			pStmt.executeUpdate();
			ResultSet rs = pStmt.getGeneratedKeys();
			if(rs.next())
			{
				lObjet.setIdUser(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	@Override
	public void update(Utilisateur lObjet) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(UPDATE);
			pStmt.setString(1, lObjet.getPseudo());
			pStmt.setString(2, lObjet.getNom());
			pStmt.setString(3, lObjet.getPrenom());
			pStmt.setString(4, lObjet.getEmail());
			pStmt.setString(5, lObjet.getTelephone());
			pStmt.setString(6, lObjet.getRue());
			pStmt.setString(7, lObjet.getCodePostal());
			pStmt.setString(8, lObjet.getVille());
			pStmt.setString(9, lObjet.getMdp());
			pStmt.setByte(10,FicheMethodeBool.boolToBit(lObjet.isBloque()));
			pStmt.setInt(11, lObjet.getIdUser());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	private Utilisateur simplyCreator(ResultSet rs) throws BusinessException {
		Utilisateur vretour = null;
		try {
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
										FicheMethodeBool.bitToBool(rs.getByte("administrateur")),
										false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vretour;
	}
}
