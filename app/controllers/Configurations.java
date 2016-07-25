package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;

public class Configurations extends Controller {

	/**
	 * This method executed before each action call in the controller. Checks
	 * that a user has logged in. If no user logged in the user is presented
	 * with the log in screen.
	 */
	@Before
	public static void checkAuthentification() {
		if (session.contains("logged_in_landlordid") == false)
			Landlords.login();
	}

	public static void index() {
		Landlord landlord = Landlords.getCurrentUser();
		List<Residence> allResidence = Residence.findAll();
		List<Residence>  residences = new ArrayList<Residence>();
		for (Residence res : allResidence ){
			if (landlord.equals(res.from)){
				residences.add(res);
			}
		}
		
		render("Landlord/configuration.html",landlord , residences);
	}
	
		
}
