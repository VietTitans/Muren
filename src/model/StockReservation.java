package model;

import java.time.LocalDateTime;

public class StockReservation {

	private int quantity;
	private LocalDateTime reservationDate;
	private int duration; /* Duration is there to show how many days before the reservation is needed, 
	that it will be valid from*/
	private int availabilityLimit; /* availabilityLimit is there to show how long after the duration has been reached
	that the reservation is still available */
	private boolean isActive;
	
	public StockReservation(int quantity, LocalDateTime reservationDate, int duration, int availabilityLimit, boolean isActive) {
		this.quantity = quantity;
		this.reservationDate = reservationDate;
		this.duration = duration;
		this.availabilityLimit = availabilityLimit;
		this.isActive = isActive;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public LocalDateTime getReservationDate() {
		return reservationDate;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public int getAvailabilityLimit() {
		return availabilityLimit;
	}
	
	public boolean getActiveStatus() {
		return isActive;
	}
	
	public void setActiveStatus(boolean newStatus) {
		isActive = newStatus;
	}
	
}
