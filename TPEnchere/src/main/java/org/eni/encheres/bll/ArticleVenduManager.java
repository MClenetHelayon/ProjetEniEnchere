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
	
	public List<ArticleVendu> selectAll() throws BusinessException {
		List<ArticleVendu> vretour = new ArrayList<ArticleVendu>();
		vretour = DAOFactory.getArticleVenduDAO().selectAll();
		return vretour;
	}
	public ArticleVendu selectById(int id) throws BusinessException {
		ArticleVendu vretour = null;
		
		vretour = DAOFactory.getArticleVenduDAO().selectById(id);

		return vretour;
	}
	public List<ArticleVendu> selectAllByCateg(int idCateg) throws BusinessException{
		List<ArticleVendu> vretour = new ArrayList<ArticleVendu>();
		vretour = DAOFactory.getArticleVenduDAO().selectAllByCateg(idCateg);
		
		return vretour;
	}
	public List<ArticleVendu> selectAllByNom(String nom) throws BusinessException{
		List<ArticleVendu> vretour = new ArrayList<ArticleVendu>();
		vretour = DAOFactory.getArticleVenduDAO().selectAllByText(nom);
		return vretour;
	}
	public void delete(int id) throws BusinessException {
		DAOFactory.getArticleVenduDAO().delete(id);
	}
	public void insert(ArticleVendu artVen) throws BusinessException {
		DAOFactory.getArticleVenduDAO().insert(artVen);
	}
	public void update(ArticleVendu artVen) throws BusinessException {
		DAOFactory.getArticleVenduDAO().update(artVen);
	}
}




