package com.zyppysselfhelp.modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the sh_lookups database table.
 * 
 */
@Entity
@Table(name = "sh_lookups")
@NamedQuery(name = "ShLookup.findAll", query = "SELECT s FROM ShLookup s")
public class ShLookup extends Audit {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sh_lookup_id")
	private int shLookupId;

	@Column(name = "language_id")
	private int languageId;

	@Column(name = "lookup_description")
	private String lookupDescription;

	@Column(name = "lookup_notes")
	private String lookupNotes;

	@Column(name = "lookup_value")
	private String lookupValue;

	private byte status;

	// bi-directional many-to-one association to ShLookupCategory
	@ManyToOne
	@JoinColumn(name = "sh_lookup_catg_id")
	private ShLookupCategory shLookupCategory;

	public ShLookup() {
	}

	public int getShLookupId() {
		return this.shLookupId;
	}

	public void setShLookupId(int shLookupId) {
		this.shLookupId = shLookupId;
	}

	public int getLanguageId() {
		return this.languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public String getLookupDescription() {
		return this.lookupDescription;
	}

	public void setLookupDescription(String lookupDescription) {
		this.lookupDescription = lookupDescription;
	}

	public String getLookupNotes() {
		return this.lookupNotes;
	}

	public void setLookupNotes(String lookupNotes) {
		this.lookupNotes = lookupNotes;
	}

	public String getLookupValue() {
		return this.lookupValue;
	}

	public void setLookupValue(String lookupValue) {
		this.lookupValue = lookupValue;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public ShLookupCategory getShLookupCategory() {
		return this.shLookupCategory;
	}

	public void setShLookupCategory(ShLookupCategory shLookupCategory) {
		this.shLookupCategory = shLookupCategory;
	}

}