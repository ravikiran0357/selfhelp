package com.zyppysselfhelp.modal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * The persistent class for the content_location database table.
 * 
 */
@Entity
@Table(name = "content_location")
@NamedQuery(name = "ContentLocation.findAll", query = "SELECT c FROM ContentLocation c")
public class ContentLocation extends Audit {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "content_location_id")
	private int contentLocationId;

	@Lob
	@Column(name = "actual_content_location")
	private String actualContentLocation;

	@Column(name = "content_quality_type")
	private int contentQualityType;

	// bi-directional many-to-one association to Content
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "content_id")
	@JsonBackReference
	private Content content;

	public ContentLocation() {
	}

	public int getContentLocationId() {
		return contentLocationId;
	}

	public void setContentLocationId(int contentLocationId) {
		this.contentLocationId = contentLocationId;
	}

	public String getActualContentLocation() {
		return this.actualContentLocation;
	}

	public void setActualContentLocation(String actualContentLocation) {
		this.actualContentLocation = actualContentLocation;
	}

	public int getContentQualityType() {
		return this.contentQualityType;
	}

	public void setContentQualityType(int contentQualityType) {
		this.contentQualityType = contentQualityType;
	}

	public Content getContent() {
		return this.content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

}