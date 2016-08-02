package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;

public class inputData extends Controller {

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
		render();
	}

	public static void datainput(String geolocation, int rent, int numbOfBedrooms, 
		String residenceType, int numberOfBathrooms, int areaOfResidence, String eircode) {
		Landlord landlord = Landlords.getCurrentUser(); 
		Residence finder = new Residence(landlord, geolocation, residenceType, numbOfBedrooms, rent, numberOfBathrooms, areaOfResidence, eircode);
		finder.save();

		index();

	}

	
}