package com.dhl.web;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dhl.cons.CommonConstant;
import com.dhl.domain.CaseContent;
import com.dhl.service.CaseContentService;

/**
 * 
 * @see
 * @since
 */
@Controller
public class CaseContentController{
	/**
	 * 自动注入
	 */
	@Autowired
	private CaseContentService caseContentService;
	
	/**
	 * 推荐课程
	 * @param request
	 * @param response
	 */
	@RequestMapping("/addcasecontent")
	public void addcasecontent(HttpServletRequest request,HttpServletResponse response,String name,String imgPath,int casetype,String url,String describe){
		try {
			String str = caseContentService.save(name, imgPath, casetype, url, describe);
			PrintWriter out = response.getWriter();			
			out.write(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/updatecasecontent")
	public void updatecasecontent(HttpServletRequest request,HttpServletResponse response,int id,String name,String imgPath,int casetype,String url,String describe){
		try {
			String str = caseContentService.update(id, name, imgPath, casetype, url, describe);
			PrintWriter out = response.getWriter();			
			out.write(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/delcasecontent")
	public void delcasecontent(HttpServletRequest request,HttpServletResponse response,int id){
		try {
			
			caseContentService.remove(id);
			
			PrintWriter out = response.getWriter();			
			out.write(CommonConstant.SUCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 课程列表
	 * @param request
	 * @param response
	 */
	@RequestMapping("/casecontent")
	public void casecontent(HttpServletRequest request,HttpServletResponse response,int cId){
		try {
			List<CaseContent> list = caseContentService.getCaseContentList(cId);
			PrintWriter out = response.getWriter();
			String str = javatojson(list);//UtilTools.listToJson(list);
			out.write(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * id课程列表
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getcasecontentbyid")
	public void getcasecontentbyid(HttpServletRequest request,HttpServletResponse response,int id){
		try {
			CaseContent c = caseContentService.getCaseContentById(id);
			PrintWriter out = response.getWriter();
			String str = "{'name':'"+c.getName()+"','content':'"+c.getDescribe()+"','url':'"+c.getUrl()+"','imgPath':'"+c.getImgPath()+"'}";
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
	private String javatojson(List<CaseContent> list)
	{
		StringBuffer buffer = new StringBuffer();
		int count = list.size();
		buffer.append("[");
		for (int i=0;i<count;i++)
		{
			CaseContent p = list.get(i);
			buffer.append("{");
			buffer.append("\"id\":");
			buffer.append("\""+p.getId()+"\"");
			buffer.append(",\"name\":");
			buffer.append("\""+p.getName()+"\"");	
			buffer.append(",\"content\":");
			buffer.append("\""+p.getDescribe()+"\"");
			buffer.append(",\"imgPath\":");
			buffer.append("\""+p.getImgPath()+"\"");
			buffer.append(",\"url\":");
			buffer.append("\""+p.getUrl()+"\"");
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
