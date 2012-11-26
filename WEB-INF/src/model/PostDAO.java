package model;

import java.util.Arrays;

import org.mybeans.dao.DAOException;
import org.mybeans.factory.BeanFactory;
import org.mybeans.factory.BeanTable;
import org.mybeans.factory.MatchArg;
import org.mybeans.factory.RollbackException;
import org.mybeans.factory.Transaction;


import databean.Post;
import databean.Topic;


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
	
	public int create(Post newPost) throws DAOException {
		try {
			Transaction.begin();
			Post dbPost = factory.create();
			factory.copyInto(newPost, dbPost);
			int ret = dbPost.getId();
			Transaction.commit();
			return ret;
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if(Transaction.isActive()) Transaction.rollback();
		}
	}
	
	
	
	public Post[] getPostsByTopic(Topic topic) throws DAOException {
		try {
			Post[] list = factory.match(MatchArg.equals("topic", topic));
			Arrays.sort(list);
			return list;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
} 
