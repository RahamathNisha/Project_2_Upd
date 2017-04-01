package com.niit.collaboration.dao;

import java.util.List;

import com.*;
import com.niit.collaboration.model.Job;
import com.niit.collaboration.model.JobApplication;

public interface JobDAO
{
	
	public boolean postjob(Job job);
	
	public boolean updatejob(Job job);
	
	public List<Job> getAllVacancies();
	
	public boolean applyforjob(JobApplication jobapplication);
	
	public boolean updatejobapplication(JobApplication jobapplication);
	
	

	public List<JobApplication> listAllAppliedJobs(String useremail);
}
