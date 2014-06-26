package com.dhl.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dhl.domain.Case;
import com.dhl.domain.CaseContent;

@Repository
public class CaseContentDao extends BaseDao<CaseContent> {
	
	public List<CaseContent> getCaseContentList(int cId)
	{
		String hql = "from CaseContent where cId = "+cId;
    	return find(hql);
	}
	
	public List<CaseContent> getListByName(String name,int cId)
	{
		String hql = "from CaseContent where name = '"+name+"' and cId = "+cId;
    	return find(hql);
	}
}
