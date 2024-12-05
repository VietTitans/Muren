package model;

import java.time.LocalDateTime;

public class StockReservation {

	private int quantity;
	private LocalDateTime reservationDate;
	private static final int duration = 7; /* Duration is there to show how many days before the reservation is needed, 
	that it will be valid from*/
	private static final int availabilityLimit = 3; /* availabilityLimit is there to show how long after 
	the reservation date has been reached that the reservation is still available */
	private boolean isActive;
	
	public StockReservation(int quantity, LocalDateTime reservationDate) {
		this.quantity = quantity;
		this.reservationDate = reservationDate;
		if(reservationDate.minusDays(duration).isEqual(LocalDateTime.now()) ||  reservationDate.minusDays(duration).isBefore(LocalDateTime.now())) {
			isActive = true;
		} else {
			isActive = false;
		}
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
	
	public boolean isActive() {
		return isActive;
	}
	
	public void setActiveStatus(boolean newStatus) {
		isActive = newStatus;
	}
	
}
