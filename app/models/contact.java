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
public class contact extends Model {

	public String firstName;
	public String lastName;
	public String email;
	public String sendMessage;

	public contact(String firstName, String lastName, String email, String sendMessage) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.sendMessage = sendMessage;

	}

}
