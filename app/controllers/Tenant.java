package controllers;

import javax.persistence.Entity;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;

public class Tenant extends Controller {
	public static void signup() {
		render("Tenant/signup.html");
	}

	public static void register(String firstName, String lastName, String email, String password, boolean terms) {
		Logger.info(firstName + " " + lastName + " " + email + " " + password);
		Tenant tenant = new Tenant ();
		if (terms != false) {
			tenant.save();
			login();
		} else {
			signup();
		}
	}

	private void save() {
		// TODO Auto-generated method stub
		
	}

	public static void login() {
		render("Landlord/login.html");
	}

	public static void authenticate(String email, String password) {
		Logger.info("Attempting to authenticate with " + email + ":" + password);

		Landlord landlord = Landlord.findByEmail(email);
		if ((landlord != null) && (landlord.checkPassword(password) == true)) {
			Logger.info("Successfully authentication of " + landlord.firstName);
			session.put("logged_in_userid", landlord.id);
			inputData.index();
		} else {
			Logger.info("Authentication failed");
			login();
		}

	}

	public static Landlord getCurrentUser() {
		String userId = session.get("logged_in_userid");
		if (userId == null) {
			return null;
		}
		Landlord logged_in_user = Landlord.findById(Long.parseLong(userId));
		Logger.info("In Accounts controller: Logged in user is " + logged_in_user.firstName);
		return logged_in_user;
	}

	 public static void edit1()
	  {
	    render();
}
	 
	 public static void logout() {
			session.clear();
			Welcome.index();
		}
	 
	 // Render the Tenant index page
	 public static void index(){
		 render();
	 }

	 }
