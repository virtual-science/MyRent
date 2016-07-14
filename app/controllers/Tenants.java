package controllers;

import javax.persistence.Entity;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;

public class Tenants extends Controller {
	public static void signup() {
		render("Tenant/signup.html");
	}

	public static void register(String firstName, String lastName, String email, String password, boolean terms) {
		Logger.info(firstName + " " + lastName + " " + email + " " + password);
		Tenant tenant = new Tenant (firstName, lastName, email, password);
		if (terms != false) {
			tenant.save();
			login();
		} else {
			signup();
		}
	}

	public static void login() {
		render("Tenant/login.html");
	}

	public static void authenticates(String email, String password) {
		Logger.info("Attempting to authenticate with " + email + ":" + password);

		Tenant tenant= Tenant.findByEmail(email);
		if ((tenant!= null) && (tenant.checkPassword(password) == true)) {
			Logger.info("Successfully authentication of " + tenant.firstName);
			session.put("logged_in_userid", tenant.id);
			inputTenantData.index();
		} else {
			Logger.info("Authentication failed");
			login();
		}

	}

	public static Tenant getCurrentUser() {
		String userId = session.get("logged_in_userid");
		if (userId == null) {
			return null;
		}
		Tenant logged_in_user = Tenant.findById(Long.parseLong(userId));
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
		 render("Tenant/index.html");
	 }

	 }
