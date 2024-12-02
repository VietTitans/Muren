package model;

import java.time.LocalDateTime;

public class Price {
	/*
	 * timeStamp 
	 */
	private LocalDateTime timeStamp;
	private double price;
	
	
	public Price(double price) {
		timeStamp = LocalDateTime.now();
		this.price = price;
	}
	
	/* 
	 Added a second constructor, with the purpose of being able to create a price object both at the current time
	 and at a chosen time.
	 */
	public Price(LocalDateTime timeStamp, double price) {
		this.timeStamp = timeStamp;
		this.price = price;
	}
	
	public double getPrice() {
		return price;
	}
	
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	
}
