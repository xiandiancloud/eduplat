package com.dhl.service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhl.cons.CommonConstant;
import com.dhl.dao.CaseCategoryDao;
import com.dhl.dao.CaseContentDao;
import com.dhl.dao.CaseDao;
import com.dhl.dao.CategoryDao;
import com.dhl.domain.Case;
import com.dhl.domain.CaseContent;

/**
 * 
 *
 */
@Service
public class CaseService {
	
	@Autowired
	private CaseDao caseDao;
	@Autowired
	private CaseCategoryDao caseCategoryDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private CaseContentDao caseContentDao;
	/**
	 * 保存分类
	 * @return
	 */
	public String save(String name,String imgPath,String design,int caseCategory,int category,String url,String describe,int recommend)
	{
		List<Case> list = caseDao.getListByName(name,category);
		if (list != null && list.size() > 0)
		{
			return CommonConstant.ERROR_2;
		}
		Case entity = new Case();
		entity.setName(name);
		entity.setImgPath(imgPath);
		entity.setDesign(design);
		entity.setCaseCategory(caseCategoryDao.get(caseCategory));
		entity.setCategory(categoryDao.get(category));
		entity.setUrl(url);
		entity.setDescribe(describe);
		entity.setRecommend(recommend);
		caseDao.save(entity);
		return CommonConstant.SUCESS;
	}
	
	/**
	 * 保存分类
	 * @return
	 */
//	public String save(Case entity)
//	{
//		String name = entity.getName();
//		List<Case> list = caseDao.getListByName(name);
//		if (list != null && list.size() > 0)
//		{
//			return CommonConstant.ERROR_2;
//		}
//		caseDao.save(entity);
//		return CommonConstant.SUCESS;
//	}
	
//	public void update(Case entity)
//	{
//		caseDao.update(entity);
//	}
	
	public String update(int id,String name,String imgPath,String design,int caseCategory,int category,String url,String describe,int recommend)
	{
		List<Case> list = caseDao.getListByName(name,category);
		if (list != null)
		{
			int len = list.size();
			for (int i=0;i<len;i++)
			{
				Case cc = list.get(i);
				if (cc != null && cc.getId() != id)
				{
					return CommonConstant.ERROR_2;		
				}
			}
		}
		Case entity = caseDao.get(id);
		if (entity != null)
		{
			entity.setName(name);
			entity.setImgPath(imgPath);
			entity.setDesign(design);
			entity.setCaseCategory(caseCategoryDao.get(caseCategory));
			entity.setCategory(categoryDao.get(category));
			entity.setUrl(url);
			entity.setDescribe(describe);
			entity.setRecommend(recommend);
			caseDao.update(entity);
		}
		return CommonConstant.SUCESS;
		
	}
	
	public void remove(int id)
	{		
		Case entity = caseDao.get(id);
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
	
	/**
	 * 取得推荐列表
	 * @return
	 */
	public List<Case> getRecommendList()
	{
		return caseDao.getRecommendList();
	}
	
	/**
	 * 取得其他案例列表
	 * @return
	 */
	public List<Case> getOtherList(int id,int pid)
	{
		return caseDao.getOtherList(id,pid);
	}
	
	/**
	 * 取得所有案例列表
	 * @return
	 */
	public List<Case> getAllList()
	{
		return caseDao.getAllList();
	}
	
	/**
	 * 跟据id取得案例 
	 * @return
	 */
	public Case get(int id)
	{
		return caseDao.get(id);
	}
}
