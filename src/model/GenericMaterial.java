package model;


import java.time.LocalDateTime;
import java.util.ArrayList;

public class GenericMaterial extends Material{

	private String materialType;


	public GenericMaterial(int materialNo, String productName, ArrayList<MaterialDescription> materialDescriptions, ArrayList<Price> salesPrices, ArrayList<Price> purchasePrices, String materialType) {
		super(materialNo, productName, materialDescriptions, salesPrices, purchasePrices);
		this.materialType = materialType;

	}
	
	
	//Getters
	
	@Override
	public int getMaterialNo() {
		return super.getMaterialNoSubClasses();
	}

	@Override
	public String getProductName() {
	
		return super.getProductNameSubClasses();
	}

	@Override
	public Price getCurrentSalesPrice() {
		return super.getCurrentSalesPriceSubClasses();
	}

	@Override
	public Price getCurrentPurchasePrice() {
		return super.getCurrentPurchasePriceSubClasses();
	}

	@Override
	public MaterialDescription getCurrentMaterialDescription() {
		return super.getCurrentMaterialDescriptionSubClasses();
	}
	
	@Override
	public Price getSalesPriceByDate(LocalDateTime date) {
		return super.getSalesPriceByDateSubClasses(date);
	}



	@Override
	public Price getPurchasePriceByDate(LocalDateTime date) {
		return super.getPurchasePriceByDateSubClasses(date);
	}



	@Override
	public MaterialDescription getMaterialDescriptionByDate(LocalDateTime date) {
		return super.getMaterialDescriptionByDateSubClasses(date);
	}
	
	public String getMaterialType() {
		return materialType;
	}
	
	
	//Setters
	
	@Override
	public void setCurrentMaterialDescription(MaterialDescription newDescription) {
		super.setCurrentMaterialDescriptionSubClasses(newDescription);
		
	}

	@Override
	public void setCurrentSalesPrice(Price newPrice) {
		super.setCurrentSalesPriceSubClasses(newPrice);
	}

	
	@Override
	public void setCurrentPurchasePrice(Price newPrice) {
		super.setCurrentPurchasePriceSubClasses(newPrice);
	}
	

	public void setMaterialType(String newType) {
		materialType = newType;
	}

}
