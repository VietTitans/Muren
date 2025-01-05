package model;



import java.util.ArrayList;

public class GenericMaterial extends Material{

	private String materialType;


	public GenericMaterial(int materialNo, String productName, ArrayList<MaterialDescription> materialDescriptions, ArrayList<Price> salesPrices, ArrayList<Price> purchasePrices, String materialType) {
		super(materialNo, productName, materialDescriptions, salesPrices, purchasePrices);
		this.materialType = materialType;

	}
	
	
	//Getters
	
	public String getMaterialType() {
		return materialType;
	}
	
	
	//Setters
	
	public void setMaterialType(String newType) {
		materialType = newType;
	}

}
