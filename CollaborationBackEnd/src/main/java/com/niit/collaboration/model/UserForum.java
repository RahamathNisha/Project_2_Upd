package com.niit.collaboration.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "UserForum")
@Component
public class UserForum {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private int forumid;
	
	@Column
	@NotEmpty(message = "Forum title should not be left blank")
	private String title;
	
	@Column
	@NotEmpty(message = "Forum description should not be left blank")
	private String description;

	@Column
	private String createdate;

	@Column
	private String modifiedat;
	
	@Column
	@NotEmpty(message = "Forum description should not be left blank")
	private String forumcategory;

	@Column
	private int likes;
	
	@Column
	private char approve;

	@Column
	private String useremail;

	@Column
	private int countcmts;
	
	public int getCountcmts() {
		return countcmts;
	}

	public void setCountcmts(int countcmts) {
		this.countcmts = countcmts;
	}

	public int getForumid() {
		return forumid;
	}

	public void setForumid(int forumid) {
		this.forumid = forumid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getModifiedat() {
		return modifiedat;
	}

	public void setModifiedat(String modifiedat) {
		this.modifiedat = modifiedat;
	}

	public String getForumcategory() {
		return forumcategory;
	}

	public void setForumcategory(String forumcategory) {
		this.forumcategory = forumcategory;
	}

	public char getApprove() {
		return approve;
	}

	public void setApprove(char approve) {
		this.approve = approve;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

}
