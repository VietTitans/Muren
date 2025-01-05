package model;


import java.util.ArrayList;



public class StockMaterial extends Material {

	private int minStock;
	private int maxStock;
	private int quantity;
	private ArrayList<StockReservation> stockReservations;
	private int availableAmount;
	
	public StockMaterial(int materialNo, String productName, ArrayList<MaterialDescription> materialDescriptions, 
			ArrayList<Price> salesPrices, ArrayList<Price> purchasePrices, int minStock, int maxStock, int quantity) {
		
		super(materialNo, productName, materialDescriptions, salesPrices, purchasePrices);
			
		this.minStock = minStock;
		this.maxStock = maxStock;
		this.quantity = quantity;
		availableAmount = quantity;
		stockReservations = new ArrayList<>();
	}
	
	public void addStockReservation(StockReservation stockReservation) {		
		stockReservations.add(stockReservation);
	}
	
	public int calculateAvailableAmount() {
		int currentlyReserved = 0;
		
		for (StockReservation reservation : stockReservations) {
			if(reservation.isActive() == true) {
				currentlyReserved =+ reservation.getQuantity();
			}
		}
		availableAmount = quantity - currentlyReserved;
		return availableAmount;
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
	
	//Setters
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
