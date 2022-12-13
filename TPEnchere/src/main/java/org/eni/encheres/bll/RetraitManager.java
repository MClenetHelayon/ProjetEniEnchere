package org.eni.encheres.bll;

import java.util.ArrayList;
import java.util.List;

import org.eni.encheres.BusinessException;
import org.eni.encheres.bo.Retrait;
import org.eni.encheres.dal.DAOFactory;

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
	public Retrait selectById(int id) {
		Retrait vretour = null;
		try {
			vretour = DAOFactory.getRetraitDAO().selectById(id);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return vretour;
	}
	public void delete(int id) {
		try {
			DAOFactory.getRetraitDAO().delete(id);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
}




