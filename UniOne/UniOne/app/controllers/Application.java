package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
        
    	render();
    }
    
    private static void generateJSON(String v) {
        
    	render(v);
    }
    
    
    public static void test(String variable) {
    	
    	
    	
    	renderJSON("{variable:"+variable+"}");
    }  
    
}