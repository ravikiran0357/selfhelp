package com.zyppysselfhelp.modal;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the sh_lookup_categories database table.
 * 
 */
@Entity
@Table(name = "sh_lookup_categories")
@NamedQuery(name = "ShLookupCategory.findAll", query = "SELECT s FROM ShLookupCategory s")
public class ShLookupCategory extends Audit {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sh_lookup_catg_id")
	private int shLookupCatgId;

	@Column(name = "catg_desc")
	private String catgDesc;

	@Column(name = "catg_name")
	private String catgName;

	// bi-directional many-to-one association to ShLookup
	@OneToMany(mappedBy = "shLookupCategory")
	private List<ShLookup> shLookups;

	private byte status;

	public ShLookupCategory() {
	}

	public int getShLookupCatgId() {
		return this.shLookupCatgId;
	}

	public void setShLookupCatgId(int shLookupCatgId) {
		this.shLookupCatgId = shLookupCatgId;
	}

	public String getCatgDesc() {
		return this.catgDesc;
	}

	public void setCatgDesc(String catgDesc) {
		this.catgDesc = catgDesc;
	}

	public String getCatgName() {
		return this.catgName;
	}

	public void setCatgName(String catgName) {
		this.catgName = catgName;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public List<ShLookup> getShLookups() {
		return this.shLookups;
	}

	public void setShLookups(List<ShLookup> shLookups) {
		this.shLookups = shLookups;
	}

	public ShLookup addShLookup(ShLookup shLookup) {
		getShLookups().add(shLookup);
		shLookup.setShLookupCategory(this);

		return shLookup;
	}

	public ShLookup removeShLookup(ShLookup shLookup) {
		getShLookups().remove(shLookup);
		shLookup.setShLookupCategory(null);

		return shLookup;
	}

}