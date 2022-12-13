package org.eni.encheres.back.dal;

import org.eni.encheres.back.bo.ArticleVendu;
import org.eni.encheres.back.bo.Categorie;
import org.eni.encheres.back.bo.Enchere;
import org.eni.encheres.back.bo.Retrait;

public abstract class DAOFactory {

	public static DAO<ArticleVendu> getArticleVenduDAO() {
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
