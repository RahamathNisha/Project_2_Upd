package com.niit.collaboration.dao.impl;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.dao.JobDAO;
import com.niit.collaboration.model.Job;
import com.niit.collaboration.model.JobApplication;

@Repository
public class JobDAOImpl implements JobDAO
{

	private static final Logger log = LoggerFactory.getLogger(JobDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public JobDAOImpl()
	{
			
	}
	
	public JobDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public boolean postjob(Job job) {
		try{
			sessionFactory.getCurrentSession().save(job);
			log.debug("job posted successfully..");
		}
		catch(HibernateException ex){
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	@Transactional
	public boolean updatejob(Job job) {
		try{
			sessionFactory.getCurrentSession().update(job);
			log.debug("job details updated successfully..");
		}
		catch(HibernateException ex){
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Job> getAllVacancies() {
		List<Job> allJobs = null;
		try{
			
			log.debug("Method => getAllVacancies() execution is starting");
			allJobs = sessionFactory.getCurrentSession().createQuery("FROM Job where status = 'V'").list();
			if(allJobs==null || allJobs.isEmpty()){
				log.debug("Record not found in Userrole table");
			}
		}
		catch(HibernateException ex){
			log.debug("Fetch Error :" + ex.getMessage());
			ex.printStackTrace();
		}
		return allJobs;
	}
		
	@SuppressWarnings("unchecked")
	@Transactional
	public List<JobApplication> listAllAppliedJobs(String useremail) {
		List<JobApplication> allAppldJobs = null;
		try{
			
			log.debug("Method => getAllVacancies() execution is starting");
			allAppldJobs = sessionFactory.getCurrentSession().createQuery("FROM JobApplication ").list();
			if(allAppldJobs==null || allAppldJobs.isEmpty()){
				log.debug("Records are not found in Job applicatione table");
			}
		}
		catch(HibernateException ex){
			log.debug("Fetch Error :" + ex.getMessage());
			ex.printStackTrace();
		}
		return allAppldJobs;
	}
	
	@Transactional
	public boolean applyforjob(JobApplication jobapplication) {
		try{
			sessionFactory.getCurrentSession().save(jobapplication);
			log.debug("apply job success..");
			sessionFactory.getCurrentSession().flush();
		}
		catch(HibernateException ex){
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	@Transactional
	public boolean updatejobapplication(JobApplication jobapplication) {
		try{
			sessionFactory.getCurrentSession().update(jobapplication);
			log.debug("job details updated..");
		}
		catch(HibernateException ex){
			ex.printStackTrace();
			log.debug("error in updating job..");
			return false;
		}
		return true;
	}

	
}