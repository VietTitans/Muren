package model;

import java.util.ArrayList;
import java.time.LocalDate;


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
		updateAvailableAmount(stockReservation);	
	}
	
	
	public void updateAvailableAmount(StockReservation stockReservation) {
		availableAmount = availableAmount - stockReservation.getQuantity();
	}
	
	//Update metoden
	public int calculatedAvailableAmount() {
		int currentlyReserved = 0;
		
		for (StockReservation reservation : stockReservations) {
			//Move to stock reservation method
			LocalDate earliestDayOfReservation = reservation.getReservationDate().minusDays(reservation.getDuration()).toLocalDate();
			LocalDate lastDayOfReservation = reservation.getReservationDate().plusDays(reservation.getDuration()).toLocalDate();
			
			if(LocalDate.now().isAfter(earliestDayOfReservation) && LocalDate.now().isBefore(lastDayOfReservation)) {
				
				currentlyReserved =+ reservation.getQuantity();
				
			}
		}
		
		return quantity -currentlyReserved;
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
	
}
