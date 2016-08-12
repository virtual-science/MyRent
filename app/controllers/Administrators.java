package controllers;

import javax.persistence.Entity;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;

public class Administrators extends Controller {

	public static void index1() {
		render("Administrator/index.html");
	}

	// Render the Administrator index page
	public static void index() {
		Administrator admin = getCurrentAdministrator();
		List<Tenant> tenants = Tenant.findAll();
		List<Landlord> landlord = Landlord.findAll();
		render("Administrator/AdminConfPage.html", tenants, landlord, admin);
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
			index();
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
		Administrator adminlogin = Administrator.findById(Long.parseLong(userId));
		Logger.info("In Accounts controller: Logged in user is " + adminlogin.email);
		return adminlogin;
	}

	public static void logout() {
		session.remove("logged_in_administratorid");
		Welcome.index();
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

	public static void deleteLandlord(long email_landlord) {
		List<Residence> residences = Residence.findAll();
		List<Tenant> tenants = Tenant.findAll();
		Landlord landl = Landlord.findById(email_landlord);

		for (Residence residence : residences) {
			if (residence.from.equals(landl)) {
				residence.delete();
			}
		}

		for (Tenant tenant : tenants) {
			if (tenant.id != null && tenant.id.equals(landl)) {
				tenant.id = null;
				tenant.save();
			}
		}
		landl.delete();
		index();

	}

	public static void deletetenant(long email_tenant) {
		List<Residence> residences = Residence.findAll();
		Tenant tena = Tenant.findById(email_tenant);
		Logger.info("tenant deleted is:  " + tena.firstName);

		for (Residence residence : residences) {
			if (residence.tenant != null && residence.tenant.equals(tena)) {
				residence.tenant = null;
				residence.save();
			}

		}
		tena.delete();

		index();
	}

}
