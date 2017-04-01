package test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.niit.collaboration.dao.UserDAO;
import com.niit.collaboration.model.UserDetails;

public class UserDAOTest {

	@Autowired
	static AnnotationConfigWebApplicationContext ctx;
	
	@Autowired
	static UserDAO userDao;
	
	@BeforeClass
	public static void init(){
		ctx = new AnnotationConfigWebApplicationContext();
		ctx.scan("com.niit.collaboration");
		ctx.refresh();
		
		userDao = (UserDAO)ctx.getBean("userDAO");
	}
	
	@Test
	public void insertUser(){
		UserDetails user = new UserDetails();
		user.setId("5673");
		user.setDatecreated(new Date());
		user.setAddress("rasd");
		user.setName("arsd");
		user.setEmail("assfsdsdfss");
		user.setEnabled("true");
		user.setGender("male");
		user.setIsOnline('n');
		assertTrue(userDao.saveUser(user));
	}
	
	@Test
	public void updUser(){
		UserDetails user = new UserDetails();
		user.setId("111");
		user.setDatecreated(new Date());
		user.setAddress("wasd");
		user.setName("awsd");
		user.setEmail("aseedsdfss");
		user.setEnabled("true");
		user.setGender("male");
		user.setIsOnline('n');
		assertTrue(userDao.updateUser(user));
	}
	
	@Test
	public void delUser(){		
		assertTrue(userDao.deleteUserById("5673"));
	}
	
	@Test
	public void getUserByID(){
		UserDetails user = userDao.getUserById("111");
		assertNotNull(user);
	}
	
	@Test
	public void getAllUsers(){
		assertNotNull(userDao.getAllUsers());
	}
}
