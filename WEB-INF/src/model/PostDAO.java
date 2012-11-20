package model;

import org.mybeans.dao.DAOException;
import org.mybeans.factory.BeanFactory;
import org.mybeans.factory.BeanTable;

import databean.Post;

public class PostDAO {
	private BeanFactory<Post> factory;
	
	public PostDAO (String jdbcDriver, String jdbcURL, UserDAO userDAO, TopicDAO topicDAO) throws DAOException {
		BeanTable<Post> postTable = BeanTable.getSQLInstance(Post.class, "team17_post", 
															jdbcDriver, jdbcURL, 
															userDAO.getFactory(), 
															topicDAO.getFactory());
		if (!postTable.exists()) postTable.create("id");
		postTable.setIdleConnectionCleanup(true);
		factory = postTable.getFactory();
	}
}
