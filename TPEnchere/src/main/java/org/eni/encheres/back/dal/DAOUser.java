package org.eni.encheres.back.dal;

import org.eni.encheres.back.BusinessException;
import org.eni.encheres.back.bo.Utilisateur;

public interface DAOUser extends DAO<Utilisateur>{
	public Utilisateur connection(String emailOrName,String mdp) throws BusinessException;
	public Utilisateur mdpOublier(String email) throws BusinessException;
	public void bloquer(int idUser) throws BusinessException;
}
