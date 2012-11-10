package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutAction extends Action{

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "logout.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
        session.setAttribute("user",null);

		//request.setAttribute("message","You are now logged out");
        return "browse.do";
	}

}
