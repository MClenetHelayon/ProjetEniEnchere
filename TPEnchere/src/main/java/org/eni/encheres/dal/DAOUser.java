package org.eni.encheres.dal;

import org.eni.encheres.BusinessException;
import org.eni.encheres.bo.Utilisateur;

public interface DAOUser extends DAO<Utilisateur>{
	public Utilisateur selectByNameOrEmail(String emailOrName) throws BusinessException;
}
