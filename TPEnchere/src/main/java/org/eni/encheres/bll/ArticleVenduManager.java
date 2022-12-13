package org.eni.encheres.bll;

import java.util.ArrayList;
import java.util.List;

import org.eni.encheres.BusinessException;
import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.dal.DAOFactory;

public class ArticleVenduManager {

	private static ArticleVenduManager instance;
	
	public static ArticleVenduManager getInstance() {
		if(instance == null) {
			instance = new ArticleVenduManager();
		}
		return instance;
	}
	
	private ArticleVenduManager() { }
	
	public List<ArticleVendu> selectAll() {
		List<ArticleVendu> vretour = new ArrayList<ArticleVendu>();
		try {
			vretour = DAOFactory.getArticleVenduDAO().selectAll();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return vretour;
	}
	public ArticleVendu selectById(int id) {
		ArticleVendu vretour = null;
		try {
			vretour = DAOFactory.getArticleVenduDAO().selectById(id);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return vretour;
	}
	public void delete(int id) {
		try {
			DAOFactory.getArticleVenduDAO().delete(id);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	public void insert(ArticleVendu artVen) {
		try {
			DAOFactory.getArticleVenduDAO().insert(artVen);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
}




