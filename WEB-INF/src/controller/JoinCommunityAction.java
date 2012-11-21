package controller;

import javax.servlet.http.HttpServletRequest;

import model.Model;
import model.RelationDAO;

public class JoinCommunityAction extends Action{
	
	RelationDAO relationDAO;

	public JoinCommunityAction(Model model){
		relationDAO = model.getRelationDAO();
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
