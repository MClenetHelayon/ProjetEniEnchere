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
	
	public List<Utilisateur> selectAll() {
		List<Utilisateur> vretour = new ArrayList<Utilisateur>();
		try {
			vretour = DAOFactory.getUtilisateurDAO().selectAll();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return vretour;
	}
	public Utilisateur selectById(int id) {
		Utilisateur vretour = null;
		try {
			vretour = DAOFactory.getUtilisateurDAO().selectById(id);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return vretour;
	}
	public Utilisateur selectById(String emailOrName,String mdp) {
		Utilisateur vretour = null;
		try {
			vretour = (Utilisateur) DAOFactory.getUtilisateurDAO().selectByNameOrEmail(emailOrName,mdp);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return vretour;
	}
	public void delete(int id) {
		try {
			DAOFactory.getUtilisateurDAO().delete(id);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	public void insert(Utilisateur user) {
		try {
			DAOFactory.getUtilisateurDAO().insert(user);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	public void update(Utilisateur user) {
		try {
			DAOFactory.getUtilisateurDAO().update(user);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
}
