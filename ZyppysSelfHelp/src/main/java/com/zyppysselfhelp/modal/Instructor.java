
package com.zyppysselfhelp.modal;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * The persistent class for the instructor database table.
 * 
 */
@Entity
@Table(name = "instructor")
@NamedQuery(name = "Instructor.findAll", query = "SELECT i FROM Instructor i")
public class Instructor extends Audit {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "instructor_id")
	private int instructorId;

	@Lob
	@Column(name = "about_instructor")
	private String aboutInstructor;

	private String email;

	@Column(name = "instructor_name")
	private String instructorName;

	private String phone;

	@Column(name = "profile_pic")
	private String profilePic;

	private byte status;

	// bi-directional many-to-one association to Content
	@OneToMany(mappedBy = "instructor",cascade=CascadeType.ALL)
	//@JsonBackReference
	private List<Content> contents;

	public Instructor() {
	}

	public int getInstructorId() {
		return this.instructorId;
	}

	public void setInstructorId(int instructorId) {
		this.instructorId = instructorId;
	}

	public String getAboutInstructor() {
		return this.aboutInstructor;
	}

	public void setAboutInstructor(String aboutInstructor) {
		this.aboutInstructor = aboutInstructor;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getInstructorName() {
		return this.instructorName;
	}

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProfilePic() {
		return this.profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public List<Content> getContents() {
		return this.contents;
	}

	public void setContents(List<Content> contents) {
		this.contents = contents;
	}

	public Content addContent(Content content) {
		getContents().add(content);
		content.setInstructor(this);

		return content;
	}

	public Content removeContent(Content content) {
		getContents().remove(content);
		content.setInstructor(null);

		return content;
	}

}