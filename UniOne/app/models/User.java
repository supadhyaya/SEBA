
package models;

import javax.persistence.Column;
import play.libs.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Table;
import play.data.validation.*;

import play.db.jpa.JPA;
import play.db.jpa.Model;

 @Entity
 @Table(name="user")
 public class User extends Model {
	@Column(name="firstName")
 	public String firstName;
 	@Column(name="lastName")
 	public String lastName;
 	@Column(name="university")
 	public String university;
 	@Email
 	@Column(name="email")
 	public String email;
 	@Column(name="password")
 	public String password;
 	@Column(name="activationKey")
 	public String activationKey;
 	@Column(name="activated")
 	public int activated;
	
	public User(String firstName, String lastName, String university, String email, String password, String activationKey, int activated) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.university = university;
		this.email = email;
		this.password = password;
		this.activationKey = activationKey;
		this.activated = activated;
		create();
	}
	
	
	public static User findByEmail(String email) {
	    return find("email", email).first();
    }
	  
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getActivationKey() {
		return activationKey;
	}
	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}
	public int getActivated() {
		return activated;
	}
	public void setActivated(int activated) {
		this.activated = activated;
	}
}
