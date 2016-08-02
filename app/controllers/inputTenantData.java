package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;

public class inputTenantData extends Controller {

	/**
	 * This method executed before each action call in the controller. Checks
	 * that a user has logged in. If no user logged in the user is presented
	 * with the log in screen.
	 */
	@Before
	public static void checkAuthentification() {
		if (session.contains("logged_in_tenantid") == false)
			Tenants.login();
	}

	public static void index() {
		String TenantEircode = null;
		Residence reside = null;
		Tenant userTenant = Tenants.getCurrentTenant();
		List<Residence> residences = Residence.findAll();
		List<Residence> allResidences = new ArrayList<Residence>();

		for (Residence res : residences) {
			if (res.tenant != null) {
				if (res.tenant.equals(userTenant)) {
					reside = res;
				} else {
					reside = reside;
				}
			}
		}
		
		for (Residence res : residences) {
			if (res.tenant == null) {
				allResidences.add(res);
			}
		}

		if (reside == null) {
			TenantEircode = " ";
		} else {
			TenantEircode = reside.eircode;
		
	}

	render("inputTenantData/index.html", userTenant, allResidences, TenantEircode);
	}


	// Method to Edit resident from list  
	 public static void changetenancy (String eircode) {
		 	Residence residence = Residence.findByEircode(eircode);
		 	render("InputTenantData/index.html", residence);
	 }
		 	
		 	// update residence record
		 	public static void updateresidence (int rent, String eircode) {
		 		Tenant userTenant = Tenants.getCurrentTenant();		 		
		 		Residence residence = Residence.findByEircode(eircode);
		 		residence.rent = rent ;
		 		residence.save();
		 		render("Landlord/updateresidence.html", residence);
		 		Configurations.index();
		 	}
	
	
		 	public static void tenancyTerminate() {

				Tenant userTenant = Tenants.getCurrentTenant();
				
				List<Residence> tenantResident = Residence.findAll();

				for (Residence residence : tenantResident) {
					if (residence.tenant != null) {

					}
					if (residence.tenant.equals(userTenant))
						break;
					{
						Logger.info("The residence deleted is : " + residence.tenant.firstName + "'s resident");

						residence.tenant = null;
						userTenant.residence = null;
						userTenant.save();
						residence.save();
						index();
					}
				}
			}

			public static void Changetenancy(long newResidence) {
				Logger.info("You have updated residence: " + newResidence);

				Residence residence = Residence.findById(newResidence);

				Tenant tenant = Tenants.getCurrentTenant();

				if (tenant.residence == null) {

					residence.tenant = tenant;
					tenant.residence = residence;
					tenant.save();
					residence.save();
					index();
				}

				Logger.info("Invalid you have to terminate residency, for a new tenancy to be added");
				index();
			}
}
