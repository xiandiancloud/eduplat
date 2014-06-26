package com.dhl.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dhl.domain.Category;

@Repository
public class CategoryDao extends BaseDao<Category> {
	
	public List<Category> getAllList()
	{
		String hql = "from Category";
    	return find(hql);
	}
	
	public List<Category> getListById(int id)
	{
		String hql = "from Category where id = "+id;
    	return find(hql);
	}
	
	public List<Category> getListByName(String name)
	{
		String hql = "from Category where name = '"+name+"'";
    	return find(hql);
	}
}
