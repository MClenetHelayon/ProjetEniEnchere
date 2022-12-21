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
	
	public List<Categorie> selectAll() throws BusinessException {
		List<Categorie> vretour = new ArrayList<Categorie>();
		vretour = DAOFactory.getCategorieDAO().selectAll();
		return vretour;
	}
	public Categorie selectById(int id) throws BusinessException {
		Categorie vretour = null;
		vretour = DAOFactory.getCategorieDAO().selectById(id);
		return vretour;
	}
	public void delete(int id) {
		try {
			DAOFactory.getCategorieDAO().delete(id);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	public void insert(Categorie categ) {
		try {
			DAOFactory.getCategorieDAO().insert(categ);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	public void update(Categorie categ) {
		try {
			DAOFactory.getCategorieDAO().update(categ);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
}




