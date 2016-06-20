package controllers;



import play.*;
import play.mvc.*;
import java.util.*;
import models.*;

public class inputData extends Controller {
	public static void index() {
		render();
	}
	
	public static void datainput (String geolocation, int rent, int numbOfBedrooms, String rented, String residenceType){
		User user = Accounts.getCurrentUser();
		Residence finder = new Residence (user, geolocation, residenceType, rented, numbOfBedrooms, rent );
		finder.save();
		
		index();
		
	}
	
}