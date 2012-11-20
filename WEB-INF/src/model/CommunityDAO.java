package model;

import java.util.Arrays;

import org.mybeans.dao.DAOException;
import org.mybeans.factory.BeanFactory;
import org.mybeans.factory.BeanFactoryException;
import org.mybeans.factory.BeanTable;
import org.mybeans.factory.DuplicateKeyException;
import org.mybeans.factory.MatchArg;
import org.mybeans.factory.RollbackException;
import org.mybeans.factory.Transaction;

import databean.Community;

public class CommunityDAO {

	private BeanFactory<Community> factory;
	
	public CommunityDAO(String jdbcDriver, String jdbcURL, UserDAO userDAO) throws DAOException {
		try {
			BeanTable<Community> communityTable = BeanTable.getSQLInstance(Community.class, 
																		    "team17_community", 
																		    jdbcDriver, 
																		    jdbcURL, 
																		    userDAO.getFactory());
			if (!communityTable.exists()) communityTable.create("name");
			communityTable.setIdleConnectionCleanup(true);
			factory = communityTable.getFactory();
		} catch (BeanFactoryException e) {
			throw new DAOException(e);
		}
	}
	
	protected BeanFactory<Community> getFactory() { return factory; }
	
	public void create(Community community) throws DAOException {
		try {
			Transaction.begin();
			Community dbcommunity = factory.create(community.getName());
			factory.copyInto(community, dbcommunity);
			
			Transaction.commit();
		
		} catch(DuplicateKeyException e) {
			throw new DAOException("Community named " + community.getName() + " already exists.");
		} catch(RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public Community[] getAllCommunities() throws DAOException {
		try {
			Community[] results = factory.match();
			return results;
 		} catch (RollbackException e) { 
 			throw new DAOException(e);
 		}
	}
	
	public Community[] getPopular() throws DAOException {
		Community[] all = getAllCommunities();
		Arrays.sort(all);
		if (all.length <= 10) return all;
		Community[] part = new Community[10];
		for (int i = 0; i < 10; i++)
			part[i] = all[i];
		return part;
	}
	
	public Community[] search(String key) throws DAOException {
		try {
			Community[] results = factory.match(MatchArg.containsIgnoreCase("name", key));
			return results;
		} catch(RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	public Community lookup(String name) throws DAOException {
		try {
			Community comm = factory.lookup(name);
			return comm;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
		
	}
}