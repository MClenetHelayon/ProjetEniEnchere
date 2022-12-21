package org.eni.encheres.dal;

import org.eni.encheres.bo.Categorie;
import org.eni.encheres.bo.Enchere;
import org.eni.encheres.bo.Retrait;

public abstract class DAOFactory {

	public static DAOArticle getArticleVenduDAO() {
		return new ArticleVenduDAOJdbcImpl();
	}
	public static DAOUser getUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
	}
	public static DAO<Enchere> getEnchereDAO() {
		return new EnchereDAOJdbcImpl();
	}
	public static DAO<Categorie> getCategorieDAO() {
		return new CategorieDAOJdbcImpl();
	}
	public static DAO<Retrait> getRetraitDAO() {
		return new RetraitDAOJdbcImpl();
	}
}
