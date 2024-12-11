package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Price {

	private LocalDateTime priceTimeStamp;
	private BigDecimal preVATValue;
	
	
	public Price(BigDecimal preVATValue) {
		priceTimeStamp = LocalDateTime.now();
		this.preVATValue = preVATValue;
	}
	
	/* 
	 Added a second constructor, with the purpose of being able to create a price object both at the current time
	 and at a chosen time.
	 */
	public Price(LocalDateTime priceTimeStamp, BigDecimal preVATValue) {
		this.priceTimeStamp = priceTimeStamp;
		this.preVATValue = preVATValue;
	}
	
	public BigDecimal getPreVATValue() {
		return preVATValue;
	}
	
	public LocalDateTime getTimeStamp() {
		return priceTimeStamp;
	}
	
}
