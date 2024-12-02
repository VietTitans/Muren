package model;




public class Material {

	private int productNo;
	private String productName;
	private MaterialDescription materialDescription;
	private Price purchasePrice;
	private Price salesPrice;
	
	
	public Material(String productName, MaterialDescription materialDescription, Price purchasePrice, Price salesPrice) {
		this.productName = productName;
		this.materialDescription = materialDescription;
		this.purchasePrice = purchasePrice;
		this.salesPrice = salesPrice;
	}
	
	
	
	
	
}




