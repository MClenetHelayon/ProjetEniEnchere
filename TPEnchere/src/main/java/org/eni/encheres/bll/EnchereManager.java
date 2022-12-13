package org.eni.encheres.bll;

import java.util.ArrayList;
import java.util.List;

import org.eni.encheres.BusinessException;
import org.eni.encheres.bo.Enchere;
import org.eni.encheres.dal.DAOFactory;

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
			vretour = (Enchere) DAOFactory.getEnchereDAO().selectById(id);
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
}
