package com.dhl.web;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dhl.cons.CommonConstant;
import com.dhl.domain.Case;
import com.dhl.domain.Category;
import com.dhl.service.CategoryService;

/**
 * 
 * @see
 * @since
 */
@Controller
public class CategoryController{
	/**
	 * 自动注入
	 */
	@Autowired
	private CategoryService categoryService;
	
	/**
	 * 增加分类
	 * @param request
	 * @param response
	 */
	@RequestMapping("/addcategory")
	public void addcategory(HttpServletRequest request,HttpServletResponse response,Category entity){
		try {
			String str = categoryService.save(entity);
			PrintWriter out = response.getWriter();			
			out.write(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 更新分类
	 * @param request
	 * @param response
	 */
	@RequestMapping("/updatecategory")
	public void updatecategory(HttpServletRequest request,HttpServletResponse response,Category entity){
		try {
			
			String str = categoryService.update(entity);
			
			PrintWriter out = response.getWriter();			
			out.write(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除分类
	 * @param request
	 * @param response
	 */
	@RequestMapping("/delcategory")
	public void delcategory(HttpServletRequest request,HttpServletResponse response,int id){
		try {
			
			categoryService.remove(id);
			
			PrintWriter out = response.getWriter();			
			out.write(CommonConstant.SUCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 分类课程
	 * @param request
	 * @param response
	 */
	@RequestMapping("/category")
	public void category(HttpServletRequest request,HttpServletResponse response){
		try {
			List<Category> list = categoryService.getAllList();
//			JsonConfig config = new JsonConfig();  
//			config.setJsonPropertyFilter(new PropertyFilter(){  
//			    public boolean apply(Object source, String name, Object value) {  
//			        if(name.equals("cases")) {
//			            return true;  
//			        } else {  
//			            return false;  
//			        }  
//			    }  
//			});        
			PrintWriter out = response.getWriter();
			String str = javatojson(list,0);//UtilTools.listToJson(list,config);
			out.write(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 相同id下的分类课程
	 * @param request
	 * @param response
	 */
	@RequestMapping("/categorybyid")
	public void categorybyid(HttpServletRequest request,HttpServletResponse response,int id,int ccid){
		try {
			List<Category> list;
			if (id < 1)
			{
				list = categoryService.getAllList();
			}
			else
			{
				list = categoryService.getListById(id);
			}
			PrintWriter out = response.getWriter();
			String str = javatojson(list,ccid);
			out.write(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * id下的分类
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getcategorybyid")
	public void getcategorybyid(HttpServletRequest request,HttpServletResponse response,int id){
		try {
			Category c = categoryService.get(id);
			PrintWriter out = response.getWriter();
			String str = "{'name':'"+c.getName()+"','content':'"+c.getDescribe()+"'}";
			str = str.replaceAll("null", "");
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
	private String javatojson(List<Category> list,int ccid)
	{
		StringBuffer buffer = new StringBuffer();
		int count = list.size();
		buffer.append("[");
		for (int i=0;i<count;i++)
		{
			Category p = list.get(i);
			buffer.append("{");
			buffer.append("\"id\":");
			buffer.append("\""+p.getId()+"\"");
			buffer.append(",\"name\":");
			buffer.append("\""+p.getName()+"\"");
			
			List<Case> cases = p.getCases();
			buffer.append(",\"cases\":[");
			if (cases != null)
			{
				String tmp = getCases(cases, ccid);
				buffer.append(tmp);
			}
			buffer.append("]");
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
	
	private String getCases(List<Case> cases,int ccid)
	{
		StringBuffer buffer = new StringBuffer();
		int size = cases.size();
        for (int j=0;j<size;j++){
        	Case c = cases.get(j); 
        	if (ccid >0 && ccid != c.getCaseCategory().getId())
        	{
        		continue;
        	}
        	buffer.append("{");
			buffer.append("\"id\":");
			buffer.append("\""+c.getId()+"\"");
			buffer.append(",\"name\":");
			buffer.append("\""+c.getName()+"\"");
			buffer.append(",\"design\":");
			buffer.append("\""+c.getDesign()+"\"");
			buffer.append(",\"caseCategory\":");
			buffer.append("\""+c.getCaseCategory().getName()+"\"");
			buffer.append(",\"imgPath\":");
			buffer.append("\""+c.getImgPath()+"\"");
			buffer.append(",\"url\":");
			buffer.append("\""+c.getUrl()+"\"");
			buffer.append("},");
        }
        if (buffer.length() >  1)
		{
			String str = buffer.substring(0, buffer.length()-1);
			str = str.replaceAll("null", "");
			return str;
		}
		else
		{
			String str = buffer.toString();
			str = str.replaceAll("null", "");
			return str;
		}
	}
}
