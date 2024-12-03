package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class Material {

	private int productNo;
	private String productName;
	private MaterialDescription materialDescription;
	private Price purchasePrice;
	private Price salesPrice;
	
	/*
	 * Constructor doesn't yet take into consideration if the productName and productNo is already taken.
	 */
	
	public Material(int productNo, String productName, MaterialDescription materialDescription, Price purchasePrice, Price salesPrice) {
		this.productNo = productNo;
		this.productName = productName;
		this.materialDescription = materialDescription;
		this.purchasePrice = purchasePrice;
		this.salesPrice = salesPrice;
	}
	
	public int getProductNo() {
		return productNo;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public BigDecimal getSalesPriceNow() {
		return salesPrice.getPreVATValue();
	}

	public BigDecimal getPurchasePriceNow() {
		return purchasePrice.getPreVATValue();
	}

	public String getMaterialDescriptionNow() {
		return materialDescription.getDescription();
	}
	
}
