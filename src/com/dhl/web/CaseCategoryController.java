package com.dhl.web;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dhl.domain.CaseCategory;
import com.dhl.service.CaseCategoryService;

/**
 * 
 * @see
 * @since
 */
@Controller
public class CaseCategoryController{
	/**
	 * 自动注入
	 */
	@Autowired
	private CaseCategoryService caseCategoryService;
	
	/**
	 * 所有实训类别列表
	 * @param request
	 * @param response
	 */
	@RequestMapping("/casecategory")
	public void casecategory(HttpServletRequest request,HttpServletResponse response){
		try {
			List<CaseCategory> list = caseCategoryService.getAllList();
			PrintWriter out = response.getWriter();
			String str = javatojson(list);
			out.write(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * list中有set，没有找到合适的转json方法，先拼接个用着
	 * @param list
	 * @return
	 */
	private String javatojson(List<CaseCategory> list)
	{
		StringBuffer buffer = new StringBuffer();
		int count = list.size();
		buffer.append("[");
		for (int i=0;i<count;i++)
		{
			CaseCategory p = list.get(i);
			buffer.append("{");
			buffer.append("\"id\":");
			buffer.append("\""+p.getId()+"\"");
			buffer.append(",\"name\":");
			buffer.append("\""+p.getName()+"\"");
			buffer.append("},");
		}
		if (count >  0)
		{
			String str = buffer.substring(0, buffer.length()-1)+"]";
			str = str.replaceAll("null", "");
			return str;
		}
		else
		{
			String str = buffer.toString()+"]";
			str = str.replaceAll("null", "");
			return str;
		}
	}
}
