
package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.db.jpa.Blob;
import play.db.jpa.Model;
import play.Logger;

@Entity
public class Landlord extends Model {

	public String firstName;
	public String lastName;
	public String address1;
	public String address2;
	public String city;
	public String county;
	public String email;
	public String password;
	
	@OneToMany(mappedBy = "from", cascade = CascadeType.ALL) 
	  public List<Residence> residences = new ArrayList<Residence>();
	
		public Landlord(String firstName, String lastName, String address1, String address2, String city, String county, String email, String password) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.county = county;
		this.email = email;
		this.password = password;
		}

	public static Landlord findByEmail(String email) {
		return find("email", email).first();
	}

	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}

	public String toString()
	  {
	    return firstName;
	  }

	
		
	}
	
