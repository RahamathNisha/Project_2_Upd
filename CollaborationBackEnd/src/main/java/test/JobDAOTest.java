package test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.niit.collaboration.dao.JobDAO;
import com.niit.collaboration.model.Job;
import com.niit.collaboration.model.JobApplication;

public class JobDAOTest {

	static JobDAO jobDAO;
	
	@BeforeClass
	public static void init()
	{
		AnnotationConfigWebApplicationContext ctx=new AnnotationConfigWebApplicationContext();
		ctx.scan("com.niit.collaboration");
		ctx.refresh();
		jobDAO=(JobDAO)ctx.getBean("jobDAO");
	}
	
	@Test
	public void PostJob()
	{
		Job job1=new Job();
		job1.setTitle("manager");
	    job1.setDescription("abc");
	    job1.setPostdate("2/3/2017");
	    job1.setQualification("MCA");
	    job1.setLocation("chennai");
	    job1.setExperience("5");
	    job1.setCompanyname("ABC");
	    job1.setStatus('y');
	    assertTrue(jobDAO.postjob(job1));
	}
	
	@Test
	public void updateJob()
	{
		Job job1=new Job();
		job1.setId(5);
		job1.setTitle("manager");
	    job1.setDescription("abc");
	    job1.setPostdate("2/3/2017");
	    job1.setQualification("MCA");
	    job1.setLocation("chennai");
	    job1.setExperience("6");
	    job1.setCompanyname("ABC");
	    job1.setStatus('y');
	    assertTrue(jobDAO.updatejob(job1));
	}
	
	@Test
	public void getAllVacancies()
	{
		assertNotNull(jobDAO.getAllVacancies());
	}
	
	@Test
	public void applyForJob()
	{
	  JobApplication jobApp=new JobApplication();
	  jobApp.setJob_id(2);
	  jobApp.setRemark("good");
	  jobApp.setStatus('y');
	  jobApp.setUseremail("a@gmail.com");
	  assertTrue(jobDAO.applyforjob(jobApp));
	}
	
	@Test
	public void updatejobApplication()
	{
		JobApplication jobApp=new JobApplication();
		jobApp.setId(42);
		jobApp.setJob_id(2);
		jobApp.setRemark("good");
		jobApp.setStatus('y');
		jobApp.setUseremail("a@gmail.com");
		assertTrue(jobDAO.updatejobapplication(jobApp));  
	}
	
	@Test
	public void ListJobApplication()
	{		
		assertNotNull(jobDAO.listAllAppliedJobs("a@gmail.com"));		
	}
}