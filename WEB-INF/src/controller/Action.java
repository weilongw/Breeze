package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public abstract class Action {
	
	public static final int PAGE = 2;
	public static final int PAGE_COUNT = 6;
	
	public abstract String getName();
	
    public abstract String perform(HttpServletRequest request);
    
    public List<String> prepareErrors(HttpServletRequest request) {
    	List<String> errors = null;
    	errors = (List<String>)request.getAttribute("errors");
    	if (errors == null) 
    		errors = new ArrayList<String>();
    	request.setAttribute("errors", errors);
    	return errors;
    }

    private static Map<String,Action> hash = new HashMap<String,Action>();

    public static void add(Action a) {
    	synchronized (hash) {
    		hash.put(a.getName(),a);
    	}
    }

    public static String perform(String name,HttpServletRequest request) {
        Action a;
        synchronized (hash) {
        	a = hash.get(name);
        }
        
        if (a == null) return null;
        return a.perform(request);
    }
}
