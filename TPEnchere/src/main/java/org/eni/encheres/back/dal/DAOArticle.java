package org.eni.encheres.back.dal;

import java.util.List;

import org.eni.encheres.back.BusinessException;
import org.eni.encheres.back.bo.ArticleVendu;

public interface DAOArticle extends DAO<ArticleVendu>{
	public List<ArticleVendu> selectAllByCateg(int idCat) throws BusinessException;
}
