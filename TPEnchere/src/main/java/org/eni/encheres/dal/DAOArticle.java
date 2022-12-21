package org.eni.encheres.dal;

import java.util.List;

import org.eni.encheres.BusinessException;
import org.eni.encheres.bo.ArticleVendu;

public interface DAOArticle extends DAO<ArticleVendu>{
	public List<ArticleVendu> selectAllByCateg(int idCat) throws BusinessException;
	public List<ArticleVendu> selectAllByText(String nom) throws BusinessException;
}
