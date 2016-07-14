
package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import play.db.jpa.Blob;
import play.db.jpa.Model;
import play.Logger;

@Entity
public class Tenant extends Model {

	public String firstName;
	public String lastName;
	public String email;
	public String password;

	public Tenant(String firstName, String lastName, String email, String password) {
     	this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public static Tenant findByEmail(String email) {
		return find("email", email).first();
	}

	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}

	public String toString()
	  {
	    return firstName;
	  }

	public static void edit1() {
		// TODO Auto-generated method stub
		
	}

	public static Tenant getCurrentTenant() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
