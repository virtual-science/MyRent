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
		if (session.contains("logged_in_userid") == false)
			Tenants.login();
	}

	public static void index() {
		render();
	}

	
	}
