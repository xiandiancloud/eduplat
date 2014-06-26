package com.dhl.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 对应具体课程表
 * 
 * @author dong
 * 
 */
@Entity
@Table(name = "t_casecontent")
public class CaseContent extends BaseDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	// case id
//	private int cId;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "cId")
	private Case cases;	
	public Case getCases() {
		return cases;
	}

	public void setCases(Case cases) {
		this.cases = cases;
	}

	// 具体课程名称
	private String name;
	// 内容
	private String describe;

	// url
	private String url;
	// 图片路径
	private String imgPath;
	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

//	public int getcId() {
//		return cId;
//	}
//
//	public void setcId(int cId) {
//		this.cId = cId;
//	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
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
}
