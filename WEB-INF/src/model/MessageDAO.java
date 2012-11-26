package model;

import java.util.Arrays;
import java.util.Date;

import org.mybeans.dao.DAOException;
import org.mybeans.factory.BeanFactory;
import org.mybeans.factory.BeanTable;
import org.mybeans.factory.MatchArg;
import org.mybeans.factory.RollbackException;
import org.mybeans.factory.Transaction;

import databean.Message;
import databean.User;

public class MessageDAO {

	private BeanFactory<Message> factory;

	public MessageDAO(String jdbcDriver, String jdbcURL,
			UserDAO userDAO) throws DAOException{
		BeanTable<Message> noteTable = BeanTable.getSQLInstance(
				Message.class, "team17_message", jdbcDriver, jdbcURL, 
				userDAO.getFactory());
		if(!noteTable.exists()) noteTable.create("id");
		noteTable.setIdleConnectionCleanup(true);		
		factory = noteTable.getFactory();
	}
	
	public void create(Message newMessage) throws RollbackException {
		//try {
		//	Transaction.begin();
			Message dbMessage = factory.create();
			factory.copyInto(newMessage,dbMessage);
		//	Transaction.commit();
		//} catch (RollbackException e) {
		//	throw new DAOException(e);
		//} finally {
		//	if (Transaction.isActive()) Transaction.rollback();
		//}
	}
	
	protected BeanFactory<Message> getFactory() { return factory; }
	
	
	public Message[] getAllMessages() throws DAOException {
		try {
			Message[] list = factory.match();
			Arrays.sort(list);
			return list;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	public Message[] getNMessageBySender(User user) throws DAOException {
		try {
			Message[] list = factory.match(MatchArg.equals("sender", user));
			Arrays.sort(list);
			return list;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	public Message[] getNMessageByReceiver(User user) throws DAOException {
		try {
			Message[] list = factory.match(MatchArg.equals("receiver", user));
			Arrays.sort(list);
			return list;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	public void send(User sender, User receiver, String title, String content) throws RollbackException {
		Message msg = new Message();
		msg.setSender(sender);
		msg.setReceiver(receiver);
		msg.setSentDate(new Date());
		msg.setTitle(title);
		msg.setContent(content);
		create(msg);
	}
	
	public Message lookup(int messageID) throws DAOException {
		try {
			Message msg = factory.lookup(messageID);
			return msg;
		} catch (RollbackException e){
			throw new DAOException(e);
		}
	}
	
	public void markRead(int messageID) throws RollbackException {	
		Message msg = factory.lookup(messageID);
		msg.setHasRead(1);
			
	}
	
}
