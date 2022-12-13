package org.eni.encheres.back.bll;

import java.util.ArrayList;
import java.util.List;

import org.eni.encheres.back.BusinessException;
import org.eni.encheres.back.bo.Categorie;
import org.eni.encheres.back.bo.Utilisateur;
import org.eni.encheres.back.dal.DAOFactory;

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




