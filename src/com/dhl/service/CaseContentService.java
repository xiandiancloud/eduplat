package com.dhl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhl.cons.CommonConstant;
import com.dhl.dao.CaseContentDao;
import com.dhl.dao.CaseDao;
import com.dhl.domain.Case;
import com.dhl.domain.CaseContent;

/**
 * 
 *
 */
@Service
public class CaseContentService {
	
	@Autowired
	private CaseContentDao caseContentDao;
	@Autowired
	private CaseDao caseDao;
	
	/**
	 * 保存分类
	 * @return
	 */
	public String save(String name,String imgPath,int casetype,String url,String describe)
	{
		List<CaseContent> list = caseContentDao.getListByName(name,casetype);
		if (list != null && list.size() > 0)
		{
			return CommonConstant.ERROR_3;
		}
		CaseContent entity = new CaseContent();
		entity.setName(name);
		entity.setImgPath(imgPath);
		entity.setCases(caseDao.get(casetype));
		entity.setUrl(url);
		entity.setDescribe(describe);
		caseContentDao.save(entity);
		return CommonConstant.SUCESS;
	}
	
	public String update(int id,String name,String imgPath,int casetype,String url,String describe)
	{
		List<CaseContent> list = caseContentDao.getListByName(name,casetype);
		if (list != null)
		{
			int len = list.size();
			for (int i=0;i<len;i++)
			{
				CaseContent cc = list.get(i);
				if (cc != null && cc.getId() != id)
				{
					return CommonConstant.ERROR_3;		
				}
			}
		}
		CaseContent entity = caseContentDao.get(id);
		if (entity != null)
		{
			entity.setName(name);
			entity.setImgPath(imgPath);
			entity.setCases(caseDao.get(casetype));
			entity.setUrl(url);
			entity.setDescribe(describe);
			caseContentDao.update(entity);
		}
		return CommonConstant.SUCESS;
	}
	
	public void remove(int id)
	{		
		CaseContent entity = caseContentDao.get(id);
		caseContentDao.remove(entity);
	}
	
	/**
	 * 取得课程列表
	 * @return
	 */
	public List<CaseContent> getCaseContentList(int cId)
	{
		return caseContentDao.getCaseContentList(cId);
	}
	
	public CaseContent getCaseContentById(int id)
	{
		return caseContentDao.get(id);
	}
}
