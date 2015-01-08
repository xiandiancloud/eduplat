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
import com.dhl.service.CaseService;

/**
 * 
 * @see
 * @since
 */
@Controller
public class CaseController{
	/**
	 * 自动注入
	 */
	@Autowired
	private CaseService caseService;
	
	/**
	 * 推荐课程
	 * @param request
	 * @param response
	 */
	@RequestMapping("/addcase")
	public void addcase(HttpServletRequest request,HttpServletResponse response,String name,String imgPath,String design,int caseCategory,int category,String url,String describe,int recommend){
		try {
			String str = caseService.save(name, imgPath, design, caseCategory, category, url, describe, recommend);
			PrintWriter out = response.getWriter();			
			out.write(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 更新课程
	 * @param request
	 * @param response
	 */
	@RequestMapping("/updatecase")
	public void updatecase(HttpServletRequest request,HttpServletResponse response,int id,String name,String imgPath,String design,int caseCategory,int category,String url,String describe,int recommend){
		try {
			
			String str = caseService.update(id,name, imgPath, design, caseCategory, category, url, describe, recommend);
			PrintWriter out = response.getWriter();			
			out.write(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除课程
	 * @param request
	 * @param response
	 */
	@RequestMapping("/delcase")
	public void delcase(HttpServletRequest request,HttpServletResponse response,int id){
		try {
			
			caseService.remove(id);
			
			PrintWriter out = response.getWriter();			
			out.write(CommonConstant.SUCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 推荐课程
	 * @param request
	 * @param response
	 */
	@RequestMapping("/recommend")
	public void recommend(HttpServletRequest request,HttpServletResponse response){
		try {
			List<Case> list = caseService.getRecommendList();
			PrintWriter out = response.getWriter();
			String str = javatojson(list);
			out.write(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 其他案例
	 * @param request
	 * @param response
	 */
	@RequestMapping("/other")
	public void other(HttpServletRequest request,HttpServletResponse response,int id,int pid){
		try {
			List<Case> list = caseService.getOtherList(id,pid);
			PrintWriter out = response.getWriter();
			String str = javatojson(list);
			out.write(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * id课程
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getcase")
	public void getcase(HttpServletRequest request,HttpServletResponse response,int id){
		try {
			Case c = caseService.get(id);
			PrintWriter out = response.getWriter();
			String str = "{'name':'"+c.getName()+"','content':'"+c.getDescribe()+"','url':'"+c.getUrl()+"','design':'"+c.getDesign()+"','cname':'"+c.getCategory().getName()+"','imgPath':'"+c.getImgPath()+"'}";
			str = str.replaceAll("null", "");
			out.write(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 所有课程
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getAllcase")
	public void getAllcase(HttpServletRequest request,HttpServletResponse response){
		try {
			List<Case> list = caseService.getAllList();
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
	private String javatojson(List<Case> list)
	{
		StringBuffer buffer = new StringBuffer();
		int count = list.size();
		buffer.append("[");
		for (int i=0;i<count;i++)
		{
			Case p = list.get(i);
			buffer.append("{");
			buffer.append("\"id\":");
			buffer.append("\""+p.getId()+"\"");
			buffer.append(",\"pid\":");
			buffer.append("\""+p.getCategory().getId()+"\"");
			buffer.append(",\"name\":");
			buffer.append("\""+p.getName()+"\"");
			buffer.append(",\"design\":");
			buffer.append("\""+p.getDesign()+"\"");
			buffer.append(",\"caseCategory\":");
			buffer.append("\""+p.getCaseCategory().getName()+"\"");
			buffer.append(",\"content\":");
			buffer.append("\""+p.getDescribe()+"\"");
			buffer.append(",\"imgPath\":");
			buffer.append("\""+p.getImgPath()+"\"");
			buffer.append(",\"url\":");
			buffer.append("\""+p.getUrl()+"\"");
			buffer.append(",\"casecount\":");
			buffer.append("\""+p.getCaseContent().size()+"\"");
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
