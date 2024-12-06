package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Material {

	private int productNo;
	private String productName;
	private MaterialDescription materialDescription;
	private Price purchasePrice;
	private Price salesPrice;
	
	public Material(int productNo, String productName, MaterialDescription materialDescription, Price purchasePrice, Price salesPrice) {
		this.productNo = productNo;
		this.productName = productName;
		this.materialDescription = materialDescription;
		this.purchasePrice = purchasePrice;
		this.salesPrice = salesPrice;
	}

	public Material(int productNo, String productName) {
		this.productNo = productNo;
		this.productName = productName;
	}
	
	public int getProductNo() {
		return productNo;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public BigDecimal getCurrentSalesPrice() {
		return salesPrice.getPreVATValue();
	}

	public BigDecimal getCurrentPurchasePrice() {
		return purchasePrice.getPreVATValue();
	}

	public String getMaterialDescription() {
		return materialDescription.getDescription();
	}
	
	/*
	 * MaterialDescription and price setters are there to set a price for generic materials 
	 * that we dont have in stock.
	 */
	
	public void setMaterialDescription(String description) {
		materialDescription = new MaterialDescription(description);
	}
	
	public void setSalesPrice(BigDecimal preVATValue) {
		salesPrice = new Price(preVATValue);
	}
	
	public void setPurchasePrice(BigDecimal preVATValue) {
		purchasePrice = new Price(preVATValue);
	}
	
}
