package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import models.Landlord;
import models.Residence;
import models.Tenant;
import play.Logger;
import play.mvc.Before;
import play.mvc.Controller;
import utils.Circle;
import utils.Geodistance;
import utils.LatLng;

public class reportVacantResidence extends Controller {
	/**
	 * This method executed before each action call in the controller. Checks
	 * that a user has logged in. If no user logged in the user is presented
	 * with the log in screen.
	 */
	@Before
	static void checkAuthentification() {
		if (session.contains("logged_in_tenantid") == false)
			Tenants.loginn();
	}

	/**
	 * Generates a Report instance relating to all residences within circle
	 * 
	 * @param radius
	 *            The radius (metres) of the search area
	 * @param latcenter
	 *            The latitude of the centre of the search area
	 * @param lngcenter
	 *            The longtitude of the centre of the search area
	 */
	public static void generateReports(double radius, double latcenter, double lngcenter) {
		Logger.info("radius:" + radius);
		// All reported residences will fall within this circle
		Circle circle = new Circle(latcenter, lngcenter, radius);

		Tenant tenant = Tenants.getCurrentTenant();
		List<Residence> residences = new ArrayList<Residence>();
		// Fetch all residences and filter out those within circle
		List<Residence> residencesAll = Residence.findAll();
		for (Residence res : residencesAll) {
			// LatLng residenceLocation = res.getGeolocation();
			LatLng residenceLocation = LatLng.toLatLng(res.geolocation);
			if (Geodistance.inCircle(residenceLocation, circle) && res.tenant == null) {
				residences.add(res);
			}
			//Collections.sort(residences, new rentComparator());
		}
		render("reportVacantResidence/index.html", tenant, circle, residences);
	}

	/**
	 * 
	 * Render the views/ReportVacantREsidence/index.html template This presents a map
	 * and resizable circle to indicate a search area for residences
	 */
	public static void index() {
		render("reportVacantResidence/index.html");
	}
}