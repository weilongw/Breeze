package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.dao.DAOException;

import databean.Community;
import databean.Relation;
import databean.Topic;
import databean.User;

import model.CommunityDAO;
import model.Model;
import model.TopicDAO;
import model.RelationDAO;

public class ShowMyCommunityAction extends Action{

	CommunityDAO communityDAO;
	TopicDAO topicDAO;
	RelationDAO relationDAO;
	
	public ShowMyCommunityAction(Model model){
		communityDAO = model.getCommunityDAO();
		topicDAO = model.getTopicDAO();
		relationDAO = model.getRelationDAO();
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "showMyCommunity.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<String> errors = prepareErrors(request);
        User curUser = (User) request.getSession(false).getAttribute("user");
        if (curUser == null) {
        	errors.add("You are not logged in");
        	return "browse.do";
        }
        
        Relation[] myRelation;
        Community[] myComm;
        Topic[] myTopic;
		try {
			myRelation = relationDAO.getMyCommunity(curUser);
			if(myRelation.length != 0){
	        	List<Community> myCommList = new ArrayList<Community>();
	        	for(Relation r:myRelation){
	        		myCommList.add(r.getCommunity());
//	        		System.out.println(r.getCommunity().getName());
	        	}
	        	Collections.sort(myCommList, Community.REVERSE_TIMEORDER);   
/*	        	for(Community c:myCommList)
	        		System.out.println(c.getName());*/
	        	myComm = myCommList.toArray(new Community[myCommList.size()]);        	
				request.setAttribute("myComm",myComm);

	        }
			myTopic = topicDAO.getMyTopic(curUser);
			
			request.setAttribute("myTopic",myTopic);
			
			return "my_community.jsp";

		} catch (DAOException e) {
			// TODO Auto-generated catch block
			errors.add(e.getMessage());
			return "browse.do";
		}
	}

}
