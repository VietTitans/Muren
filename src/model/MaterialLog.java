package model;

import java.time.LocalDateTime;

public class MaterialLog extends Logs {

	
	private Material material;
	private int quantity;
	
	public MaterialLog(Employee employee, Material material, int quantity) {
		super(employee);
		this.material = material;
		this.quantity = quantity;
		updateQuantity(material, quantity);
	}
	public MaterialLog(Employee employee, Material material, int quantity, LocalDateTime madeAtTime) {
		super(employee,madeAtTime);
		this.material = material;
		this.quantity = quantity;
		updateQuantity(material, quantity);
	}
	
	public void updateQuantity(Material material, int quantity) {
		try {
			if(material instanceof StockMaterial) {
				StockMaterial stockMaterial = (StockMaterial) material; 
				if(quantity <= stockMaterial.calculateAvailableAmount()) {
					int updatedQuantity = stockMaterial.getQuantity() - quantity; 
					stockMaterial.setQuantity(updatedQuantity);
				}
			}
		} catch (NullPointerException e) {
			throw new NullPointerException ("Can't update quantity");
		}
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
