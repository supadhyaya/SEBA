

package models;


import java.util.ArrayList;
import antlr.collections.List;

public class SessionHandler {
ArrayList<String> cookies = null;


private static SessionHandler instance = null;

  protected SessionHandler() {

      // Exists only to defeat instantiation.

  cookies = new ArrayList<String>();

  }

  public static SessionHandler getInstance() {

      if(instance == null) {

        instance = new SessionHandler();

      }

      return instance;

  }

   
public void addCookies(String userId) {

if (instance.cookies.contains(userId)) {

instance.cookies.add(userId);
}

}
public void removeCookies(String userId){

instance.cookies.remove(userId);

}
public  boolean isCookiesExists(String userId) {

return instance.cookies.contains(userId);


}

//public User getSessionUser(String userId) {
//
//	   //Check session if exists or not.
//	   boolean sessionExists =  instance.isCookiesExists(userId);
//	   if (!sessionExists) {
//		   
//		   instance.addCookies(userId);
//	       //for adding cookies into the session. 
//	   }
//	   return 
//	 //for removing cookies into the session. 
//	   //sessionHandler.removeCookies(Long.toString(userId));
//    //return userId == null ? null : (User) User.findById(Long.parseLong(userId));
//     
//     
// }

}