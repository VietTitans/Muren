package model;

import java.util.HashMap;

public abstract class Inventory {

	private String description;
	protected HashMap<Integer, Material> materialsInInventory;
	
	public Inventory(String description) {
		this.description = description;
		materialsInInventory = new HashMap<>();
	}
	
	public abstract void addMaterialToInventory(Material material);
	
	public abstract Material getMaterialByProductNo(int productNo);
	
	public abstract HashMap<Integer, Material> getInventory();
}
