
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
public class Administrator extends Model {

	public String email;
	public String password;

	public Administrator(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public static Administrator findByEmail(String email) {
		return find("email", email).first();
	}

	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}

}
