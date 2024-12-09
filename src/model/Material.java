package model;


import java.util.ArrayList;


public abstract class Material {
	
	private int materialNo;
	private String productName;
	private MaterialDescription materialDescription;
	private Price purchasePrice;
	private Price salesPrice;
	private ArrayList<Price> salesPrices;
	private ArrayList<Price> purchasePrices;
	private ArrayList<MaterialDescription> materialDescriptions;
			
	

	public Material(int materialNo, String productName, ArrayList<MaterialDescription> materialDescriptions, 
	ArrayList<Price> salesPrices, ArrayList<Price> purchasePrices) {
		this.materialNo = materialNo;
		this.productName = productName;
		this.materialDescriptions = materialDescriptions;
		this.salesPrices = salesPrices;
		this.purchasePrices = purchasePrices;
		
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
	public abstract Price getCurrentSalesPrice(); 
	
	protected Price getCurrentSalesPriceSubClasses() {
		salesPrice = salesPrices.get(0);
		return salesPrice;
	}
	
	/*
	 * Called currentPurchasePrice because the option to search for a specific Price,
	 * at a certain time is a different use case.
	 */
	
	public abstract Price getCurrentPurchasePrice(); 
	
	protected Price getCurrentPurchasePriceSubClasses() {
		purchasePrice = purchasePrices.get(0);
		return purchasePrice;
	}
	
	
	public abstract MaterialDescription getCurrentMaterialDescription();
	
	protected MaterialDescription getCurrentMaterialDescriptionSubClasses() {
		materialDescription = materialDescriptions.get(0);
		return materialDescription;	
	}
	
	
	
	//Setters
	
	public abstract void setCurrentSalesPrice(Price newPrice);
	
	protected void setCurrentSalesPriceSubClasses(Price newPrice) {
		salesPrice = newPrice;
		if(!salesPrices.contains(newPrice)) {
			addSalesPriceToSalesPricesSubClasses(newPrice);
		}
	}
	
	private void addSalesPriceToSalesPricesSubClasses(Price salesPrice) {
		salesPrices.add(0, salesPrice);
	}	
	
	public abstract void setCurrentPurchasePrice(Price newPrice);
	
	protected void setCurrentPurchasePriceSubClasses(Price newPrice) {	
		purchasePrice = newPrice;
		if(!purchasePrices.contains(newPrice)) {
			addPurchasePriceToPurchasePricesSubClasses(newPrice);
		}
	}
	
	
	private void addPurchasePriceToPurchasePricesSubClasses(Price purchasePrice) {
		purchasePrices.add(0, salesPrice);
	}
	
	public abstract void setCurrentMaterialDescription(MaterialDescription newDescription);
	
	protected void setCurrentMaterialDescriptionSubClasses(MaterialDescription newDescription) {
		materialDescription = newDescription;
		if(materialDescriptions.contains(newDescription)) {
			addMaterialDescriptionToMaterialDescriptionsSubClasses(newDescription);
		}
		
	}
	
	
	private void addMaterialDescriptionToMaterialDescriptionsSubClasses(MaterialDescription newDescription) {
		materialDescriptions.add(0, newDescription);
	}
	
}
