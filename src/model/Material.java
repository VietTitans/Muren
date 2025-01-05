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
	
	public int getMaterialNo() {
		return materialNo;
	}
	
	public String getProductName() {
		return productName;
	}

	
	public Price getCurrentSalesPrice() {
		salesPrice = salesPrices.get(0);
		return salesPrice;
	}

	public Price getCurrentPurchasePrice() {
		purchasePrice = purchasePrices.get(0);
		return purchasePrice;
	}
	
	public MaterialDescription getCurrentMaterialDescription() {
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
	
	public Price getSalesPriceByDate(LocalDateTime timeStamp) {
		Price tempPrice = null;
		boolean found = false;
		int index = 0;
		while(!found && index < salesPrices.size()) {
			if(salesPrices.get(index).getTimeStamp().isEqual(timeStamp) || salesPrices.get(index).getTimeStamp().isBefore(timeStamp)) {
				tempPrice = salesPrices.get(index);
				found = true;
			} else {
				index++;
			}
		}
		return tempPrice;
	}
	
	public Price getPurchasePriceByDate(LocalDateTime timeStamp) {
		Price tempPrice = null;
		boolean found = false;
		int index = 0;
		while(!found && index < purchasePrices.size()) {
			if(purchasePrices.get(index).getTimeStamp().isEqual(timeStamp) || purchasePrices.get(index).getTimeStamp().isBefore(timeStamp)) {
				tempPrice = purchasePrices.get(index);
				found = true;
			} else {
				index++;
			}
		}
		return tempPrice;
	}
	
	public MaterialDescription getMaterialDescriptionByDate(LocalDateTime timeStamp) {
		MaterialDescription tempDescription = null;
		boolean found = false;
		int index = 0;
		while(!found && index < materialDescriptions.size()) {
			if(materialDescriptions.get(index).getTimeStamp().isEqual(timeStamp) || materialDescriptions.get(index).getTimeStamp().isBefore(timeStamp)) {
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
	
	public MaterialDescription getMaterialDescription() {
		return materialDescription;
	}
	
	public void setCurrentSalesPrice(Price newPrice) {
		salesPrice = newPrice;
		if(!salesPrices.contains(newPrice)) {
			addSalesPriceToSalesPrices(newPrice);
		}
	}
	
	private void addSalesPriceToSalesPrices(Price salesPrice) {
		salesPrices.add(0, salesPrice);
	}	
	
	public void setCurrentPurchasePrice(Price newPrice) {	
		purchasePrice = newPrice;
		if(!purchasePrices.contains(newPrice)) {
			addPurchasePriceToPurchasePrices(newPrice);
		}
	}
	
	private void addPurchasePriceToPurchasePrices(Price purchasePrice) {
		purchasePrices.add(0, purchasePrice);
	}
	
	public void setCurrentMaterialDescription(MaterialDescription newDescription) {
		materialDescription = newDescription;
		if(materialDescriptions.contains(newDescription)) {
			addMaterialDescriptionToMaterialDescriptions(newDescription);
		}
	}
	
	private void addMaterialDescriptionToMaterialDescriptions(MaterialDescription newDescription) {
		materialDescriptions.add(0, newDescription);
	}
	
}
