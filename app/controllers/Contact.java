package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;

public class Contact extends Controller {
	public static void index() {
		render();
	}

	public static void acknowledgement() {
		render();
	}

	public static void storeContact(String firstName, String lastName, String email, String sendMessage) {
		contact con = new contact(firstName, lastName, email, sendMessage);
		con.save();
		acknowledgement();
	}
}