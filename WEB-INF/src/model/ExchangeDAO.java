package model;

import java.util.Arrays;
import java.util.Date;

import org.mybeans.dao.DAOException;
import org.mybeans.factory.BeanFactory;
import org.mybeans.factory.BeanFactoryException;
import org.mybeans.factory.BeanTable;
import org.mybeans.factory.MatchArg;
import org.mybeans.factory.RollbackException;
import org.mybeans.factory.Transaction;

import databean.Exchange;
import databean.Item;
import databean.User;

public class ExchangeDAO {

	private BeanFactory<Exchange> factory;
	
	public ExchangeDAO (String jdbcDriver, String jdbcURL, UserDAO userDAO, ItemDAO itemDAO) throws DAOException {
		try {
			BeanTable<Exchange> exchangeTable = BeanTable.getSQLInstance(Exchange.class, 
													"team17_exchange", 
													jdbcDriver, 
													jdbcURL, 
													userDAO.getFactory(),
													itemDAO.getFactory());
			if(!exchangeTable.exists()) exchangeTable.create("id");
			exchangeTable.setIdleConnectionCleanup(true);
			factory = exchangeTable.getFactory();
		} catch (BeanFactoryException e) {
			throw new DAOException(e);
		}
	}
	
	public int create(Exchange newExchange) throws DAOException {
		try{
			Transaction.begin();
			Exchange dbExchange = factory.create();
			factory.copyInto(newExchange, dbExchange);
			int exchangeId = dbExchange.getId();
			Transaction.commit();
			return exchangeId;
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	public Exchange lookup(int exchangeId) throws DAOException {
		try {
			Exchange ret = factory.lookup(exchangeId);
			return ret;
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	public void createCancelTransaction(Item item) throws DAOException {
		
		User owner = item.getOwner();
		Exchange newExchange = new Exchange();
		newExchange.setItem(item);
		newExchange.setPoster(owner);
		newExchange.setRespondType(Exchange.UNDEFINED);
		newExchange.setStatus(Exchange.NO_ONE_ANSWER);
		newExchange.setEndDate(new Date());
		create(newExchange);
	}
	
	/*public void closedfTransaction(Item item, User responder, int respondType) throws DAOException {
		
		User owner = item.getOwner();
		if (respondType == Exchange.ANSWER_POST_WITH_CREDIT) {
			Exchange newExchange = new Exchange();
			newExchange.setItem(item);
			newExchange.setPoster(owner);
			newExchange.setResponder(responder);
			newExchange.setRespondType(Exchange.ANSWER_POST_WITH_CREDIT);
			newExchange.setStatus(Exchange.CLOSED);
			create(newExchange);
		}
		closeItemTransaction(item);
	}*/
	
	
	/*public void closeTransaction(int exchangeId) throws DAOException {
		try {
			Transaction.begin();
			Exchange xchg = factory.lookup(exchangeId);
			xchg.setStatus(Exchange.CLOSED);
			Transaction.commit();
		} catch(RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}*/
	
	public void closeItemTransaction(Item item) throws DAOException {
		try {
			Exchange[] item_xchgs = factory.match(MatchArg.equals("status", Exchange.PENDING), 
											      MatchArg.equals("item", item));
			for (Exchange xchg : item_xchgs) {
				Transaction.begin();
				Exchange ex = factory.lookup(xchg.getId());
				ex.setStatus(Exchange.CLOSED);
				ex.setEndDate(new Date());
				Transaction.commit();
			}
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public int openPendingTransaction(Item item, User responder, int respondType) throws DAOException {
	
		User owner = item.getOwner();
		Exchange newExchange = new Exchange();
		newExchange.setItem(item);
		newExchange.setPoster(owner);
		newExchange.setResponder(responder);
		newExchange.setRespondType(respondType);
		newExchange.setStatus(Exchange.PENDING);
		return create(newExchange);
	}
	
	public Exchange[] findItemClosedTransactions(Item item) throws DAOException {
		Exchange[] ret = null;
 		try {
			ret = factory.match(MatchArg.equals("status", Exchange.CLOSED),
								MatchArg.equals("item", item));
		} catch(RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
 		return ret;
	}
	
	public Exchange[] findItemPendingTransactions(Item item) throws DAOException {
		Exchange[] ret = null;
 		try {
			ret = factory.match(MatchArg.equals("status", Exchange.PENDING),
								MatchArg.equals("item", item));
		} catch(RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
 		return ret;
	}
	
	public boolean exists(Item item, int respond, User responder) throws DAOException {
		try {
			Exchange[] xchg = factory.match(MatchArg.equals("item", item),
										   MatchArg.equals("respondType", respond),
										   MatchArg.equals("responder", responder));
			if (xchg.length == 0) return false;
		} catch (RollbackException e) {
			throw new DAOException (e);
		}
		return true;
	}
	
	public void createSuccessTransaction(Item item, User responder) throws DAOException {
		Exchange newXchg = new Exchange();
		newXchg.setItem(item);
		newXchg.setPoster(item.getOwner());
		newXchg.setResponder(responder);
		newXchg.setRespondType(Exchange.ANSWER_POST_WITH_CREDIT);
		newXchg.setStatus(Exchange.SUCCESS);
		newXchg.setEndDate(new Date());
		create(newXchg);
	}
	
	public void setSuccessTransaction(int xchgId) throws DAOException {
		try {
			Transaction.begin();
			Exchange xchg = factory.lookup(xchgId);
			xchg.setStatus(Exchange.SUCCESS);
			xchg.setEndDate(new Date());
			Transaction.commit();
		} catch(RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public Item[] getPendingItems(User user) throws DAOException {
		try {
			Exchange[] xchgs = factory.match(MatchArg.equals("responder", user),
											 MatchArg.equals("status", Exchange.PENDING));
			Item[] items = new Item[xchgs.length];
			for (int i = 0; i < xchgs.length; i++) {
				items[i] = xchgs[i].getItem();
			}
			return items;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	public Exchange[] getMyFinished(User user) throws DAOException {
		try {
			Exchange[] xchgs1 = factory.match(MatchArg.equals("poster", user),
											  MatchArg.equals("status", Exchange.SUCCESS));
			Exchange[] xchgs2 = factory.match(MatchArg.equals("responder", user),
					 						  MatchArg.equals("status", Exchange.SUCCESS));
			Exchange[] xchgs = Arrays.copyOf(xchgs1, xchgs1.length + xchgs2.length);
			System.arraycopy(xchgs2, 0, xchgs, xchgs1.length, xchgs2.length);
			return xchgs;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	public Exchange[] getMyWon(User user) throws DAOException {
		try {
			Exchange[] xchgs = factory.match(MatchArg.equals("responder", user),
											 MatchArg.equals("status", Exchange.SUCCESS));
			return xchgs;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	public Exchange[] getMyClosed(User user) throws DAOException {
		try {
			Exchange[] xchgs = factory.match(MatchArg.equals("poster", user),
											 MatchArg.equals("status", Exchange.NO_ONE_ANSWER));
			return xchgs;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	public int findExchangeResult(Item item) throws DAOException{
		try {
			int result = 0;
			if(factory.match(MatchArg.equals("item", item), 
					MatchArg.equals("status", Exchange.NO_ONE_ANSWER)).length == 1)
				result = 1;
			else if(factory.match(MatchArg.equals("item", item), 
					MatchArg.equals("status", Exchange.SUCCESS)).length == 1)
				result = 2;
			return result;
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e);
		}
	}
	
	public Exchange findSuccessExchange(Item item) throws DAOException{
		try {
			Exchange[] xchgs = factory.match(MatchArg.equals("item", item), 
					MatchArg.equals("status", Exchange.SUCCESS));
			if(xchgs.length != 1)
				throw new DAOException("Invalid opration " +
						"in finding success exchange for the item. No or more than " +
						"two successful exchange record(s).");
			Exchange xchg = xchgs[0];
			return xchg;
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e);
		}
	}
}
