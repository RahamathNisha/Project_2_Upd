package com.niit.collaboration.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration.dao.UserDAO;
import com.niit.collaboration.dao.impl.UserDAOImpl;
import com.niit.collaboration.model.UserDetails;
import com.niit.collaboration.model.UserDetails;

@RestController
public class UserController {
	
	 private static final Logger log = LoggerFactory.getLogger(UserController.class);
		

	@Autowired
	UserDetails user;

	@Autowired
	UserDAO userDAO;

	
	@RequestMapping(value = "/tusers", method = RequestMethod.GET)
	public ResponseEntity<List<UserDetails>> getAllUsers() {
		log.debug("Starting of the method getAllUsers" );
		List<UserDetails> users = userDAO.getAllUsers();

		if (users.isEmpty()) {
			user.setErrorCode("404");
			user.setErrorMessage("No Users are available");
		}
		log.debug("Ending of the method getAllUsers" );
		return new ResponseEntity<List<UserDetails>>(users, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/tuser/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDetails> getUserDetails(@PathVariable("id") String id) {
		log.debug("Starting of the method getUserDetails" );
		user = userDAO.getUserById(id);
		
		if (user== null) {
           user = new UserDetails();
			user.setErrorCode("404"); // NLP NullPointerException
			user.setErrorMessage("Iser does not exist with this id :" + id);
		}
		log.debug("Ending of the method getUserDetails" );
		return new ResponseEntity<UserDetails>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/authenticate/", method = RequestMethod.POST)
	public ResponseEntity<UserDetails> login(@RequestBody UserDetails user, HttpSession session) {
		log.debug("Starting of the method login" );
		
		String id = user.getId();
		 user = userDAO.validate(user.getId(), user.getPassword());
		if (user == null) {
			user = new UserDetails();
			user.setErrorCode("404"); // NLP NullPointerException
			user.setErrorMessage("User does not exist with this id:" + id);
		} else {
			user.setIsOnline('Y');
			user.setErrorCode("200"); // NLP NullPointerException
			user.setErrorMessage("You have successfully loggedIn");
			userDAO.updateUser(user);

			session.setAttribute("loggedInUserID", user.getId());
		}
		log.debug("Ending of the method login" );
		return new ResponseEntity<UserDetails>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/tlogout/", method = RequestMethod.GET)
	public ResponseEntity<UserDetails> logout(HttpSession session) {
		log.debug("Starting of the method logout" );
		
		String loggedInUserID= (String) session.getAttribute("loggedInUserID");
		
		log.debug("loggedInUserID : " + loggedInUserID);
		
		user =userDAO.getUserById(loggedInUserID);
		
		log.debug("user:"+ user);
		user.setIsOnline('N');

		session.invalidate();

		if (userDAO.updateUser(user)) {
			user.setErrorCode("200");
			user.setErrorMessage("You have logged out successfully");
		} else {
			user.setErrorCode("404");
			user.setErrorMessage("You could not logged. please contact admin");
		}
		log.debug("Ending of the method logout" );
		return new ResponseEntity<UserDetails>(user, HttpStatus.OK);

	}

	@RequestMapping(value = "/tregister/", method = RequestMethod.POST)
	public ResponseEntity<UserDetails> register(@RequestBody UserDetails user) 
	{
		log.debug("Starting of the method register" );
		if (userDAO.getUserById(user.getId()) != null) {
			user.setErrorCode("404");
			user.setErrorMessage("With this id, the record is already exist.  Please choose another id");
		} else {
			user.setStatus('N');
			if (userDAO.saveUser(user)) {
				user.setErrorCode("200");
				user.setErrorMessage("Your Registration is Successfull");

			} else {
				user.setErrorCode("405");
				user.setErrorMessage("Unable process your registration. Please contact Admin");
			}
		}
		log.debug("Ending of the method register" );
		return new ResponseEntity<UserDetails>(user, HttpStatus.OK);

	}
	
	
	
	@RequestMapping(value = "/taccept/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDetails> accept(@PathVariable("id") String id) {
		log.debug("Starting of the method register" );				  
		user = updateStatus(id, 'A',"");			
		return new  ResponseEntity<UserDetails>(user,HttpStatus.OK);		
	}
	
	
	@RequestMapping(value = "/treject/{id}/{reason}", method = RequestMethod.GET)
	public ResponseEntity<UserDetails> reject(@PathVariable("id") String id ,@PathVariable("reason")  String reason) {
		log.debug("Starting of the method register" );
		user = updateStatus(id, 'R', reason);
		return new  ResponseEntity<UserDetails>(user,HttpStatus.OK);
	}
	
	
	private UserDetails updateStatus(String id, char status, String reason)
	{
		log.debug("Starting of the method updateStatus" );
		
		log.debug("status: " + status);
		user =userDAO.getUserById(id);	  
		if(user==null)
		{
			user = new UserDetails();
			user.setErrorCode("404");
			user.setErrorMessage("Could not update the status");
		}
		else
		{			
			user.setStatus(status);
			user.setReason(reason);
			userDAO.updateUser(user);
		}
		log.debug("Ending of the method updateStatus" );
		return user;	  
	}
}