package controllers;

import models.SessionHandler;
import models.User;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.mail.HtmlEmail;
import org.bouncycastle.util.encoders.Hex;

import play.mvc.Controller;

/* New Imports are below*/
public class UserController extends Controller {
	User user;
	
	public static void index() {
		render();
	}
	
	// action for user logout & nullifying the session
	public static void logoutUser() {
		// destroy session
		try {
			if (!session.current().isEmpty()) {
				session.current().clear();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// action for user registration
	public static void registerUser(String firstName, String lastName, String university, String email, String password) {
		User user = User.findByEmail(email);
		String activationKey;
		
		// if user doesn't exist in the system already
		if(user == null) {
			try {
				// generating the user activation key
				activationKey = Base64.encodeBase64URLSafeString(Hex.decode(DigestUtils.sha256Hex(password+firstName)));
				
				// registering the user in the database
				user = new User(firstName, lastName, university, email, password, activationKey, 0).save();
				
				// sending email with the activation key to the user
				sendEmail(user);
				
			} catch (Exception e) {
				e.printStackTrace();
				renderJSON("{\"error\": true, \"message\": \"Registration failed! Something went wrong.\"}");
			}

			renderJSON("{\"error\": false, \"message\":null}");
		} else{
			renderJSON("{\"error\": true, \"message\": \"User already exists in the system.\"}");
		}

	}

	// user login action
	public static void loginUser(String username, String password) throws Exception {
		User user = User.findByEmail(username);
		
		if (user!=null) {
			
			if (password.equals(user.getPassword())) {
				
				session.current().put("login", username);
				session.current().put("isActivated", user.getActivated());
				renderJSON("{\"error\": false, \"message\": null}");
				
			} else {
				
				renderJSON("{\"error\": true, \"message\": \"Invalid username: "+username+" or password: "+password+"!\"}");
			}
		} else {
			
			renderJSON("{\"error\": true, \"message\": \"There is no such user exists! Server Error!\"}");
		}
		

	}

	// action for activating the user
	public static void activateUser(String userName, String activationKey) {
		System.out.println("Inside Activate User");
		System.out.println(userName);
		System.out.println(activationKey);
		User user = User.findByEmail(userName);
		
		if(user != null) {
			if(user.getActivationKey().equals(activationKey)) {
				System.out.println("Inside Condition");
				user.setActivated(1);
				user.save();
				
				session.current().put("isActivated", user.getActivated());
				
				// will be removed once handled by angularjs function
				redirect("/browse");
				
				renderJSON("{\"error\": false, \"message\": User successfully activated}");
				
			} else {
				System.out.println(activationKey);
				System.out.println(user.getActivationKey());
				renderJSON("{\"error\": true, \"message\": \"Invalid activation key\"}");
			}
		}
	}
	
	// send email to the user
	public static void sendEmail(User user) {
		HtmlEmail htmlEmail = new HtmlEmail();
		
		try {
			htmlEmail.addTo(user.getEmail(), user.getFirstName());
			htmlEmail.setFrom("sender@unione.com");
			htmlEmail.setHostName("smtp.gmail.com");
			htmlEmail.setSmtpPort(587);
			htmlEmail.setAuthentication("unionemailer@gmail.com", "ThisisUniOne");
			htmlEmail.getMailSession().getProperties().put("mail.smtps.auth", "true");
			htmlEmail.getMailSession().getProperties().put("mail.debug", "true");
			htmlEmail.getMailSession().getProperties().put("mail.smtps.port", "587");
			htmlEmail.getMailSession().getProperties().put("mail.smtps.socketFactory.port", "587");
			htmlEmail.getMailSession().getProperties().put("mail.smtps.socketFactory.class",   "javax.net.ssl.SSLSocketFactory");
			htmlEmail.getMailSession().getProperties().put("mail.smtps.socketFactory.fallback", "false");
			htmlEmail.getMailSession().getProperties().put("mail.smtp.starttls.enable", "true");
			htmlEmail.setSubject("User Activation");
			htmlEmail.setHtmlMsg("<HTML>"+"Dear "+user.getLastName()+", <br/><br/>	In order to get registered and access the complete " +
					"application you must activate your account. " +"Your activation link is: <url>http://localhost:9000/activate?username="+user.getEmail()+"&activationKey="+user.getActivationKey()+"&activation=Activate</url><br/><br/> Kind Regards,<br/>Team UniOne</HTML>");
			htmlEmail.send();
		} catch(Exception e) {
			e.printStackTrace();
			renderJSON("{\"error\": true, \"message\": \"Email sending failed! Something went wrong.\"}");
			
		}
	}
	
//	public static void testAjax(String test) {
//
//		renderJSON("{error: false, message: \"Of course I know how to answer to Ajax Calls! U have sent: "
//				+ test + " string.\"}");
//	}
}
