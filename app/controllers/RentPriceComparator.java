package controllers;

import java.util.Comparator;

import models.Residence;

public class RentPriceComparator implements Comparator<Residence> {

	@Override
	public int compare(Residence rentprice0, Residence rentprice1) {
		
		return Integer.compare(rentprice0.rent, rentprice1.rent);
	}

}
