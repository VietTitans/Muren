package model;

import java.util.ArrayList;
import java.time.LocalDateTime;

public class StockMaterial extends Material {

	private int minStock;
	private int maxStock;
	private int quantity;
	private ArrayList<StockReservation> stockReservations;
	private int availableAmount;
	
	public StockMaterial(int productNo, String productName, MaterialDescription materialDescription,
			Price purchasePrice, Price salesPrice, int minStock, int maxStock, int quantity) {
		
		super(productNo, productName);
			
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
	
	
	
	
}
