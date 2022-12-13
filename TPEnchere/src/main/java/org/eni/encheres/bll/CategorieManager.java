package org.eni.encheres.bll;

import java.util.ArrayList;
import java.util.List;

import org.eni.encheres.BusinessException;
import org.eni.encheres.bo.Categorie;
import org.eni.encheres.dal.DAOFactory;

public class CategorieManager {

	private static CategorieManager instance;
	
	public static CategorieManager getInstance() {
		if(instance == null) {
			instance = new CategorieManager();
		}
		return instance;
	}
	
	private CategorieManager() { }
	
	public List<Categorie> selectAll() {
		List<Categorie> vretour = new ArrayList<Categorie>();
		try {
			vretour = DAOFactory.getCategorieDAO().selectAll();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return vretour;
	}
	public Categorie selectById(int id) {
		Categorie vretour = null;
		try {
			vretour = DAOFactory.getCategorieDAO().selectById(id);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return vretour;
	}
	public void delete(int id) {
		try {
			DAOFactory.getCategorieDAO().delete(id);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
}




