package com.dhl.service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhl.cons.CommonConstant;
import com.dhl.dao.CaseContentDao;
import com.dhl.dao.CaseDao;
import com.dhl.dao.CategoryDao;
import com.dhl.domain.Case;
import com.dhl.domain.CaseContent;
import com.dhl.domain.Category;

/**
 * 
 *
 */
@Service
public class CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private CaseDao caseDao;
	@Autowired
	private CaseContentDao caseContentDao;
	/**
	 * 保存分类
	 * @return
	 */
	public String save(Category entity)
	{
		String name = entity.getName();
		List<Category> list = categoryDao.getListByName(name);
		if (list != null && list.size() > 0)
		{
			return CommonConstant.ERROR_1;
		}
		categoryDao.save(entity);
		return CommonConstant.SUCESS;
	}
	
	public String update(Category entity)
	{
		String name = entity.getName();
		List<Category> list = categoryDao.getListByName(name);
		if (list != null && list.size() > 0)
		{
			return CommonConstant.ERROR_1;
		}
		categoryDao.update(entity);
		return CommonConstant.SUCESS;
	}
	
	public void remove(int id)
	{		
		Category c = categoryDao.get(id);
		List<Case> cases = c.getCases();
		if (cases != null)
		{
			int len = cases.size();
			for (int i=0;i<len;i++)
			{
				Case entity = cases.get(i);
				if (entity != null)
				{
					Set<CaseContent> set = entity.getCaseContent();
					Iterator<CaseContent> it = set.iterator();
					while(it.hasNext())
					{
						CaseContent obj = it.next();
						if (obj != null)
						{
							caseContentDao.remove(obj);
						}
					}
					caseDao.remove(entity);
				}
			}
		}
		categoryDao.remove(c);
	}
	
	/**
	 * 取得所有分类列表
	 * @return
	 */
	public List<Category> getAllList()
	{
		return categoryDao.getAllList();
	}
	
	/**
	 * 根据分类，取得下面的列表
	 * @param id
	 * @return
	 */
	public List<Category> getListById(int id)
	{
    	return categoryDao.getListById(id);
	}
	
	public Category get(int id)
	{
    	return categoryDao.get(id);
	}
	
	public List<Category> getListByName(String name)
	{
    	return categoryDao.getListByName(name);
	}
}
