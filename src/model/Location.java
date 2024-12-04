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
	
	
	@Override
	public void addMaterialToInventory(Material material) {
		materialsInInventory.put(material.getProductNo(), material);
	}

	@Override
	public Material getMaterialByProductNo(int productNo) {
		Material material = materialsInInventory.get(productNo);
		return material;
	}
	
	@Override
	public HashMap<Integer, Material> getInventory(){
		return materialsInInventory;
	}
	
	
}
