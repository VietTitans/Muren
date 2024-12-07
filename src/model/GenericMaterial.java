package model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class GenericMaterial extends Material{

	private String materialType;


	public GenericMaterial(int materialNo, String productName, MaterialDescription materialDescription, ArrayList<Price> salesPrices, ArrayList<Price> purchasePrices, String materialType) {
		super(materialNo, productName, materialDescription, salesPrices, purchasePrices);
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
	public BigDecimal getCurrentSalesPrice() {
		return super.getCurrentSalesPriceSubClasses();
	}

	@Override
	public BigDecimal getCurrentPurchasePrice() {
		return super.getCurrentPurchasePriceSubClasses();
	}

	@Override
	public String getMaterialDescription() {
		return super.getMaterialDescriptionSubClasses();
	}
	
	
	public String getMaterialType() {
		return materialType;
	}
	
	
	//Setters
	
	@Override
	public void setMaterialDescription(String newDescription) {
		super.setMaterialDescriptionSubClasses(newDescription);
		
	}

	@Override
	public void setCurrentSalesPrice(BigDecimal newValue) {
		super.setCurrentSalesPriceSubClasses(newValue);
	}

	@Override
	public void addSalesPriceToSalesPrices(Price salesPrice) {
		super.addSalesPriceToSalesPricesSubClasses(salesPrice);
		
	}
	
	@Override
	public void setCurrentPurchasePrice(BigDecimal newValue) {
		super.setCurrentPurchasePriceSubClasses(newValue);
	}
	
	@Override
	public void addPurchasePriceToPurchasePrices(Price purchasePrice) {
		super.addPurchasePriceToPurchasePricesSubClasses(purchasePrice);
		
	}

	public void setMaterialType(String newType) {
		materialType = newType;
	}

	

	



}
