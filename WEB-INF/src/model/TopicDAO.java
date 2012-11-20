package model;

import org.mybeans.dao.DAOException;
import org.mybeans.factory.BeanFactory;
import org.mybeans.factory.BeanTable;
import org.mybeans.factory.RollbackException;
import org.mybeans.factory.Transaction;

import databean.Topic;

public class TopicDAO {

	private BeanFactory<Topic> factory;
	
	public TopicDAO(String jdbcDriver, String jdbcURL, UserDAO userDAO, CommunityDAO communityDAO) throws DAOException {
		BeanTable<Topic> topicTable = BeanTable.getSQLInstance(Topic.class, "team17_topic", 
															   jdbcDriver, jdbcURL, 
															   userDAO.getFactory(),
															   communityDAO.getFactory());
		if (!topicTable.exists()) topicTable.create("id");
		topicTable.setIdleConnectionCleanup(true);
		factory = topicTable.getFactory();
	}
	
	protected BeanFactory<Topic> getFactory() { return factory; }
	
	public int create (Topic newTopic) throws DAOException {
		try {
			Transaction.begin();
			Topic dbTopic = factory.create();
			factory.copyInto(newTopic, dbTopic);
			int topicId = dbTopic.getId();
			Transaction.commit();
			return topicId;
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}

}
