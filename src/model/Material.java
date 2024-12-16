package model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;


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
	
	public Price getLastestPurchasePrice() {
		ArrayList<Price> tempSortedPrices = new ArrayList<Price>(purchasePrices);
		tempSortedPrices.sort(Comparator.comparing(Price::getTimeStamp).reversed());
		return tempSortedPrices.get(0);
		
	}
	public Price getLastestSalesPrice() {
		ArrayList<Price> tempSortedPrices = new ArrayList<Price>(salesPrices);
		tempSortedPrices.sort(Comparator.comparing(Price::getTimeStamp).reversed());
		return tempSortedPrices.get(0);
		
	}
	
	public abstract Price getSalesPriceByDate(LocalDateTime date);
	
	protected Price getSalesPriceByDateSubClasses(LocalDateTime date) {
		Price tempPrice = null;
		boolean found = false;
		int index = 0;
		while(!found && index < salesPrices.size()) {
			if(salesPrices.get(index).getTimeStamp().isEqual(date) || salesPrices.get(index).getTimeStamp().isBefore(date)) {
				tempPrice = salesPrices.get(index);
				found = true;
			} else {
				index++;
			}
		}
		return tempPrice;
	}
	
	public abstract Price getPurchasePriceByDate(LocalDateTime date);
	
	protected Price getPurchasePriceByDateSubClasses(LocalDateTime date) {
		Price tempPrice = null;
		boolean found = false;
		int index = 0;
		while(!found && index < purchasePrices.size()) {
			if(purchasePrices.get(index).getTimeStamp().isEqual(date) || purchasePrices.get(index).getTimeStamp().isBefore(date)) {
				tempPrice = purchasePrices.get(index);
				found = true;
			} else {
				index++;
			}
		}
		return tempPrice;
	}
	
	public abstract MaterialDescription getMaterialDescriptionByDate(LocalDateTime date);
	
	protected MaterialDescription getMaterialDescriptionByDateSubClasses(LocalDateTime date) {
		MaterialDescription tempDescription = null;
		boolean found = false;
		int index = 0;
		while(!found && index < materialDescriptions.size()) {
			if(materialDescriptions.get(index).getTimeStamp().isEqual(date) || materialDescriptions.get(index).getTimeStamp().isBefore(date)) {
				tempDescription = materialDescriptions.get(index);
				found = true;
			} else {
				index++;
			}
		}
		return tempDescription;
	}
	
	public ArrayList<Price> getSalesPrices() {
		ArrayList<Price> tempList = salesPrices;
		return tempList;
	}
	
	public ArrayList<Price> getPurchasePrices() {
		ArrayList<Price> tempList = purchasePrices;
		return tempList;
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
		purchasePrices.add(0, purchasePrice);
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
