package model;

import java.math.BigDecimal;
import java.util.ArrayList;


public abstract class Material {
	
	private int materialNo;
	private String productName;
	private MaterialDescription materialDescription;
	private Price purchasePrice;
	private Price salesPrice;
	private ArrayList<Price> salesPrices;
	private ArrayList<Price> purchasePrices;
	
	public Material(int materialNo, String productName, MaterialDescription materialDescription, Price salesPrice, Price purchasePrice) {
		this.materialNo = materialNo;
		this.productName = productName;
		this.materialDescription = materialDescription;
		this.salesPrice = salesPrice;
		this.purchasePrice = purchasePrice;
		
	}

	public Material(int materialNo, String productName, MaterialDescription materialDescription, 
	ArrayList<Price> salesPrices, ArrayList<Price> purchasePrices) {
		this.materialNo = materialNo;
		this.productName = productName;
		this.materialDescription = materialDescription;
		this.salesPrices = salesPrices;
		this.purchasePrices = purchasePrices;
		salesPrice = salesPrices.get(0);
		purchasePrice = purchasePrices.get(0);
		
	}
	
	public Material(int materialNo, String productName, MaterialDescription materialDescription) {
		this.materialNo = materialNo;
		this.productName = productName;
		this.materialDescription = materialDescription;
		
		
	}
	
	//Getters
	
	public abstract int getMaterialNo();
	
	protected int getMaterialNoSubClasses() {
		return materialNo;
	}
	
	public abstract String getProductName();
	
	protected String getProductNameSubClasses() {
		return productName;
	}
	/*
	 * Called currentSalesPrice because the option to search for a specific Price,
	 * at a certain time is a different use case.
	 */
	public abstract BigDecimal getCurrentSalesPrice(); 
	
	protected BigDecimal getCurrentSalesPriceSubClasses() {
		return salesPrice.getPreVATValue();
	}
	
	/*
	 * Called currentPurchasePrice because the option to search for a specific Price,
	 * at a certain time is a different use case.
	 */
	
	public abstract BigDecimal getCurrentPurchasePrice(); 
	
	protected BigDecimal getCurrentPurchasePriceSubClasses() {
		return purchasePrice.getPreVATValue();
	}
	
	
	public abstract String getMaterialDescription();
	
	protected String getMaterialDescriptionSubClasses() {
		return materialDescription.getDescription();
	}
	
	
	
	//Setters
	
	public abstract void setCurrentSalesPrice(BigDecimal newValue);
	
	protected void setCurrentSalesPriceSubClasses(BigDecimal newValue) {
		salesPrices.add(0, new Price(newValue));
		salesPrice = salesPrices.get(0);
	}
	
	public abstract void addSalesPriceToSalesPrices(Price salesPrice);
	
	protected void addSalesPriceToSalesPricesSubClasses(Price salesPrice) {
		salesPrices.add(salesPrice);
	}	
	
	public abstract void setCurrentPurchasePrice(BigDecimal newValue);
	
	protected void setCurrentPurchasePriceSubClasses(BigDecimal newValue) {	
		purchasePrices.add(0, new Price(newValue));
		purchasePrice = purchasePrices.get(0);
	
	}
	
	public abstract void addPurchasePriceToPurchasePrices(Price purchasePrice);
	
	protected void addPurchasePriceToPurchasePricesSubClasses(Price purchasePrice) {
		
	}
	
	public abstract void setMaterialDescription(String newDescription);
	
	protected void setMaterialDescriptionSubClasses(String description) {
		materialDescription = new MaterialDescription(description);
	}
	
	
}
