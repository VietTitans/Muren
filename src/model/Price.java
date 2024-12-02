package model;

import java.time.LocalDateTime;

public class Price {
	/*
	 * timeStamp 
	 */
	private LocalDateTime priceTimeStamp;
	private double preVATValue;
	
	
	public Price(double preVATValue) {
		priceTimeStamp = LocalDateTime.now();
		this.preVATValue = preVATValue;
	}
	
	/* 
	 Added a second constructor, with the purpose of being able to create a price object both at the current time
	 and at a chosen time.
	 */
	public Price(LocalDateTime priceTimeStamp, double preVATValue) {
		this.priceTimeStamp = priceTimeStamp;
		this.preVATValue = preVATValue;
	}
	
	public double getPreVATValue() {
		return preVATValue;
	}
	
	public LocalDateTime getTimeStamp() {
		return priceTimeStamp;
	}
	
}
