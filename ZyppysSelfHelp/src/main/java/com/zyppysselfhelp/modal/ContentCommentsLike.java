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

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * The persistent class for the content_comments_likes database table.
 * 
 */
@Entity
@Table(name = "content_comments_likes")
@NamedQuery(name = "ContentCommentsLike.findAll", query = "SELECT c FROM ContentCommentsLike c")
public class ContentCommentsLike extends Audit {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String comment;

	@Column(name = "customer_id")
	private int customerId;

	private byte likes;

	private byte status;

	// bi-directional many-to-one association to Content
	@ManyToOne
	@JoinColumn(name = "content_id")
	@JsonBackReference
	private Content content;

	public ContentCommentsLike() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public byte getLikes() {
		return this.likes;
	}

	public void setLikes(byte likes) {
		this.likes = likes;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public Content getContent() {
		return this.content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

}