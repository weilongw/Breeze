package model;

import java.util.Arrays;

import org.mybeans.dao.DAOException;
import org.mybeans.factory.BeanFactory;
import org.mybeans.factory.BeanTable;
import org.mybeans.factory.MatchArg;
import org.mybeans.factory.RollbackException;
import org.mybeans.factory.Transaction;

import databean.FakePost;
import databean.Post;
import databean.Topic;


public class PostDAO {
	private BeanFactory<FakePost> factory;
	
	public PostDAO (String jdbcDriver, String jdbcURL, UserDAO userDAO, TopicDAO topicDAO) throws DAOException {
		BeanTable<FakePost> postTable = BeanTable.getSQLInstance(FakePost.class, "team17_post", 
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
			FakePost dbPost = factory.create();
			dbPost.setContent(newPost.getContent().getBytes());
			dbPost.setPostDate(newPost.getPostDate());
			dbPost.setPoster(newPost.getPoster());
			dbPost.setTopic(newPost.getTopic());
			
			//factory.copyInto(newPost, dbPost);
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
			
			FakePost[] list = factory.match(MatchArg.equals("topic", topic));
			Post[] ret = new Post[list.length];
			for (int i = 0; i < list.length; i++)
				ret[i] = Post.toPost(list[i]);
			Arrays.sort(ret);
			return ret;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
} 
