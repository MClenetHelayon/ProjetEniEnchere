package org.eni.encheres.back.bll;

import java.util.ArrayList;
import java.util.List;

import org.eni.encheres.back.BusinessException;
import org.eni.encheres.back.bo.Enchere;
import org.eni.encheres.back.bo.Utilisateur;
import org.eni.encheres.back.dal.DAOFactory;

public class EnchereManager {
	private static EnchereManager instance;
	
	public static EnchereManager getInstance() {
		if(instance == null) {
			instance = new EnchereManager();
		}
		return instance;
	}
	
	private EnchereManager() { }
	
	public List<Enchere> selectAll() {
		List<Enchere> vretour = new ArrayList<Enchere>();
		try {
			vretour = DAOFactory.getEnchereDAO().selectAll();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return vretour;
	}
	public Enchere selectById(int id) {
		Enchere vretour = null;
		try {
			vretour = DAOFactory.getEnchereDAO().selectById(id);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return vretour;
	}
	public void delete(int id) {
		try {
			DAOFactory.getEnchereDAO().delete(id);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	public void insert(Enchere ench) {
		try {
			DAOFactory.getEnchereDAO().insert(ench);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	public void update(Enchere ench) {
		try {
			DAOFactory.getEnchereDAO().update(ench);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
}
