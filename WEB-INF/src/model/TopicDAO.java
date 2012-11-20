package model;

import java.util.Arrays;

import org.mybeans.dao.DAOException;
import org.mybeans.factory.BeanFactory;
import org.mybeans.factory.BeanTable;
import org.mybeans.factory.MatchArg;
import org.mybeans.factory.RollbackException;
import org.mybeans.factory.Transaction;

import databean.Community;
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
	
	public Topic[] getAllTopics() throws DAOException {
		try {
			Topic[] all = factory.match();
			return all;
		} catch(RollbackException e) {
			throw new DAOException(e);
		} 
	}
	
	public Topic[] getPopular() throws DAOException {
		Topic[] all = getAllTopics();
		Arrays.sort(all);
		if (all.length <= 10) return all;
		Topic[] part = new Topic[10];
		for (int i = 0; i < 10; i++)
			part[i] = all[i];
		return part;
	}
	
	public Topic[] search(String key) throws DAOException {
		try {
			Topic[] results = factory.match(MatchArg.containsIgnoreCase("title", key));
			return results;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	public Topic[] getTopicsByCommunity(Community key) throws DAOException {
		try {
			Topic[] results = factory.match(MatchArg.equals("ownerGroup", key));
			return results;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
}