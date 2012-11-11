package model;

import java.util.Arrays;

import org.mybeans.dao.DAOException;
import org.mybeans.factory.BeanFactory;
import org.mybeans.factory.BeanFactoryException;
import org.mybeans.factory.BeanTable;
import org.mybeans.factory.MatchArg;
import org.mybeans.factory.RollbackException;
import org.mybeans.factory.Transaction;

import databean.Item;
import databean.User;

public class ItemDAO {

	private BeanFactory<Item> factory;

	public ItemDAO(String jdbcDriver, String jdbcURL, UserDAO userDAO) throws DAOException{
		try{
		BeanTable<Item> itemTable = BeanTable.getSQLInstance(
				Item.class, "team17_Item", jdbcDriver, jdbcURL,
				userDAO.getFactory());
		if(!itemTable.exists()) itemTable.create("id");
		itemTable.setIdleConnectionCleanup(true);
		factory = itemTable.getFactory();
		} catch(BeanFactoryException e){
			throw new DAOException(e);
		}
	}
	
	public int create(Item newItem) throws DAOException {
		try {
			Transaction.begin();
			Item dbItem = factory.create();
			factory.copyInto(newItem,dbItem);
			Transaction.commit();
			return dbItem.getId();
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public Item[] getAllItems() throws DAOException {
		try {
			Item[] list = factory.match();
			Arrays.sort(list);
			return list;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	public Item[] getItemsByType(int type) throws DAOException {
		try {
			Item[] list = factory.match(MatchArg.equals("type", type));
			Arrays.sort(list);
			return list;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	public Item[] getItemsByCategory(String category) throws DAOException{
		Item[] list;
		try {
			list = factory.match(MatchArg.equals("category", category));
			Arrays.sort(list);
			return list;
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e);
		}
	}
	
	public Item getItemById(int id) throws DAOException{
		Item item;
		try {
			item = factory.lookup(id);
			return item;
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e);
		}		
	}
	
	public Item[] getMyPostedItems(User user) throws DAOException{
		Item[] list;
		try {
			list = factory.match(MatchArg.equals("owner", user), MatchArg.equals("type", 1));
			Arrays.sort(list);
			return list;
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e);
		}
	}
	public Item[] getMyRequestedItems(User user) throws DAOException{
		Item[] list;
		try {
			list = factory.match(MatchArg.equals("owner", user), MatchArg.equals("type", 2));
			Arrays.sort(list);
			return list;
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e);
		}
	}
	
	public void setImgName(int itemId, String imgName) throws DAOException {
		try {
			Transaction.begin();
			Item item = factory.lookup(itemId);
			if (item == null) {
				throw new DAOException("item not present");
			}
			item.setImgName(imgName);
			Transaction.commit();
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public void closeItem(int itemId) throws DAOException{
		try {
			Transaction.begin();
			Item item = factory.lookup(itemId);
			if (item == null) {
				throw new DAOException("item not present");
			}
			item.setStatus(1);
			Transaction.commit();
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
		
	}
	
	public Item[] getActiveItem() throws DAOException{
		Item[] list;
		try {
			list = factory.match(MatchArg.equals("status", 0));
			Arrays.sort(list);
			return list;
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e);
		}
		
		
		
	}
}
