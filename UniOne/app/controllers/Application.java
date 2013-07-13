package controllers;

import play.*;
import play.cache.Cache;
import play.mvc.*;
import java.util.*;
import controllers.Secure.Security;
import models.*;
import play.mvc.Scope;
import play.mvc.Scope.Session;



public class Application extends Controller {
	
    public static void index() {
    	System.out.println(session.get("login"));
    	if(session != null && session.contains("login")) {
    		int isActivated = new Integer(session.get("isActivated"));
    		
    		if(isActivated == 1) {
    			System.out.println("user is activated moving to browse");
    			redirect("/browse");
    		} else if(isActivated == 0) {
    			System.out.println("user is not activated moving to activate");
    			redirect("/activate");
    		}
    	}
    	render();
    }
    
    public static void rate() {
    	if (session.current().contains("login"))
    		render();
    }
    
    public static void questions(int id) {
    	System.out.println(id);
    	if (session.current().contains("login"))
    		render();
    }
    
    public static void activate() {
    	System.out.println("Inside Application.activate");
    	if(session != null && session.contains("login")) {
    		int isActivated = new Integer(session.get("isActivated"));
    		
    		if(isActivated == 1) {
    			System.out.println("user is activated moving to browse");
    			redirect("/browse");
    		} else {
    			System.out.println("user is not activated moving to activate");
    			render();
    		}
    	}
    }
    
    public static void browse() {
    	render();
    }
}