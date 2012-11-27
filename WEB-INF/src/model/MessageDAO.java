package model;

import java.util.Arrays;
import java.util.Date;

import org.mybeans.dao.DAOException;
import org.mybeans.factory.BeanFactory;
import org.mybeans.factory.BeanTable;
import org.mybeans.factory.MatchArg;
import org.mybeans.factory.RollbackException;
import org.mybeans.factory.Transaction;

import databean.FakeMessage;
import databean.Message;
import databean.User;

public class MessageDAO {

	private BeanFactory<FakeMessage> factory;

	public MessageDAO(String jdbcDriver, String jdbcURL,
			UserDAO userDAO) throws DAOException{
		BeanTable<FakeMessage> messageTable = BeanTable.getSQLInstance(
				FakeMessage.class, "team17_message", jdbcDriver, jdbcURL, 
				userDAO.getFactory());
		if(!messageTable.exists()) messageTable.create("id");
		messageTable.setIdleConnectionCleanup(true);		
		factory = messageTable.getFactory();
	}
	
	public void create(FakeMessage newMessage) throws RollbackException {
		
		FakeMessage dbMessage = factory.create();
		factory.copyInto(newMessage,dbMessage);
		
	}
	
	protected BeanFactory<FakeMessage> getFactory() { return factory; }
	
	
	public Message[] getAllMessages() throws DAOException {
		try {
			FakeMessage[] list = factory.match();
			Message[] ret = new Message[list.length];
			for (int i = 0; i < list.length; i++) 
				ret[i] = Message.toMessage(list[i]);
			Arrays.sort(ret);
			return ret;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	public Message[] getNMessageBySender(User user) throws DAOException {
		try {
			FakeMessage[] list = factory.match(MatchArg.equals("sender", user));
			Message[] ret = new Message[list.length];
			for (int i = 0; i < list.length; i++)
				ret[i] = Message.toMessage(list[i]);
			Arrays.sort(ret);
			return ret;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	public Message[] getNMessageByReceiver(User user) throws DAOException {
		try {
			FakeMessage[] list = factory.match(MatchArg.equals("receiver", user));
			Message[] ret = new Message[list.length];
			for (int i = 0; i < list.length; i++)
				ret[i] = Message.toMessage(list[i]);
			Arrays.sort(ret);
			return ret;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	public void send(User sender, User receiver, String title, String content) throws RollbackException {
		FakeMessage msg = new FakeMessage();
		msg.setSender(sender);
		msg.setReceiver(receiver);
		msg.setSentDate(new Date());
		msg.setTitle(title);
		msg.setContent(content.getBytes());
		create(msg);
	}
	
	public Message lookup(int messageID) throws DAOException {
		try {
			FakeMessage msg = factory.lookup(messageID);
			return Message.toMessage(msg);
		} catch (RollbackException e){
			throw new DAOException(e);
		}
	}
	
	public void markRead(int messageID) throws RollbackException {	
		FakeMessage msg = factory.lookup(messageID);
		msg.setHasRead(1);
			
	}
	
	public void sendDirectly(User sender, User receiver, String title, String content) throws DAOException{
		try {
        	Transaction.begin();
			FakeMessage msg = new FakeMessage();
			msg.setSender(sender);
			msg.setReceiver(receiver);
			msg.setSentDate(new Date());
			msg.setTitle(title);
			msg.setContent(content.getBytes());
			create(msg);
			Transaction.commit();
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
}
