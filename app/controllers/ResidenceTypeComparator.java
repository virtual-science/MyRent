package controllers;

import java.util.Comparator;

import models.Residence;

public class ResidenceTypeComparator implements Comparator<Residence> {

	@Override
	public int compare(Residence type0, Residence type1) {
		
		return type0.residenceType.compareToIgnoreCase(type1.residenceType);
		
	}

}
