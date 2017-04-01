package com.niit.collaboration.dao.impl;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.dao.BlogDAO;
import com.niit.collaboration.model.Blog;

@Repository("blogDAO")
public class BlogDAOImpl implements BlogDAO
{
	private static final Logger logger = LoggerFactory.getLogger(BlogDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	Blog userDetails;
	
	public BlogDAOImpl()
	{
		
	}
	
	public BlogDAOImpl(SessionFactory sessionFactory) 
	{
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public boolean updateBlog(Blog blog) 
	{
		logger.debug("updating Blog..");
		sessionFactory.getCurrentSession().saveOrUpdate(blog);
        return true;		
	}

	@Transactional
	public boolean deleteBlogById(int blogId) 
	{
		logger.debug("deleting Blog..");
		Blog blogToDelete = new Blog();
		blogToDelete.setBlogId(blogId);
		sessionFactory.getCurrentSession().delete(blogToDelete);
		return true;
	}

	@Transactional
	public Blog getBlogById(int blogId) 
	{
		logger.debug("getting blog:"+blogId);
		String hql = "from Blog where id=" + "'" + blogId + "'";
		@SuppressWarnings({ })
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings({ "unchecked" })
		List<Blog> listBlog = (List<Blog>) query.list();

		if (listBlog != null && !listBlog.isEmpty()) {
			return listBlog.get(0);
		}
		return null;
	}

	public List<Blog> getAllBlogs() {
		logger.debug("getting all Blogs..");
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")

		List<Blog> blogDetailsList = session.createQuery("from Blog").list();
		session.close();
		logger.debug("--------->>>>>> Returning Blog Details");
		return blogDetailsList;
	}

	@Transactional
	public boolean saveBlog(Blog blogDetails) 
	{
		logger.debug("saving Blog..");
		sessionFactory.getCurrentSession().save(blogDetails);
		return true;
	}
}