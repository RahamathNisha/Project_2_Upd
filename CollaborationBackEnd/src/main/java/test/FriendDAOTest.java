package test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.niit.collaboration.dao.FriendDAO;
import com.niit.collaboration.model.Friend;
import com.niit.collaboration.model.UserDetails;

public class FriendDAOTest {

	
	
	static FriendDAO friendDAO;
	
	@BeforeClass
	public static void init()
	{
		AnnotationConfigWebApplicationContext ctx=new AnnotationConfigWebApplicationContext();
		ctx.scan("com.niit.collaboration");
		ctx.refresh();
		friendDAO=(FriendDAO)ctx.getBean("friendDAO");
	}

	
	
	@Test
	public void saveFriend()
	{
		Friend frnd=new Friend();
		frnd.setUserID("2");
		frnd.setFriendID("3");
		frnd.setStatus("valid");
		frnd.setIsOnline('o');
		assertTrue(friendDAO.saveFriend(frnd));
		
	}
	
	@Test
	public void updateFriend()
	{
	Friend frnd=new Friend();
	frnd.setUserID("5");
	frnd.setFriendID("6");
	frnd.setStatus("Invalid");
	frnd.setIsOnline('f');
	assertTrue(friendDAO.updateFriend(frnd));
	}

	@Test
	public void getAllFriends()
	{
		assertNotNull(friendDAO.getAllFriends("111"));
	}
	
	@Test
	public void getFriendByID(){
	  Friend frnd=friendDAO.getFriendById("2", "3");
	  assertNotNull(frnd);
	}
	
}
