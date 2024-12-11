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
		materialsInInventory.put(material.getMaterialNo(), material);
	}

	@Override
	public Material getMaterialByMaterialNo(int materialNo) {
		Material material = materialsInInventory.get(materialNo);
		return material;
	}
	
	@Override
	public HashMap<Integer, Material> getInventory(){
		return new HashMap<>(materialsInInventory); //returnere en klon af hashmappet, så det ikke kan modifies.
	}
	
	
}
