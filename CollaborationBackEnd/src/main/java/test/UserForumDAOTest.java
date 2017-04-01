package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.niit.collaboration.dao.FriendDAO;
import com.niit.collaboration.dao.UserForumDAO;
import com.niit.collaboration.model.UserForum;
import com.niit.collaboration.model.UserForumComments;

public class UserForumDAOTest {

static UserForumDAO ufDAO;
	
	@BeforeClass
	public static void init()
	{
		AnnotationConfigWebApplicationContext ctx=new AnnotationConfigWebApplicationContext();
		ctx.scan("com.niit.collaboration");
		ctx.refresh();
		ufDAO=(UserForumDAO)ctx.getBean("forumDAO");
	}
	@Test
	public void listAllForums()
	{
		assertNotNull(ufDAO.listAllForums());
	}

	@Test
	public void addForum()
	{
		UserForum ufd=new UserForum();
		ufd.setTitle("asd");
		ufd.setDescription("hjk");
		ufd.setCreatedate("12/3/2017");
		ufd.setModifiedat("15/3/2017");
		ufd.setForumcategory("fg");
		ufd.setLikes(5);
		ufd.setApprove('y');
		ufd.setUseremail("b@gmail.com");
		ufd.setCountcmts(10);
		assertTrue(ufDAO.addForum(ufd));
	}
	
	@Test
	public void getForumByID()
	{
		assertNotNull(ufDAO.getForumByID(2));
	}
	
	@Test
	public void getUpdateLike()
	{
		UserForum ufd=new UserForum();
		ufd.setLikes(5);
		assertTrue(ufDAO.getUpdateLike(2));
	}
	
	@Test
	public void updateApprove()
	{
	assertTrue(ufDAO.updateApprove(2,'y'));
	}
	
	@Test
	public void listAllForumComments()
	{
		assertNotNull(ufDAO.listAllForumComments(2));
	}
	
	@Test
	public void addForumComment()
	{
		UserForumComments ufc=new UserForumComments();
		ufc.setId(2);
		ufc.setDateofcomments("12/3/2017");
		ufc.setForumid(2);
		ufc.setComments("good");
		ufc.setUseremail("b@gmail.com");
		assertTrue(ufDAO.addForumComment(ufc));
	}
	
	@Test
	public void deleteForum()
	{
		assertTrue(ufDAO.deleteForum(2));
	}
	
	

}
