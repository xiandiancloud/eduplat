package com.dhl.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 对应类别表
 * 
 * @author dong
 * 
 */
@Entity
@Table(name = "t_categroy")
public class Category extends BaseDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	// 分类名称
	private String name;
	// 分类描述
	private String describe;

	//每个类别下的案例
	@OneToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER,mappedBy = "category")
	private List<Case> cases;

//	public Set<Case> getCases() {
//		return cases;
//	}
//
//	public void setCases(Set<Case> cases) {
//		this.cases = cases;
//	}

	public List<Case> getCases() {
		return cases;
	}

	public void setCases(List<Case> cases) {
		this.cases = cases;
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
	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
}
