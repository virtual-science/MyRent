package controllers;

import java.util.Comparator;

import models.Residence;

public class rentComparator implements Comparator<Residence> {

	@Override
	public int compare(Residence t1, Residence t2) {
		
		return Integer.compare(t1.rent, t2.rent);
	}

}
