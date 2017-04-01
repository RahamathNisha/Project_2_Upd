package test;

import static org.junit.Assert.*;

import javax.validation.constraints.AssertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.niit.collaboration.dao.BlogDAO;
import com.niit.collaboration.model.Blog;

public class BlogDAOTest {

	static BlogDAO blogdao;
	
	@BeforeClass
	public static void init(){
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.scan("com.niit.collaboration");
		ctx.refresh();
		
		blogdao = (BlogDAO)ctx.getBean("blogDAO");
	}
	
	@Test
	public void saveBlog() {
		Blog blog = new Blog();
		blog.setBlogId(2);
		blog.setTitle("b2");
		blog.setDescription("d2");
		blog.setErrorCode("1234");
		blog.setErrorMessage("abc");
		assertTrue(blogdao.saveBlog(blog));
	}
	
	@Test
	public void updateBlog()
	{
		Blog blog=new Blog();
		blog.setBlogId(2);
		blog.setTitle("g");
		blog.setDescription("h");
		blog.setErrorCode("345");
		blog.setErrorMessage("err");
		assertTrue(blogdao.updateBlog(blog));
	}

}
