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
	static void checkAuthentification() {
		if (session.contains("logged_in_userid") == false)
			Accounts.login();
	}

	public static void index() {
		render();
	}

	public static void datainput(String geolocation, int rent, int numbOfBedrooms, String rented,
			String residenceType, int numberOfBathrooms, int areaOfResidence) {
		User user = Accounts.getCurrentUser();
		Residence finder = new Residence(user, geolocation, residenceType, rented, numbOfBedrooms, rent, numberOfBathrooms, areaOfResidence);
		finder.save();

		index();

	}

}