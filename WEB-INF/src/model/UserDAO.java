package model;

import org.mybeans.dao.DAOException;
import org.mybeans.factory.BeanFactory;
import org.mybeans.factory.BeanFactoryException;
import org.mybeans.factory.BeanTable;
import org.mybeans.factory.DuplicateKeyException;
import org.mybeans.factory.RollbackException;
import org.mybeans.factory.Transaction;

import databean.Item;
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
	
	/*public User[] getUsers() throws DAOException {
		try {
			User[] users = factory.match();
			Arrays.sort(users);  // We want them sorted by last and first names (as per User.compareTo());
			return users;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}*/
	
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
	
	public void setCredit(int credit, String userName) throws DAOException{
		try {
			Transaction.begin();
			User user = factory.lookup(userName);
			if (user == null) {
				throw new DAOException("user not present");
			}
			user.setCredit(credit);
			Transaction.commit();
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
}
