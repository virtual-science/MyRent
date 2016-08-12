package controllers;

import javax.persistence.Entity;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;

public class Tenants extends Controller {
	public static void signupp() {
		render("Tenant/signup.html");
	}

	public static void registers(String firstName, String lastName, String email, String password, boolean terms) {
		Logger.info(firstName + " " + lastName + " " + email + " " + password);
		Tenant tenant = new Tenant(firstName, lastName, email, password);
		if (terms != false) {
			tenant.save();
			loginn();
		} else {
			signupp();
		}
	}

	public static void loginn() {
		render("Tenant/login.html");
	}

	public static void authenticates(String email, String password) {
		Logger.info("Attempting to authenticate with " + email + ":" + password);

		Tenant tenant = Tenant.findByEmail(email);
		if ((tenant != null) && (tenant.checkPassword(password) == true)) {
			Logger.info("Successfully authentication of " + email);
			session.put("logged_in_tenantid", tenant.id);
			inputTenantData.index();
		} else {
			Logger.info("Authentication failed");
			loginn();
		}
	}

	public static Tenant getCurrentTenant() {
		String userId = session.get("logged_in_tenantid");
		if (userId == null) {
			return null;
		}
		Tenant logged_in_user = Tenant.findById(Long.parseLong(userId));
		//Logger.info("In Accounts controller: Logged in user is " + logged_in_user.firstName);
		return logged_in_user;
	}

	public static void edit1() {
		render();
	}

	public static void logout() {
		session.remove("logged_in_tennatid");
		Welcome.index();
	}

	// Render the Tenant index page
	public static void index() {
		render("Tenant/index.html");
	}

	// Method to Edit resident from list
	public static void editresidence(String eircode) {
		Residence residence = Residence.findByEircode(eircode);
		render("inputTenantData/index.html", residence);
	}

	}
