package model;

import org.mybeans.dao.DAOException;
import org.mybeans.factory.BeanFactory;
import org.mybeans.factory.BeanFactoryException;
import org.mybeans.factory.BeanTable;
import org.mybeans.factory.DuplicateKeyException;
import org.mybeans.factory.RollbackException;
import org.mybeans.factory.Transaction;


import databean.User;

public class UserDAO {
	
	private BeanFactory<User> factory;
	
	public UserDAO(String jdbcDriver, String jdbcURL) throws DAOException{
		try{
			// create table "user"
			BeanTable<User> userTable = BeanTable.getSQLInstance(
					User.class, "team17_user", jdbcDriver, jdbcURL);
			// set primary key "email"
			if(!userTable.exists()) userTable.create("userName");
			
			userTable.setIdleConnectionCleanup(true);
			
			factory = userTable.getFactory();
			
		}catch(BeanFactoryException e){
			throw new DAOException(e);
		}
	}

	public void create(User user) throws DAOException {
        try {
        	Transaction.begin();
			User dbUser = factory.create(user.getUserName());
			factory.copyInto(user,dbUser);
			Transaction.commit();
		} catch (DuplicateKeyException e) {
			throw new DAOException("Username "+user.getUserName()+" already exists");
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public void update(User user) throws DAOException{
    	try {
			Transaction.begin();
			User dbUser = factory.lookup(user.getUserName());
			dbUser.setEmail(user.getEmail());
	        dbUser.setAddress(user.getAddress());
	        dbUser.setSalt(user.getSalt());
	        dbUser.setHashedPassword(user.getHashedPassword());
			Transaction.commit();
    	} catch (RollbackException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}		
	}
	
	public User lookup(String userName) throws DAOException {
		try {
			return factory.lookup(userName);
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	protected BeanFactory<User> getFactory() { return factory; }
	

	
	//what's the use of this?
	public void setPassword(String userName, String password) throws DAOException {
        try {
        	Transaction.begin();
			User dbUser = factory.lookup(userName);
			
			if (dbUser == null) {
				throw new DAOException("User "+userName+" no longer exists");
			}
			
			dbUser.setPassword(password);
			Transaction.commit();
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public void setCredit(int credit, String userName) throws RollbackException{
		//try {
		//	Transaction.begin();
			User user = factory.lookup(userName);
			if (user == null) {
				throw new RollbackException("user not present");
			}
			user.setCredit(credit);
			//Transaction.commit();
		//} catch (RollbackException e) {
			// TODO Auto-generated catch block
		//	throw new DAOException(e);
		//} finally {
		//	if (Transaction.isActive()) Transaction.rollback();
		//}
	}
	
	public void transferCredit(int credit, User from, User to) throws RollbackException{
		//try{
			User usr1 = factory.lookup(from.getUserName());
			User usr2 = factory.lookup(to.getUserName());
			if (usr1.getCredit() < credit) throw new RollbackException("Not enough credit");
			setCredit(usr1.getCredit() - credit, usr1.getUserName());
			setCredit(usr2.getCredit() + credit, usr2.getUserName());
		//} catch (RollbackException e) {
		//	throw new DAOException(e);
		//} finally {
		//	if (Transaction.isActive()) Transaction.rollback();
		//}
	
	}
	
	public void setPhoto(User user, String photo) throws DAOException {
		try {
			Transaction.begin();
			User usr = factory.lookup(user.getUserName());
			usr.setUserPhoto(photo);
			Transaction.commit();
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public void updateNewMsgCount(String userName, int x) throws RollbackException {		
			User user = factory.lookup(userName);
			user.setNewMsgCount(user.getNewMsgCount() + x);
		
	}
	
}
