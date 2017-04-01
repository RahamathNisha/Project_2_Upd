package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.niit.collaboration.dao.ForumCategoryDAO;
import com.niit.collaboration.dao.FriendDAO;
import com.niit.collaboration.model.ForumCategory;
import com.niit.collaboration.model.Friend;

public class ForumCategoryDAOTest {

static ForumCategoryDAO fcDAO;
	
	@BeforeClass
	public static void init()
	{
		AnnotationConfigWebApplicationContext ctx=new AnnotationConfigWebApplicationContext();
		ctx.scan("com.niit.collaboration");
		ctx.refresh();
		fcDAO=(ForumCategoryDAO)ctx.getBean("fcDAO");
	}
	
	@Test
	public void getAllForumCategory()
	{
		assertNotNull(fcDAO.getAllForumCategory());
		
	}
	
	@Test
	public void forumCategoryUpdate()
	{
		ForumCategory fc=new ForumCategory();
		fc.setForumcatname("ghu");
	}
	
	@Test
	public void getForumCategoryByID()
	{
		ForumCategory frnd=fcDAO.getForumCategoryByID(1);
		  assertNotNull(frnd);

	}
	
}


