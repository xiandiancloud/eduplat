package com.dhl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhl.dao.CaseCategoryDao;
import com.dhl.domain.CaseCategory;

/**
 * 
 *
 */
@Service
public class CaseCategoryService {
	
	@Autowired
	private CaseCategoryDao caseCategoryDao;
	
	/**
	 * 取得所有实训类别列表
	 * @return
	 */
	public List<CaseCategory> getAllList()
	{
		return caseCategoryDao.getAllList();
	}
}
