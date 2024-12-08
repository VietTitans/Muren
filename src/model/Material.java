package model;

import java.math.BigDecimal;


public abstract class Material {
	
	private int materialNo;
	private String productName;
	private MaterialDescription materialDescription;
	private Price purchasePrice;
	private Price salesPrice;
	
	public Material(int materialNo, String productName, MaterialDescription materialDescription, Price salesPrice, Price purchasePrice) {
		this.materialNo = materialNo;
		this.productName = productName;
		this.materialDescription = materialDescription;
		this.salesPrice = salesPrice;
		this.purchasePrice = purchasePrice;
		
	}

	public Material(int materialNo, String productName, MaterialDescription materialDescription) {
		this.materialNo = materialNo;
		this.productName = productName;
		this.materialDescription = materialDescription;
	}
	
	/*
	 * Protected methods are for use in the subclass only.
	 * 
	 * Where the abstract methods tells us what methods the subclasses should have,
	 * the protected method gets the fields down to the subclasses so they can use them, 
	 * but without making them public so all the other classes can access them.
	 * 
	 * in short, it gives better encapsulation.
	 * 
	 * 
	 */
	
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
		salesPrice = new Price(newValue);
	}
	
	public abstract void setCurrentPurchasePrice(BigDecimal newValue);
	
	protected void setCurrentPurchasePriceSubClasses(BigDecimal newValue) {
		purchasePrice = new Price(newValue);
	}
	
	
	public abstract void setMaterialDescription(String newDescription);
	
	protected void setMaterialDescriptionSubClasses(String description) {
		materialDescription = new MaterialDescription(description);
	}
	
	
}
