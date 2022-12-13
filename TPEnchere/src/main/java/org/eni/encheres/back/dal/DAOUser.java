package org.eni.encheres.back.dal;

import org.eni.encheres.back.BusinessException;
import org.eni.encheres.back.bo.Utilisateur;

public interface DAOUser extends DAO<Utilisateur>{
	public Utilisateur selectByNameOrEmail(String emailOrName,String mdp) throws BusinessException;
}
