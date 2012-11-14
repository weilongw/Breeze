package model;

import org.mybeans.dao.DAOException;
import org.mybeans.factory.BeanFactory;
import org.mybeans.factory.BeanFactoryException;
import org.mybeans.factory.BeanTable;
import org.mybeans.factory.MatchArg;
import org.mybeans.factory.RollbackException;
import org.mybeans.factory.Transaction;

import databean.Exchange;
import databean.Item;
import databean.Message;
import databean.User;

public class ExchangeDAO {

	private BeanFactory<Exchange> factory;
	
	public ExchangeDAO (String jdbcDriver, String jdbcURL, UserDAO userDAO) throws DAOException {
		try {
			BeanTable<Exchange> exchangeTable = BeanTable.getSQLInstance(Exchange.class, 
													"team17_exchange", 
													jdbcDriver, 
													jdbcURL, 
													userDAO.getFactory());
			if(!exchangeTable.exists()) exchangeTable.create("id");
			factory = exchangeTable.getFactory();
		} catch (BeanFactoryException e) {
			throw new DAOException(e);
		}
	}
	
	public void create(Exchange newExchange) throws DAOException {
		try{
			Transaction.begin();
			Exchange dbExchange = factory.create();
			factory.copyInto(newExchange, dbExchange);
			Transaction.commit();
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public void closeTransaction(Item item) throws DAOException {
		int itemId = item.getId();
		User owner = item.getOwner();
		Exchange newExchange = new Exchange();
		newExchange.setItemId(itemId);
		newExchange.setPoster(owner);
		newExchange.setRespondType(Exchange.NO_ONE_ANSWER);
		newExchange.setStatus(Exchange.CLOSED);	
		create(newExchange);
	}
	
	public void closeTransaction(Item item, User responder, int respondType) throws DAOException {
		int itemId = item.getId();
		User owner = item.getOwner();
		if (respondType == Exchange.ANSWER_POST_WITH_CREDIT) {
			Exchange newExchange = new Exchange();
			newExchange.setItemId(itemId);
			newExchange.setPoster(owner);
			newExchange.setResponder(responder);
			newExchange.setRespondType(Exchange.ANSWER_POST_WITH_CREDIT);
			newExchange.setStatus(Exchange.CLOSED);
			create(newExchange);
		}
		closeItemTransaction(item);
	}
	
	public void closeItemTransaction(Item item) throws DAOException {
		try {
			Exchange[] item_xchgs = factory.match(MatchArg.equals("status", Exchange.PENDING));
			for (Exchange xchg : item_xchgs) {
				Transaction.begin();
				Exchange ex = factory.lookup(xchg.getId());
				ex.setStatus(Exchange.CLOSED);
				Transaction.commit();
			}
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public void openPendingTransaction(Item item, User responder, int respondType) throws DAOException {
		int itemId = item.getId();
		User owner = item.getOwner();
		Exchange newExchange = new Exchange();
		newExchange.setItemId(itemId);
		newExchange.setPoster(owner);
		newExchange.setResponder(responder);
		newExchange.setRespondType(respondType);
		newExchange.setStatus(Exchange.PENDING);
		create(newExchange);
	}

}
