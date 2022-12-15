package org.eni.encheres.back.bll;

import java.util.ArrayList;
import java.util.List;

import org.eni.encheres.back.BusinessException;
import org.eni.encheres.back.bo.Utilisateur;
import org.eni.encheres.back.dal.DAOFactory;



public class UtilisateurManager {
	private static UtilisateurManager instance;
	
	public static UtilisateurManager getInstance() {
		if(instance == null) {
			instance = new UtilisateurManager();
		}
		return instance;
	}
	
	private UtilisateurManager() { }
	
	public List<Utilisateur> selectAll() throws BusinessException {
		List<Utilisateur> vretour = new ArrayList<Utilisateur>();
		vretour = DAOFactory.getUtilisateurDAO().selectAll();
		return vretour;
	}
	public Utilisateur selectById(int id) throws BusinessException {
		Utilisateur vretour = null;
		vretour = DAOFactory.getUtilisateurDAO().selectById(id);
		return vretour;
	}
	public Utilisateur connection(String emailOrName,String mdp) throws BusinessException {
		Utilisateur vretour = null;
		vretour = DAOFactory.getUtilisateurDAO().connection(emailOrName,mdp);
		return vretour;
	}
	public Utilisateur mdpOublier(String email) throws BusinessException {
		Utilisateur vretour = null;
		vretour = DAOFactory.getUtilisateurDAO().mdpOublier(email);
		return vretour;
	}
	public void delete(int id) throws BusinessException {
		DAOFactory.getUtilisateurDAO().delete(id);
	}
	public void insert(Utilisateur user) throws BusinessException {
		DAOFactory.getUtilisateurDAO().insert(user);
	}
	public void update(Utilisateur user) throws BusinessException {
		DAOFactory.getUtilisateurDAO().update(user);
	}
}
