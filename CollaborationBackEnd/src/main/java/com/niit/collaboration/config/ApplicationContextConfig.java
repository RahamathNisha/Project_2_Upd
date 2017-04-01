package com.niit.collaboration.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.collaboration.dao.BlogDAO;
import com.niit.collaboration.dao.ForumCategoryDAO;
import com.niit.collaboration.dao.UserForumDAO;
import com.niit.collaboration.dao.FriendDAO;
import com.niit.collaboration.dao.JobDAO;
import com.niit.collaboration.dao.UserDAO;
import com.niit.collaboration.dao.UserForumDAO;
import com.niit.collaboration.dao.impl.BlogDAOImpl;
import com.niit.collaboration.dao.impl.UserForumDAO_Impl;
import com.niit.collaboration.dao.impl.FriendDAOImpl;
import com.niit.collaboration.dao.impl.JobDAOImpl;
import com.niit.collaboration.dao.impl.UserDAOImpl;
import com.niit.collaboration.dao.impl.UserForumDAO_Impl;
import com.niit.collaboration.model.Blog;
import com.niit.collaboration.model.Forum;
import com.niit.collaboration.model.ForumCategory;
//import com.niit.collaboration.model.Chat;
//import com.niit.collaboration.model.ChatForum;
//import com.niit.collaboration.model.ChatForumComment;
//import com.niit.collaboration.model.Event;
import com.niit.collaboration.model.Friend;
import com.niit.collaboration.model.Job;
import com.niit.collaboration.model.JobApplication;
//import com.niit.collaboration.model.JobApplication;
import com.niit.collaboration.model.UserDetails;
import com.niit.collaboration.model.UserForum;
import com.niit.collaboration.model.UserForumComments;
import com.niit.collaboration.dao.impl.ForumCategoryDAO_Impl;

@Configuration
@ComponentScan("com.niit.collaboration")
@EnableTransactionManagement
public class ApplicationContextConfig {
	
	private static final Logger logger = 
			LoggerFactory.getLogger(ApplicationContextConfig.class);
	
	@Bean(name = "dataSource")
	public DataSource getOracleDataSource() 
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		dataSource.setUsername("nisha"); //Schema name
		dataSource.setPassword("12345");
		return dataSource;
	}
	
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) 
	{
		LocalSessionFactoryBuilder lsf=new LocalSessionFactoryBuilder(getOracleDataSource());
		Properties hibernateProperties=new Properties();
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		lsf.addProperties(hibernateProperties);
		return lsf.addAnnotatedClasses(Blog.class)
				//.addAnnotatedClass(Forum.class)
				.addAnnotatedClass(ForumCategory.class)
				.addAnnotatedClass(Job.class)
				.addAnnotatedClass(Friend.class)
				.addAnnotatedClass(UserDetails.class)
				.addAnnotatedClass(JobApplication.class)
				.addAnnotatedClass(UserDetails.class)
				.addAnnotatedClass(UserForum.class)
				.addAnnotatedClass(UserForumComments.class)
		   .buildSessionFactory();

	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

		return transactionManager;
	}

	@Autowired
	@Bean(name = "blogDAO")
	public BlogDAO getBlogDAO(SessionFactory sessionFactory) {
		logger.debug("accessing BlogDAO.");
		return new BlogDAOImpl(sessionFactory);
	}

	@Autowired
	@Bean(name = "forumDAO")
	public UserForumDAO getForumDAO(SessionFactory sessionFactory) {
		return new UserForumDAO_Impl(sessionFactory);
	}

	
	@Autowired
	@Bean(name = "userDAO")
	public UserDAO getUserDetailsDAO(SessionFactory sessionFactory) {
		logger.debug("accessing userDetailsDAO.");
		return new UserDAOImpl(sessionFactory);
	}

	@Autowired
	@Bean(name = "friendDAO")
	public FriendDAO getFriendDAO(SessionFactory sessionFactory) {
		logger.debug("accessing FriendDAO.");
		return new FriendDAOImpl(sessionFactory);
	}
	
	
	@Autowired	
    @Bean(name="jobDAO")
	public JobDAO getJobDAO(SessionFactory sessionFactory)
	{
		logger.debug("accessing jobDAO");
		return new JobDAOImpl(sessionFactory);
	}

	@Autowired
	@Bean(name = "fcDAO")
	public ForumCategoryDAO getForumCategoryDAO(SessionFactory sessionFactory) {
		logger.debug("accessing forumDAO");
		return new ForumCategoryDAO_Impl(sessionFactory);
	}
}