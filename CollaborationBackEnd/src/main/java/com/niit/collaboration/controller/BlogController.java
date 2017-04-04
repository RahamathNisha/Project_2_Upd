package com.niit.collaboration.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.util.UriComponentsBuilder;

import com.niit.collaboration.dao.BlogDAO;
import com.niit.collaboration.model.Blog;

@RestController
public class BlogController implements ServletContextAware {
	@Autowired
	private BlogDAO blogDAO;
	
	//@Autowired
	ServletContext req;
	
	private static final Logger log = LoggerFactory.getLogger(BlogController.class);
	
	@GetMapping("/BlogDetails/")
	public ResponseEntity<List<Blog>> listAllUserDetails(){
		List<Blog> blogDetails = blogDAO.getAllBlogs();
		log.debug("Data retrieved successfully");
		if (blogDetails.isEmpty()){
			
			return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);
			
		}
		
		return new ResponseEntity<List<Blog>>(blogDetails, HttpStatus.OK);
		
	}
	
	@GetMapping("/Blog/{username}")
	public ResponseEntity<Blog> getBlog(@ModelAttribute("username") int id){
		
		Blog Blog = blogDAO.getBlogById(id);
		if (Blog == null){
			return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
			
		}
		return new ResponseEntity<Blog>(Blog, HttpStatus.OK);
	}
	
	
	@PostMapping("/BlogSave/")
	public ResponseEntity<Void> createBlog(@RequestBody Blog blog, UriComponentsBuilder ucBuilder)
	{
	log.debug("@@@@@@@@@@@@@---------------->>>In Blog Detail");
	log.debug(blog.getTitle()+" "+blog.getDescription());
		if(blogDAO.getBlogById(blog.getId())!= null)
		{
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	
		blogDAO.saveBlog(blog);
	
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/Blog/{id}").buildAndExpand(blog.getTitle()).toUri());
	    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("/Blog/{username}")
	public ResponseEntity<Blog> updateBlog(@ModelAttribute("username") int id, @RequestBody Blog blog){
		
		blog = blogDAO.getBlogById(id);
		if (blog == null){
			return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
			
		}
		blogDAO.updateBlog(blog);
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
	
	
	@PostMapping("/admin/delete/{user}")
	public ResponseEntity<Blog> deleteBlog(@ModelAttribute("user") int id)
	{
		
		log.debug("id is "+id);
		log.debug("inside BlogController delete Blog");
		Blog Blog = blogDAO.getBlogById(id);
		if (Blog == null){
			return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
			
		}		
		blogDAO.deleteBlogById(id);
		return new ResponseEntity<Blog>(HttpStatus.NO_CONTENT);

	}

	public void setServletContext(ServletContext arg0) {
		req = arg0;
	}

}