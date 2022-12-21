package org.eni.encheres.dal;

import org.eni.encheres.BusinessException;
import org.eni.encheres.bo.Utilisateur;

public interface DAOUser extends DAO<Utilisateur>{
	public Utilisateur connection(String emailOrName,String mdp) throws BusinessException;
	public Utilisateur mdpOublier(String email) throws BusinessException;
	public void bloquer(int idUser) throws BusinessException;
}
