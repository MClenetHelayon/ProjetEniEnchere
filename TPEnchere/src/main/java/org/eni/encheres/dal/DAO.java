package org.eni.encheres.dal;

import java.util.List;

import org.eni.encheres.BusinessException;

public interface DAO<T> {
	public List<T> selectAll() throws BusinessException;
	public T selectById(int id) throws BusinessException;
	public void delete(int id) throws BusinessException;
}
