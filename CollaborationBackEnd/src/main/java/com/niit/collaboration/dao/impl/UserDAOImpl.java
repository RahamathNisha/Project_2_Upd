package com.niit.collaboration.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.dao.UserDAO;
import com.niit.collaboration.model.UserDetails;

@Repository("UserDAO")
public class UserDAOImpl implements  UserDAO {
	
	private static final Logger log = LoggerFactory.getLogger(UserDAOImpl.class);
			
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	UserDetails user;
		
	public UserDAOImpl(SessionFactory sessionFactory)
	{
		log.debug("Starting of the method  TUserDAOImpl");
		this.sessionFactory = sessionFactory;
		log.debug("Ending of the method  TUserDAOImpl");		
	}		
			
	@Transactional
	public boolean saveUser(UserDetails user) {
		log.debug("Starting of the method  save");
		try {
			sessionFactory.getCurrentSession().save(user);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}		
	}
	
	@Transactional
	public boolean updateUser(UserDetails user) {
		
		log.debug("Starting of the method  update");
		log.debug("update the UserDetails " + user);
		try {
			sessionFactory.openSession().update(user);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			log.debug("error in update "+e);
			return false;
		}						
	}
	
	
	public boolean deleteUserById(String id)
	{
		log.debug("Starting of the method delete with the id :"+ id);
		UserDetails user =	(UserDetails)sessionFactory.openSession().get(UserDetails.class, id);
		sessionFactory.openSession().delete(user);
		return true;
	}
	@Transactional
	public UserDetails getUserById(String id) {
		log.debug("Starting of the method  get with the id :"+ id);
		
		user =	(UserDetails)sessionFactory.getCurrentSession().get(UserDetails.class, id);
		
		log.debug("user:" + user);
				return user;						
	}

	@Transactional
	public List<UserDetails> getAllUsers() {
		log.debug("Starting of the method  list");
		String hql = "from UserDetails";
		
		Query query=  sessionFactory.getCurrentSession().createQuery(hql);
		log.debug("Ending of the method  TUserDAOImpl");
		return  query.list();				
	}

	@Transactional
	public UserDetails validate(String id, String password) {
		log.debug("Starting of the method  validate");
		//select * from User where id= ' Srinivas' and password = 'Srinivas'
		String hql = "from UserDetails  where id= '" + id +"' and password = '" + password+"'";
		
		Query query=  sessionFactory.getCurrentSession().createQuery(hql);
		log.debug("Ending of the method  TUserDAOImpl");
		return  (UserDetails)query.uniqueResult();		
	}
}