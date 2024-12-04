package model;

import java.util.HashMap;

public class Location extends Inventory{

	private int locationId;
	private Address address;
	
	public Location(String description, int locationId, Address address) {
		super(description);
		this.locationId = locationId;
		this.address = address;
		
	}
}
