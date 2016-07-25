package models;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import play.Logger;
import play.db.jpa.Model;
import utils.LatLng;

@Entity
public class Residence extends Model {

	public String geolocation;
	public String postDate;
	public String residenceType;
	public String eircode;
	public int numberOfBathrooms;
	public int areaOfResidence;
	public int rent; // how much rent is
	public int numbOfBedrooms;
	public String rented;
		
	
	@ManyToOne
	public Landlord from;
	
	@OneToOne(mappedBy = "residence", cascade = CascadeType.ALL)
	public Tenant tenant;
	
	

	public Residence(Landlord from, String geolocation, String residenceType, int numbOfBedrooms, int rent, int numberOfBathrooms, int areaOfResidence, String eircode, String rented) {

		this.from = from;
		this.geolocation = geolocation;
		this.residenceType = residenceType;
		this.rent = rent;
		this.numbOfBedrooms = numbOfBedrooms;
		this.numberOfBathrooms = numberOfBathrooms;
		this.areaOfResidence = areaOfResidence;
		postDate = dateValidator();
		this.eircode = eircode;
		this.rented = rented;
		
	}

	public String dateValidator() {
		Date createOn = new Date();
		createOn = new Timestamp(createOn.getTime());
		DateFormat dformat = new SimpleDateFormat("E dd/MM/yy - KK:mm a");
		Logger.info("Date Created On " + createOn + " postDate " + dformat.format(createOn));
		return dformat.format(createOn);
	}
	
	public static Residence findByEircode (String eircode){
		
		return find("eircode", eircode).first();
		
	}
	
	
}
