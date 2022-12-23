package org.eni.encheres.bll;

import java.util.ArrayList;
import java.util.List;

import org.eni.encheres.BusinessException;
import org.eni.encheres.bo.Enchere;
import org.eni.encheres.dal.DAOFactory;

public class EnchereManager {
	private static EnchereManager instance;
	
	public static EnchereManager getInstance() {
		if(instance == null) {
			instance = new EnchereManager();
		}
		return instance;
	}
	
	private EnchereManager() { }
	
	public List<Enchere> selectAll() throws BusinessException {
		List<Enchere> vretour = new ArrayList<Enchere>();
		
		vretour = DAOFactory.getEnchereDAO().selectAll();
		
		return vretour;
	}
	
	public Enchere selectById(int id) {
		Enchere vretour = null;
		try {
			vretour = DAOFactory.getEnchereDAO().selectById(id);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return vretour;
	}
	
	public List<Enchere> selectListByIdArticle(int idArticle) throws BusinessException {
		List<Enchere> listEnchere = new ArrayList<>();
		
		listEnchere = DAOFactory.getEnchereDAO().selectListById(idArticle);
		
		return listEnchere;
	}
	
	public void delete(int id) {
		try {
			DAOFactory.getEnchereDAO().delete(id);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	public void insert(Enchere ench) throws BusinessException {
		//control if not exist
		Enchere controlDoublon = selectMiseMax(ench.getArtVendu().getNumArticle());
		
		if(controlDoublon != null) {
			if(ench.getDate() != controlDoublon.getDate() && ench.getMontant() != controlDoublon.getMontant()) {
				DAOFactory.getEnchereDAO().insert(ench);
			}
		} else {
			DAOFactory.getEnchereDAO().insert(ench);
		}
		
		
	}
	public void update(Enchere ench) throws BusinessException {
		DAOFactory.getEnchereDAO().update(ench);
	}
	
	public Enchere selectMiseMax(int idArticle) throws BusinessException {
		Enchere vretour = null;
		
		vretour = DAOFactory.getEnchereDAO().selectMiseMax(idArticle);
		
		return vretour;
	}
	
	
	
	
	
	
	/*public void selectReturnCredit(int idArticle) throws BusinessException {
		DAOFactory.getEnchereDAO().selectReturnCredit(idArticle);
	}*/
	
	/*public void selectAllByIdArticle(int idArticle) throws BusinessException {
		Enchere ancientMax = DAOFactory.getEnchereDAO().selectById(idArticle);
		
		int countIdArticle = DAOFactory.getEnchereDAO().selectAllByIdArticle(idArticle);
		System.out.println("passe ici Manager " + countIdArticle);
		if(countIdArticle != 10_000) {
			DAOFactory.getEnchereDAO().selectReturnCredit(countIdArticle);
		} else {
			System.out.println("Erreur BLL ENCHERE manager");
		}
	}*/
}
