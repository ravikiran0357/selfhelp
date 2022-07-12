package com.zyppysselfhelp.modal;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The persistent class for the content database table.
 * 
 */
@Entity
@Table(name = "content")
@NamedQuery(name = "Content.findAll", query = "SELECT c FROM Content c")
//@SQLDelete(sql = "UPDATE content SET deleted = true WHERE id=?")
//@FilterDef(name = "deletedContentFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
//@Filter(name = "deletedContentFilter", condition = "deleted = :isDeleted")
public class Content extends Audit {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "content_id")
	private int contentId;

	@Column(name = "access_mode")
	private int accessMode;

	@Column(name = "content_format_extension")
	private String contentFormatExtension;

	@Column(name = "content_format_type")
	private String contentFormatType;

	@Column(name = "content_lookup_category_id")
	private int contentLookupCategoryId;

	@Column(name = "content_source")
	private String contentSource;

	private String description;

	private String duration;

	private String hashtags;

	@Column(name = "is_archieve")
	private byte isArchieve;

	@Column(name = "is_premium")
	private byte isPremium;

	@Column(name = "language_id")
	private int languageId;

	@Column(name = "likes_count")
	private int likesCount;

	@Column(name = "organization_id")
	private int organizationId;

	@Column(name = "preview_content_location")
	private String previewContentLocation;

	@Column(name = "shares_count")
	private int sharesCount;

	private byte status;

	private String title;

	@Column(name = "uploaded_from_lat")
	private BigDecimal uploadedFromLat;

	@Column(name = "uploaded_from_longcontentcol")
	private BigDecimal uploadedFromLongcontentcol;

	// bi-directional many-to-one association to ContentCommentsLike
	@OneToMany(mappedBy = "content")
	//@JsonIgnore
	@JsonManagedReference
	private List<ContentCommentsLike> contentCommentsLikes;

	// bi-directional many-to-one association to ContentLocation
	@OneToMany(mappedBy = "content", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
		      CascadeType.REFRESH })
//	@JsonIgnore
	@JsonManagedReference
	private List<ContentLocation> contentLocations;

	// bi-directional many-to-one association to Instructor
	@ManyToOne
	@JoinColumn(name = "instructor_id")
//	@JsonIgnore
	//@JsonManagedReference
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	private Instructor instructor;

	public Content() {
	}

	public int getContentId() {
		return this.contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public int getAccessMode() {
		return this.accessMode;
	}

	public void setAccessMode(int accessMode) {
		this.accessMode = accessMode;
	}

	public String getContentFormatExtension() {
		return contentFormatExtension;
	}

	public void setContentFormatExtension(String contentFormatExtension) {
		this.contentFormatExtension = contentFormatExtension;
	}

	public String getContentFormatType() {
		return contentFormatType;
	}

	public void setContentFormatType(String contentFormatType) {
		this.contentFormatType = contentFormatType;
	}

	public int getContentLookupCategoryId() {
		return this.contentLookupCategoryId;
	}

	public void setContentLookupCategoryId(int contentLookupCategoryId) {
		this.contentLookupCategoryId = contentLookupCategoryId;
	}

	public String getContentSource() {
		return this.contentSource;
	}

	public void setContentSource(String contentSource) {
		this.contentSource = contentSource;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDuration() {
		return this.duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getHashtags() {
		return this.hashtags;
	}

	public void setHashtags(String hashtags) {
		this.hashtags = hashtags;
	}

	public byte getIsArchieve() {
		return this.isArchieve;
	}

	public void setIsArchieve(byte isArchieve) {
		this.isArchieve = isArchieve;
	}

	public byte getIsPremium() {
		return this.isPremium;
	}

	public void setIsPremium(byte isPremium) {
		this.isPremium = isPremium;
	}

	public int getLanguageId() {
		return this.languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public int getLikesCount() {
		return this.likesCount;
	}

	public void setLikesCount(int likesCount) {
		this.likesCount = likesCount;
	}

	public int getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public String getPreviewContentLocation() {
		return this.previewContentLocation;
	}

	public void setPreviewContentLocation(String previewContentLocation) {
		this.previewContentLocation = previewContentLocation;
	}

	public int getSharesCount() {
		return this.sharesCount;
	}

	public void setSharesCount(int sharesCount) {
		this.sharesCount = sharesCount;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getUploadedFromLat() {
		return this.uploadedFromLat;
	}

	public void setUploadedFromLat(BigDecimal uploadedFromLat) {
		this.uploadedFromLat = uploadedFromLat;
	}

	public BigDecimal getUploadedFromLongcontentcol() {
		return this.uploadedFromLongcontentcol;
	}

	public void setUploadedFromLongcontentcol(BigDecimal uploadedFromLongcontentcol) {
		this.uploadedFromLongcontentcol = uploadedFromLongcontentcol;
	}

	public List<ContentCommentsLike> getContentCommentsLikes() {
		return this.contentCommentsLikes;
	}

	public void setContentCommentsLikes(List<ContentCommentsLike> contentCommentsLikes) {
		this.contentCommentsLikes = contentCommentsLikes;
	}

	public ContentCommentsLike addContentCommentsLike(ContentCommentsLike contentCommentsLike) {
		getContentCommentsLikes().add(contentCommentsLike);
		contentCommentsLike.setContent(this);

		return contentCommentsLike;
	}

	public ContentCommentsLike removeContentCommentsLike(ContentCommentsLike contentCommentsLike) {
		getContentCommentsLikes().remove(contentCommentsLike);
		contentCommentsLike.setContent(null);

		return contentCommentsLike;
	}

	public List<ContentLocation> getContentLocations() {
		return this.contentLocations;
	}

	public void setContentLocations(List<ContentLocation> contentLocations) {
		this.contentLocations = contentLocations;
	}

	public ContentLocation addContentLocation(ContentLocation contentLocation) {
		getContentLocations().add(contentLocation);
		contentLocation.setContent(this);

		return contentLocation;
	}

	public ContentLocation removeContentLocation(ContentLocation contentLocation) {
		getContentLocations().remove(contentLocation);
		contentLocation.setContent(null);

		return contentLocation;
	}

	public Instructor getInstructor() {
		return this.instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

}