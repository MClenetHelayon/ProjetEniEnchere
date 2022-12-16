package org.eni.encheres.back.bll;

import java.util.ArrayList;
import java.util.List;

import org.eni.encheres.back.BusinessException;
import org.eni.encheres.back.bo.Retrait;
import org.eni.encheres.back.dal.DAOFactory;


public class RetraitManager {

	private static RetraitManager instance;
	
	public static RetraitManager getInstance() {
		if(instance == null) {
			instance = new RetraitManager();
		}
		return instance;
	}
	
	private RetraitManager() { }
	
	public List<Retrait> selectAll() {
		List<Retrait> vretour = new ArrayList<Retrait>();
		try {
			vretour = DAOFactory.getRetraitDAO().selectAll();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return vretour;
	}
	public Retrait selectById(int id) throws BusinessException {
		Retrait vretour = null;

		vretour = DAOFactory.getRetraitDAO().selectById(id);
		
		return vretour;
	}
	public void delete(int id) {
		try {
			DAOFactory.getRetraitDAO().delete(id);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	public void insert(Retrait retrait) {
		try {
			DAOFactory.getRetraitDAO().insert(retrait);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	public void update(Retrait retrait) {
		try {
			DAOFactory.getRetraitDAO().update(retrait);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
}




