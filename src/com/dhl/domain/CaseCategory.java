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
 * 对应具体案例属于哪个类别表    
 * 
 * @author dong
 * 
 */
@Entity
@Table(name = "t_catecategory")
public class CaseCategory extends BaseDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	// 对应具体案例分类名称
	private String name;

	//每个类别下的案例
	@OneToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER,mappedBy = "caseCategory")
	private List<Case> cases;
	
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
}
