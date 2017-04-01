package com.niit.collaboration.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.dao.UserForumDAO;
import com.niit.collaboration.model.UserForum;
import com.niit.collaboration.model.UserForumComments;

@EnableTransactionManagement
@Repository("userForumDao")
public class UserForumDAO_Impl implements UserForumDAO {

	private static final Logger log = LoggerFactory.getLogger(BlogDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public UserForumDAO_Impl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<UserForum> listAllForums() {
		List<UserForum> allForums = null;
		try{
			
			log.debug("Method => getAllBlogs() execution is starting");
			allForums = sessionFactory.getCurrentSession().createQuery("FROM UserForum").list();
			if(allForums==null || allForums.isEmpty()){
				log.debug("Record not found in UserForum table");
			}
		}
		catch(HibernateException ex){
			log.debug("Fetch Error :" + ex.getMessage());
			ex.printStackTrace();
		}
		return allForums;
	}

	@Transactional
	public boolean addForum(UserForum userforum) {
		try
		{
			log.debug("Method => addForum() execution is starting");
			sessionFactory.getCurrentSession().saveOrUpdate(userforum);
			return true;
		}
		catch(HibernateException ex){
			log.debug("Data Save Error :" + ex.getMessage());
			ex.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<UserForum> getForumByID(int forumid) {
		List<UserForum> allForums = null;
		try{
			
			log.debug("Method => getForumByID() execution is starting");
			allForums = sessionFactory.getCurrentSession().createQuery("FROM UserForum where forumid = " + forumid).list();
			if(allForums==null || allForums.isEmpty()){
				log.debug("Record not found in UserForum table");
			}
		}
		catch(HibernateException ex){
			log.debug("Fetch Error :" + ex.getMessage());
			ex.printStackTrace();
		}
		return allForums;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<UserForumComments> listAllForumComments(int forumid) {
		List<UserForumComments> allForumscmts = null;
		try{
			
			log.debug("Method => listAllForumComments() execution is starting");
			allForumscmts = sessionFactory.getCurrentSession().createQuery("FROM UserForumComments where forumid = " + forumid).list();
			if(allForumscmts==null || allForumscmts.isEmpty()){
				log.debug("Record not found in UserForum comments table");
			}
		}
		catch(HibernateException ex){
			log.debug("Fetch Error :" + ex.getMessage());
			ex.printStackTrace();
		}
		return allForumscmts;
	}

	@Transactional
	public boolean updateApprove(int forumid, char flag) {
		try{
			Session session = sessionFactory.getCurrentSession();
	        Query query = session.createQuery("update UserForum set Approve = '" + flag + "' where id = " + forumid);
			return query.executeUpdate()==1 ? true : false;
		}
		catch(HibernateException ex){
			log.debug("Data update Error :" + ex.getMessage());
			ex.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean getUpdateLike(int forumid) {
		try{
			Session session = sessionFactory.getCurrentSession();
	        Query query = session.createQuery("update UserForum set likes = likes + 1 where id = " + forumid);
			return query.executeUpdate()==1 ? true : false;
		}
		catch(HibernateException ex){
			log.debug("Data update Error :" + ex.getMessage());
			ex.printStackTrace();
			return false;
		}

	}

	@Transactional
	public boolean addForumComment(UserForumComments userforumcmt) {
		try
		{
			log.debug("Method => addForumComment() execution is starting");
			sessionFactory.getCurrentSession().save(userforumcmt);
			return true;
		}
		catch(HibernateException ex){
			log.debug("Data Save Error :" + ex.getMessage());
			ex.printStackTrace();
			return false;
		}
	}

	@Transactional
	public void updateForumCommentsCount(int forumid) {
		try{
			Session session = sessionFactory.getCurrentSession();
	        Query query = session.createQuery("update UserForum set countcmts = countcmts + 1 where id = " + forumid);
			query.executeUpdate();
		}
		catch(HibernateException ex){
			log.debug("Data update Error :" + ex.getMessage());
			ex.printStackTrace();
		}
	}

	@Transactional
	public boolean deleteForum(int forumid) {
		try{
			Session session = sessionFactory.getCurrentSession();
	        Query query = session.createQuery("update UserForum set Approve = 'D' where id = " + forumid);
			return query.executeUpdate()==1 ? true : false;
		}
		catch(HibernateException ex){
			log.debug("Data update Error :" + ex.getMessage());
			ex.printStackTrace();
			return false;
		}		
	}
}
