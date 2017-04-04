package com.niit.collaboration.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

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

import com.niit.collaboration.dao.ForumCategoryDAO;
import com.niit.collaboration.dao.UserForumDAO;
import com.niit.collaboration.model.Forum;
import com.niit.collaboration.model.ForumCategory;
import com.niit.collaboration.model.UserForum;
import com.niit.collaboration.model.UserForumComments;

@RestController
public class ForumController 
{
	//@Autowired
	UserForumDAO service;

	//@Autowired
	ForumCategoryDAO f_catdao;
	
	private static final Logger log = LoggerFactory.getLogger(ForumController.class);

	@RequestMapping(value = "/adduserforum/", method = RequestMethod.POST)
	public ResponseEntity<UserForum> createUserForum(@RequestBody UserForum userforum, HttpSession session)
	{
		log.debug("calling => createUserForum() method");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		userforum.setCreatedate(dateFormat.format(date));
		userforum.setApprove('N');
		userforum.setUseremail((String)session.getAttribute("loggeduser"));
		userforum.setModifiedat(dateFormat.format(date));
		userforum.setCountcmts(0);
		
		boolean flag = service.addForum(userforum);
		
		if(!flag){
			log.debug("error in calling => createUserType() method");
			return new ResponseEntity<UserForum>(userforum, HttpStatus.CONFLICT);
		}
		else
		{
			log.debug("Update user forum");
			return new ResponseEntity<UserForum>(userforum, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/alluserforum", method = RequestMethod.GET)
	public ResponseEntity<List<UserForum>> listAllUserForum()	{

		log.debug("calling => listAllUserForum() method");
		List<UserForum> lsts = service.listAllForums();
		if(lsts.isEmpty()){
			return new ResponseEntity<List<UserForum>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UserForum>>(lsts, HttpStatus.OK);
	}
           
	
	@RequestMapping(value = "/alluserforumcats", method = RequestMethod.GET)
	public ResponseEntity<List<ForumCategory>> listAllUserForumCategory()	{

		log.debug("calling => listAllUserForumCategory() method");
		List<ForumCategory> lsts = f_catdao.getAllForumCategory();
		if(lsts.isEmpty()){
			return new ResponseEntity<List<ForumCategory>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ForumCategory>>(lsts, HttpStatus.OK);
	}

	@RequestMapping(value = "/alluserforumcmts/{forumid}", method = RequestMethod.GET)
	public ResponseEntity<List<UserForumComments>> listAllUserForumComments(@PathVariable("forumid") int forumid)	{

		log.debug("calling => listAllUserForumComments() method");
		List<UserForumComments> lsts = service.listAllForumComments(forumid);
		if(lsts.isEmpty()){
			return new ResponseEntity<List<UserForumComments>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UserForumComments>>(lsts, HttpStatus.OK);
	}

	@RequestMapping(value = "/getforumbyid/{forumid}", method = RequestMethod.GET)
	public ResponseEntity<List<UserForum>> getforumbyid(@PathVariable("forumid") int forumid)	{

		log.debug("calling => getforumbyid() method");
		List<UserForum> userforum = service.getForumByID(forumid);
		
		if(userforum==null){
			return new ResponseEntity<List<UserForum>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UserForum>>(userforum, HttpStatus.OK);
	}

	@RequestMapping(value = "/getapproveforum/{forumid}", method = RequestMethod.POST)
	public ResponseEntity<UserForum> getapproveforum(@PathVariable("forumid") int forumid)	{

		log.debug("calling => getapproveforum() method");
		boolean flag = service.updateApprove(forumid, 'Y');
		if(!flag){
			return new ResponseEntity<UserForum>(HttpStatus.BAD_REQUEST);
		}
		//UserForum userforum = service.getForumByID(forumid);
		UserForum  userforum = new  UserForum();
		return new ResponseEntity<UserForum>(userforum, HttpStatus.OK);
	}	

	@RequestMapping(value = "/gerejectforum/{forumid}", method = RequestMethod.POST)
	public ResponseEntity<UserForum> gerejectedforum(@PathVariable("forumid") int forumid)	{

		log.debug("calling => getrejectedforum() method");
		boolean flag = service.updateApprove(forumid, 'R');
		if(!flag){
			return new ResponseEntity<UserForum>(HttpStatus.BAD_REQUEST);
		}
		//UserForum userforum = service.getForumByID(forumid);
		UserForum  userforum = new  UserForum();
		return new ResponseEntity<UserForum>(userforum, HttpStatus.OK);
	}	

	@RequestMapping(value = "/getupdateforumlike/{forumid}", method = RequestMethod.POST)
	public ResponseEntity<UserForum> getupdatelike(@PathVariable("forumid") int forumid)	{

		log.debug("calling => getupdatelike() method");
		boolean flag = service.getUpdateLike(forumid);
		if(!flag){
			return new ResponseEntity<UserForum>(HttpStatus.BAD_REQUEST);
		}
		//UserForum userforum = service.getForumByID(forumid);
		UserForum  userforum = new  UserForum();
		return new ResponseEntity<UserForum>(userforum, HttpStatus.OK);
	}	
	
	@RequestMapping(value = "/adduserforumcmt/", method = RequestMethod.POST)
	public ResponseEntity<UserForumComments> createUserForumComment(@RequestBody UserForumComments userforumcmt, HttpSession session)
	{
		log.debug("calling => createUserForumComment() method");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		userforumcmt.setDateofcomments(dateFormat.format(date));
		//userforumcmt.setUseremail(userforumcmt.getUseremail());

		boolean flag = service.addForumComment(userforumcmt);
		
		if(!flag){
			log.debug("error in calling => createUserForumComment() method");
			return new ResponseEntity<UserForumComments>(userforumcmt, HttpStatus.CONFLICT);
		}
		else
		{
			//update number of comments given by user
			service.updateForumCommentsCount(userforumcmt.getForumid());
			log.debug("Update user forum comment");
			return new ResponseEntity<UserForumComments>(userforumcmt, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/getdeleteforum/{forumid}", method = RequestMethod.POST)
	public ResponseEntity<UserForum> getdeleteforum(@PathVariable("forumid") int forumid)	{

		log.debug("calling => getdeleteforum() method");
		boolean flag = service.deleteForum(forumid); 
		if(!flag){
			return new ResponseEntity<UserForum>(HttpStatus.BAD_REQUEST);
		}
		UserForum userforum = service.getForumByID(forumid).get(0);
		return new ResponseEntity<UserForum>(userforum, HttpStatus.OK);
	}	
}
