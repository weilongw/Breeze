package model;

import org.mybeans.dao.DAOException;
import org.mybeans.factory.BeanFactory;
import org.mybeans.factory.BeanTable;
import org.mybeans.factory.MatchArg;
import org.mybeans.factory.RollbackException;
import org.mybeans.factory.Transaction;

import databean.Community;
import databean.Relation;
import databean.User;

public class RelationDAO {

	private BeanFactory<Relation> factory;
	
	public RelationDAO (String jdbcDriver, String jdbcURL, UserDAO userDAO, CommunityDAO communityDAO) {
		BeanTable<Relation> relationTable = BeanTable.getSQLInstance(Relation.class, "team17_relation",
																	jdbcDriver, jdbcURL, 
																	userDAO.getFactory(),
																	communityDAO.getFactory());
		if (!relationTable.exists()) relationTable.create("id");
		relationTable.setIdleConnectionCleanup(true);
		factory = relationTable.getFactory();
	}
	
	public void create(User user, Community community) throws DAOException {
		try {
			Transaction.begin();
			Relation dbrelation = factory.create();
			dbrelation.setUser(user);
			dbrelation.setCommunity(community);
			Transaction.commit();
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public void destroy(User user, Community community){
		
		try {
			Transaction.begin();
			
			dbrelation.setUser(user);
			dbrelation.setCommunity(community);
			Transaction.commit();
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Transaction.begin();
 			Relation[] result = factory.match(MatchArg.equals("user", user), MatchArg.equals("community", community));

    		if (result == null) {
				throw new DAOException("You are not in the community: "+ community.getName());
    		}

    		if (result.length != 1) {
				throw new DAOException("Error occured in join/unjoin");
    		}

			factory.delete(result[0].getId());
			Transaction.commit();
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
		
		
		
		
	}
	
	public boolean exist(User user, Community community) throws DAOException {
		try {
			Relation[] result = factory.match(MatchArg.equals("user", user), MatchArg.equals("community", community));
			if (result.length == 0 ) return false;
			return true;
		} catch (RollbackException e) {
			throw new DAOException(e);
		} 
	}

}
