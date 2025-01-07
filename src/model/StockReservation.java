package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class StockReservation {

	private int quantity;
	private LocalDateTime reservationDate;
	//LocalDateTime er for granudleret. Brug LocalDate. 
	
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
		LocalDate earliestDayOfReservation = getReservationDate().minusDays(getDuration()).toLocalDate();
		LocalDate lastDayOfReservation = getReservationDate().plusDays(getDuration()).toLocalDate();
		if(LocalDate.now().isAfter(earliestDayOfReservation) && LocalDate.now().isBefore(lastDayOfReservation)) {
			isActive = true;
		} else {
			isActive = false;
		}
		return isActive;
	}
	
	
}
