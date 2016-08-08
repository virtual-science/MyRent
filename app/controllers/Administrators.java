package controllers;

import javax.persistence.Entity;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;

public class Administrators extends Controller {

	public String id;
	public String email;
	public String password;

	// Render the Administrator index page
	public static void index() {
		Administrator admin = getCurrentAdministrator();
		List<Tenant> tenants = Tenant.findAll();
		List<Landlord> landlord = Landlord.findAll();
		render("Administrator/index.html", tenants, landlord, admin);
	}

	public static void logins() {
		render("Administrator/login.html");
	}

	public static void authentications(String email, String password) {
		Logger.info("Attempting to authenticate with " + email + ":" + password);

		Administrator admin = Administrator.findByEmail(email);
		if ((admin != null) && (admin.checkPassword(password) == true)) {
			Logger.info("Successfully authentication of ");
			session.put("logged_in_admin", admin.id);
			render("Administrator/AdminConfPage.html");
		} else {
			Logger.info("Authentication failed");
			logins();
		}
	}

	public static Administrator getCurrentAdministrator() {
		String userId = session.get("logged_in_adminid");
		if (userId == null) {
			return null;
		}
		Administrator logged_in_user = Administrator.findById(Long.parseLong(userId));
		Logger.info("In Accounts controller: Logged in user is " + logged_in_user.firstName);
		return logged_in_user;
	}

	public static void logout() {
		session.remove("logged_in_administratorid");
		Welcome.index();
	}

	// Method to delete tenant from list
	public static void deletetenant(String eircode) {
		Administrator admin = Administrators.getCurrentAdministrator();
		Logger.info("eircode is:  " + eircode);
		Residence residence = Residence.findByEircode(eircode);

		// residence.from = null;
		admin.residences.remove(eircode);
		admin.save();
		residence.delete();
		render("Administrator/AdminConfPage.html");
	}
	
	public static void getlocationCordinates() {
		int flag = 0;
		List<Residence> allResi = Residence.findAll();
		List<List<String>> jsArray = new ArrayList<List<String>>();

		for (Residence res : allResi) {

			
				
				String id = Long.toString(res.id);
				String lon = Double.toString(res.getlocation().getLongitude());
				String lat = Double.toString(res.getlocation().getLatitude());
				String tName = (res.tenant == null) ? "avaiable" : res.tenant.firstName;
				String eircode = res.eircode;

				jsArray.add(flag, Arrays.asList(id, lat, lon, tName, eircode));
				flag++;
			}
		
		renderJSON(jsArray);

	

}
}
