package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import databean.User;

import model.Model;

public class Controller extends HttpServlet {

	public void init() throws ServletException {
        Model model = new Model(getServletConfig());
        
        Action.add(new RegisterAction(model));
        Action.add(new LoginAction(model));
        Action.add(new LogoutAction());
        Action.add(new ShowProfileAction(model));
        Action.add(new UpdateProfileAction(model));
        Action.add(new PostItemAction(model));
        Action.add(new ShowMyItemsAction(model));
        Action.add(new HomeAction(model));
        Action.add(new BrowseAction(model));
        Action.add(new SearchAction(model));
        Action.add(new UploadImageAction(model));
        Action.add(new ShowItemPageAction(model));
        Action.add(new BuyItemAction(model));
        Action.add(new ComposeMessageAction(model));
        Action.add(new ShowMessageAction(model));
        Action.add(new RedirectSendAction(model));
        Action.add(new ShowMovieAction(model));
        Action.add(new CloseItemAction(model));
        Action.add(new CompleteExchangeAction(model));
        Action.add(new UpdatePhotoAction(model));
        Action.add(new CreateCommunityAction(model));
        Action.add(new BrowseCommunityAction(model));
        Action.add(new CommunitySearchAction(model));
        Action.add(new ViewCommunityAction(model));
        Action.add(new JoinCommunityAction(model));
        Action.add(new NewTopicAction(model));
        Action.add(new ViewTopicAction(model));
        Action.add(new NewPostAction(model));
        Action.add(new SearchTopicAction(model));
        Action.add(new UnjoinCommunityAction(model));
        Action.add(new PaginateMsgAction(model));
        Action.add(new CheckAction(model));
        Action.add(new AjaxReadAction(model));
        Action.add(new ShowMyCommunityAction(model));
        Action.add(new DownloadAction());
        Action.add(new UploadAction());
        Action.add(new UploadItemAction());
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nextPage = performTheAction(request);
        sendToNextPage(nextPage,request,response);
    }
    
    private String performTheAction(HttpServletRequest request) {
        HttpSession session     = request.getSession(true);
        String      servletPath = request.getServletPath();
        User        user = (User) session.getAttribute("user");
        String      action = getActionName(servletPath);

        System.out.println("servletPath="+servletPath+" requestURI="+request.getRequestURI()+"  user="+user);

        
        if (action.equals("start")) {
        	// If the user hasn't logged in, direct him to the login page
			return "home.jsp";
        }
        
		return Action.perform(action,request);
    }
    
    /*
     * If nextPage is null, send back 404
     * If nextPage starts with a '/', redirect to this page.
     *    In this case, the page must be the whole servlet path including the webapp name
     * Otherwise dispatch to the page (the view)
     *    This is the common case
     * Note: If nextPage equals "image", we will dispatch to /image.  In the web.xml file, "/image"
     *    is mapped to the ImageServlet will which return the image bytes for display.
     */
    private void sendToNextPage(String nextPage, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	//System.out.println("nextPage="+nextPage);
    	
    	if (nextPage == null) {
    		response.sendError(HttpServletResponse.SC_NOT_FOUND,request.getServletPath());
    		return;
    	}
    	
    	if (nextPage.charAt(0) == '/') {
			String host  = request.getServerName();
			String port  = ":"+String.valueOf(request.getServerPort());
			if (port.equals(":80")) port = "";
			response.sendRedirect("http://"+host+port+nextPage);
			return;
    	}
    	
    	System.out.println(nextPage);
    	
    	RequestDispatcher d = request.getRequestDispatcher("/view/"+nextPage);
    	d.forward(request,response);
    }

	/*
	 * Returns the path component after the last slash removing any "extension"
	 * if present.
	 */
    private String getActionName(String path) {
    	// We're guaranteed that the path will start with a slash
        int slash = path.lastIndexOf('/');
        return path.substring(slash+1);
    }
}
