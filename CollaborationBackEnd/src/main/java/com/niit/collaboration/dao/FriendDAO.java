package com.niit.collaboration.dao;

import java.util.List;

import com.niit.collaboration.model.Friend;

public interface FriendDAO 
{
	public List<Friend> getAllFriends(String userID);
	public Friend getFriendById(String userID,String friendID);
	public boolean saveFriend(Friend friend);
	public boolean updateFriend(Friend friend);
	public void deleteFriendById(String userID,String friendID);
	public List<Friend> getNewFriendRequests(String userID);
	public void setOnline(String userID);
	public void setOffLine(String userID);

}
