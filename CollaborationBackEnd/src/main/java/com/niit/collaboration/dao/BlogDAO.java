package com.niit.collaboration.dao;

import java.util.List;

import com.niit.collaboration.model.Blog;
public interface BlogDAO

{
	public List<Blog> getAllBlogs();
	
    public boolean updateBlog(Blog blog);
    
    public boolean deleteBlogById(int id);
    public boolean saveBlog(Blog blogDetails);
    public Blog getBlogById(int id);
	
}
