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
		if (session.contains("logged_in_userid") == false)
			Landlords.login();
	}

	public static void index() {
		List<Residence> allResidence = Residence.findAll();
		
		render("Landlord/configuration.html", allResidence);
	}
	
	/*public static void deleteresidence(Long deleteresidence) {
		
		Logger.info("lng id is:  " +  deleteresidence);

		Residence residence = Residence.findById(deleteresidence);
		residence.from = null;
		residence.save();
		residence.delete();

		index();*/
	
}
