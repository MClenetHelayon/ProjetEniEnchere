package org.eni.encheres.back.dal;

import java.util.List;

import org.eni.encheres.back.BusinessException;


public interface DAO<T> {
	public List<T> selectAll() throws BusinessException;
	public T selectById(int id) throws BusinessException;
	public void delete(int id) throws BusinessException;
	public void insert(T lObjet) throws BusinessException;
	public void update(T lObjet) throws BusinessException;
}
