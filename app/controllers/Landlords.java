package controllers;

import javax.persistence.Entity;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;

public class Landlords extends Controller {
	public static void signup() {
		render("Landlord/signup.html");
	}

public static void register(String firstName, String lastName, String address1, String address2, String city,
			String county, String email, String password, boolean terms) {
		Logger.info(firstName + " " + lastName + " " + email + " " + password);
		Landlord landlord = new Landlord(firstName, lastName, address1, address2, city, county, email, password);
		if (terms != false) {
			landlord.save();
			login();
		} else {
			signup();
		}
	}

	public static void login() {
		render("Landlord/login.html");
	}

	public static void authenticate(String email, String password) {
		Logger.info("Attempting to authenticate with " + email + ":" + password);

		Landlord landlord = Landlord.findByEmail(email);
		if ((landlord != null) && (landlord.checkPassword(password) == true)) {
			Logger.info("Successfully authentication of " + email);
			session.put("logged_in_landlordid", landlord.id);
			Configurations.index();
		} else {
			Logger.info("Authentication failed");
			login();
		}

	}

	public static Landlord getCurrentLandlord() {
		String userId = session.get("logged_in_landlordid");
		if (userId == null) {
			return null;
		}
		Landlord logged_in_user = Landlord.findById(Long.parseLong(userId));
		Logger.info("In Accounts controller: Logged in user is " + logged_in_user.firstName);
		return logged_in_user;
	}

	public static void edit1() {
		render();
	}

	public static void logout() {
		session.remove("logged_in_landlordid");
		Welcome.index();
	}

	// Render the Landlord index page
	public static void index() {

		render("Landlord/index.html");
	}

	// Method to delete resident from list
	public static void deleteresidence(String eircode) {
		Landlord landlord = Landlords.getCurrentLandlord();
		Logger.info("eircode is:  " + eircode);
		Residence residence = Residence.findByEircode(eircode);

		// residence.from = null;
		landlord.residences.remove(eircode);
		landlord.save();
		residence.delete();
		Configurations.index();
	}

	// Method to Edit resident from list
	public static void editresidence(String eircode) {
		Residence residence = Residence.findByEircode(eircode);
		render("Landlord/updateresidence.html", residence);
	}

	// update residence record
	public static void updateresidence(int rent, String eircode) {
		Landlord landlord = Landlords.getCurrentLandlord();
		Residence residence = Residence.findByEircode(eircode);
		residence.rent = rent;
		residence.save();
		render("Landlord/updateresidence.html", residence, landlord);
		Configurations.index();

	}

}
