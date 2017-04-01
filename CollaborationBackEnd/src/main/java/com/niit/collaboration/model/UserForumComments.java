package com.niit.collaboration.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "UserForumComments")
@Component
public class UserForumComments {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private int id;

	@Column
	private String dateofcomments;
	
	@Column
	private int forumid;
	
	@Column
	@NotEmpty(message="Comments should not be left blank")
	@Size(max=200, message="Comments should not be exceeds 100 characters")
	public String comments;

	@Column
	private String useremail;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDateofcomments() {
		return dateofcomments;
	}

	public void setDateofcomments(String dateofcomments) {
		this.dateofcomments = dateofcomments;
	}

	public int getForumid() {
		return forumid;
	}

	public void setForumid(int forumid) {
		this.forumid = forumid;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
}