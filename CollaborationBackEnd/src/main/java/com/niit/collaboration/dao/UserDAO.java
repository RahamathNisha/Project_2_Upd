package com.niit.collaboration.dao;
import java.util.List;

import com.niit.collaboration.model.UserDetails;
public interface UserDAO 
{
	public boolean saveUser(UserDetails user);
	public boolean updateUser(UserDetails user);
	public UserDetails getUserById(String i);
	public List<UserDetails> getAllUsers();
	public UserDetails validate(String i,String password);
	public boolean deleteUserById(String id);

}
