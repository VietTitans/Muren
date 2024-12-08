package model;

import java.util.ArrayList;
import java.math.BigDecimal;
import java.time.LocalDate;


public class StockMaterial extends Material {

	private int minStock;
	private int maxStock;
	private int quantity;
	private ArrayList<StockReservation> stockReservations;
	private int availableAmount;
	
	public StockMaterial(int materialNo, String productName, MaterialDescription materialDescription, 
			ArrayList<Price> salesPrices, ArrayList<Price> purchasePrices, int minStock, int maxStock, int quantity) {
		
		super(materialNo, productName, materialDescription, salesPrices, purchasePrices);
			
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
	public int calculatedAvailableAmount() {
		int currentlyReserved = 0;
		
		for (StockReservation reservation : stockReservations) {
			
			LocalDate earliestDayOfReservation = reservation.getReservationDate().minusDays(reservation.getDuration()).toLocalDate();
			LocalDate lastDayOfReservation = reservation.getReservationDate().plusDays(reservation.getDuration()).toLocalDate();
			
			if(LocalDate.now().isAfter(earliestDayOfReservation) && LocalDate.now().isBefore(lastDayOfReservation)) {
				reservation.setActiveStatus(true);
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
	
	@Override
	public void setMaterialDescription(String newDescription) {
		super.setMaterialDescriptionSubClasses(newDescription);
		
	}

	
	
	
	
	
	
}
