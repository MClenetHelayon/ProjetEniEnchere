package org.eni.encheres.dal;

import java.util.List;

import org.eni.encheres.BusinessException;
import org.eni.encheres.bo.Enchere;

public interface DAOEnchere extends DAO<Enchere> {
	public List<Enchere> selectListById(int idArticle) throws BusinessException;
	public Enchere selectMiseMax(int idArticle) throws BusinessException;
}
