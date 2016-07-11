package controllers; 
import play.*; 
import play.mvc.*; 
import java.util.*; 
import models.*; 
import java.util.List; 
import java.util.ArrayList; 
import javax.persistence.OneToMany; 
import javax.persistence.Entity; 
import play.Logger; 
import play.db.jpa.Model; 
import play.db.jpa.Blob; 
 
 
 public class editdetails extends Controller 
 { 
 	public static void index() 
 	  { 
 		String userId = session.get("logged_in_userid"); 
 	     
 	    if(userId != null) 
 	    { 
 	    	render(); 
 	    } 
 
 
 	    else 
	    { 
 	    	Landlord.edit1(); 
 	    } 
 	  } 
 	  	public static void changeDetails(String firstName, String lastName, String email, String password) 
 	  { 
 	    Logger.info("New details are: " + firstName + " " + lastName + " " + email + " " + password); 
	    String landrloardId = session.get("logged_in_userid"); 
	    Landlord landlord = Landlord.findById(Long.parseLong(landrloardId)); 
	    landlord.firstName = firstName; 
	    landlord.lastName = lastName; 
 	    landlord.email = email;  
 	    landlord.password = password;  
 	    landlord.findById(Long.parseLong(landrloardId)); 
 	    landlord.save(); 
 	    Welcome.index(); 
 	  } 
 } 
