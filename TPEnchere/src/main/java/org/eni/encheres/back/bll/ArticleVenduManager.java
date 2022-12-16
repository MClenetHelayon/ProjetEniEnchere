package org.eni.encheres.back.bll;

import java.util.ArrayList;
import java.util.List;

import org.eni.encheres.back.BusinessException;
import org.eni.encheres.back.bo.ArticleVendu;
import org.eni.encheres.back.dal.DAOFactory;

public class ArticleVenduManager {

	private static ArticleVenduManager instance;
	
	public static ArticleVenduManager getInstance() {
		if(instance == null) {
			instance = new ArticleVenduManager();
		}
		return instance;
	}
	
	private ArticleVenduManager() { }
	
	public List<ArticleVendu> selectAll() throws BusinessException {
		List<ArticleVendu> vretour = new ArrayList<ArticleVendu>();
		
		vretour = DAOFactory.getArticleVenduDAO().selectAll();
		
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
	public void insert(ArticleVendu artVen) throws BusinessException {
		DAOFactory.getArticleVenduDAO().insert(artVen);
	}
	public void update(ArticleVendu artVen) {
		try {
			DAOFactory.getArticleVenduDAO().update(artVen);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
}




