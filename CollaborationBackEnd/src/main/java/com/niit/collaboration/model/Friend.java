package com.niit.collaboration.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.niit.collaboration.dao.impl.UserDAOImpl;
import com.niit.collaboration.model.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Entity
@Table(name="C_FRIEND")
@Component
public class Friend extends BaseDomain 
{
	private static final Logger log = LoggerFactory.getLogger(Friend.class);

@Id
private int id;

@Column(name="user_id")
private String userID;

@Column(name="friend_id")
private String friendID;

private String status;

@Column(name="is_online")
private char isOnline;

public int getId() {
	return id;
}

public void setId(int id) {

	this.id = id;
}

public String getUserID() {
	return userID;
}

public void setUserID(String userID) {
	this.userID = userID;
}

public String getFriendID() {
	return friendID;
}

public void setFriendID(String friendID) {
	this.friendID = friendID;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public char getIsOnline() {
	return isOnline;
}

public void setIsOnline(char isOnline) {
	this.isOnline = isOnline;
}




}
