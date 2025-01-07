package model;

import java.util.HashMap;

public abstract class Inventory {

	private String description;
//	protected HashMap<Material,Integer> materialsInInventory;
	protected HashMap<Integer,Material> materialsInInventory;
	
	public Inventory(String description) {
		this.description = description;
		materialsInInventory = new HashMap<>();
	}
	
	public abstract void addMaterialToInventory(Material material);
	
	public abstract Material getMaterialByMaterialNo(int materialNo);
	//Byttet om på key og value
	public abstract HashMap<Integer, Material> getInventory();
//	public HashMap<Material, Integer> getInventory() {
//	return null;
//}
}
