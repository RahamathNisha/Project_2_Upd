package com.niit.collaboration.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.*;
import com.niit.collaboration.dao.JobDAO;
import com.niit.collaboration.model.Job;
import com.niit.collaboration.model.JobApplication;

@RestController
public class JobController {
	private static final Logger log = LoggerFactory.getLogger(JobController.class);

	//@Autowired
	JobDAO service;
	
	@RequestMapping(value = "/addjob/", method = RequestMethod.POST)
	public ResponseEntity<Job> createJob(@RequestBody Job job)
	{
		log.debug("calling => createJob() method");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		job.setPostdate(dateFormat.format(date));
		job.setStatus('V');
		
		boolean flag = service.postjob(job);
		
		if(!flag){
			log.debug("error in calling => createJob() method");
			return new ResponseEntity<Job>(job, HttpStatus.CONFLICT);
		}
		else
		{
			log.debug("Update user blog");
			return new ResponseEntity<Job>(job, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/alljobs", method = RequestMethod.GET)
	public ResponseEntity<List<Job>> listAllJobs()	{

		log.debug("calling => listAllJobs() method");
		List<Job> lsts = service.getAllVacancies();
		if(lsts.isEmpty()){
			return new ResponseEntity<List<Job>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Job>>(lsts, HttpStatus.OK);
	}

	@RequestMapping(value = "/allappliedjobs", method = RequestMethod.GET)
	public ResponseEntity<List<JobApplication>> listAllAppliedJobs(HttpSession session)	{

		log.debug("calling => listAllJobs() method");
		List<JobApplication> lsts = service.listAllAppliedJobs(session.getAttribute("loggeduser").toString());
		if(lsts.isEmpty()){
			return new ResponseEntity<List<JobApplication>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<JobApplication>>(lsts, HttpStatus.OK);
	}

	@RequestMapping(value = "/applyforjob/{jobid}", method = RequestMethod.POST)
	public ResponseEntity<Job> applyforjob(@PathVariable("jobid") int jobid, HttpSession session)
	{	
		log.debug("appforjob() method get execute");
		
		JobApplication jobapp = new JobApplication();
		jobapp.setJob_id(jobid);
		jobapp.setStatus('N');
		jobapp.setUseremail((String)session.getAttribute("loggeduser"));
		
		boolean flag = service.applyforjob(jobapp); 
		
		if(!flag){
			return new ResponseEntity<Job>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Job>(HttpStatus.OK);
	}	
}
