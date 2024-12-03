package model;

public class MaterialLog extends Logs {

	private Material material;
	private int quantity;
	
	public MaterialLog(Employee employee, Material material, int quantity) {
		super(employee);
		this.material = material;
		this.quantity = quantity;
	}

	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
}
