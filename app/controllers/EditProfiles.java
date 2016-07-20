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
 
 
 public class EditProfiles extends Controller 
 { 
 	public static void index() 
 	  { 
 		String landlordId = session.get("logged_in_userid"); 
 	     
 	    if(landlordId != null) 
 	    { 
 	    	render("EditProfile/index.html"); 
 	    } 
 
  	    else 
	    { 
 	    	Welcome.index(); 
 	    } 
 	  } 
 	  	public static void changeDetails(String firstName, String lastName, String address1, String address2, String city, String county, String email, String password) 
 	  { 
 	    Logger.info("New details are: " + firstName + " " + lastName + " " + address1 + " " + address2 + " " + city + " " + county + " " + email + " " + password); 
	    String landlordId = session.get("logged_in_userid"); 
	    Landlord landlord = Landlord.findById(Long.parseLong(landlordId)); 
	    landlord.firstName = firstName; 
	    landlord.lastName = lastName; 
	    landlord.address1 = address1;
	    landlord.address2 = address2;
 	    landlord.city = city;
 	    landlord.county = county;
 	    landlord.email = email;  
	    landlord.password = password;
 	    landlord.findById(Long.parseLong(landlordId)); 
 	    landlord.save(); 
 	    Welcome.index(); 
 	  }
 }
