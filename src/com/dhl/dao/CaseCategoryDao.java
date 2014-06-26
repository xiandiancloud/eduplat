package com.dhl.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dhl.domain.CaseCategory;

@Repository
public class CaseCategoryDao extends BaseDao<CaseCategory> {
	
	public List<CaseCategory> getAllList()
	{
		String hql = "from CaseCategory";
    	return find(hql);
	}
}
