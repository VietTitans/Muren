package model;

import java.util.ArrayList;
import java.math.BigDecimal;


public class StockMaterial extends Material {

	private int minStock;
	private int maxStock;
	private int quantity;
	private ArrayList<StockReservation> stockReservations;
	private int availableAmount;
	
	public StockMaterial(int productNo, String productName, MaterialDescription materialDescription, 
			Price salesPrice, Price purchasePrice, int minStock, int maxStock, int quantity) {
		
		super(productNo, productName, materialDescription, salesPrice, purchasePrice);
			
		this.minStock = minStock;
		this.maxStock = maxStock;
		this.quantity = quantity;
		availableAmount = quantity;
		stockReservations = new ArrayList<>();
	}
	
	public void addStockReservation(StockReservation stockReservation) {		
		stockReservations.add(stockReservation);
		updateAvailableAmount(stockReservation);	
	}
	
	
	public void updateAvailableAmount(StockReservation stockReservation) {
		availableAmount = availableAmount - stockReservation.getQuantity();
	}
	
	//Getters
	
	public int getAvailableAmount() {
		return availableAmount;
	}
		
	public int getQuantity() {
		return quantity;
	}
	
	public int getMinStock() {
		return minStock;
	}
	
	public int getMaxStock() {
		return maxStock;
	}
	
	public ArrayList<StockReservation> getStockReservations(){
		return stockReservations;
	}

	@Override
	public int getProductNo() {
		return super.getProductNoSubClasses();
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

	
	//Setters
	
	@Override
	public void setCurrentSalesPrice(BigDecimal newValue) {
		super.setCurrentPurchasePriceSubClasses(newValue);
	}
	
	@Override
	public void setCurrentPurchasePrice(BigDecimal newValue) {
		super.setCurrentPurchasePriceSubClasses(newValue);	
	}
	
	@Override
	public void setMaterialDescription(String newDescription) {
		super.setMaterialDescriptionSubClasses(newDescription);
		
	}

	
	
	
	
	
	
}
