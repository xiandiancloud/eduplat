package com.dhl.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dhl.domain.Case;

@Repository
public class CaseDao extends BaseDao<Case> {
	
	public List<Case> getRecommendList()
	{
		String hql = "from Case where recommend = 1";
    	return find(hql);
	}
	
	public List<Case> getOtherList(int id,int pid)
	{
		String hql = "from Case where id != "+id +" and cId = "+pid;
    	return find(hql);
	}
	
	public List<Case> getListByName(String name,int cId)
	{
		String hql = "from Case where name = '"+name+"' and cId = "+cId;
    	return find(hql);
	}
	
	public List<Case> getAllList()
	{
		String hql = "from Case";
    	return find(hql);
	}
}
