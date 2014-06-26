package com.dhl.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 对应具体案例表
 * 
 * @author dong
 * 
 */
@Entity
@Table(name = "t_case")
public class Case extends BaseDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	// 分类id
	// private int cId;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "cId")
	private Category category;
	// 具体案例名称
	private String name;
	// 设计
	private String design;
	// 案例类别
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoryId")
	private CaseCategory caseCategory;
	// 是否推荐
	private int recommend = 0;
	// 图片路径
	private String imgPath;
	// url
	private String url;
	// 描述
	private String describe;
	
	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	@OneToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER,mappedBy = "cases")
	private Set<CaseContent> caseContent;

	public Set<CaseContent> getCaseContent() {
		return caseContent;
	}

	public void setCaseContent(Set<CaseContent> caseContent) {
		this.caseContent = caseContent;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getDesign() {
		return design;
	}

	public void setDesign(String design) {
		this.design = design;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CaseCategory getCaseCategory() {
		return caseCategory;
	}

	public void setCaseCategory(CaseCategory caseCategory) {
		this.caseCategory = caseCategory;
	}

}
