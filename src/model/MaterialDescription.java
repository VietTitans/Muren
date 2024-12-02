package model;

import java.time.LocalDateTime;

public class MaterialDescription {

	private LocalDateTime timeStamp;
	private String description;
	
	/* 
	 Added a second constructor, with the purpose of being able to create a 
	 MaterialDescription object both at the current time and at a chosen time.
	 */
	
	public MaterialDescription(String description) {
		timeStamp = LocalDateTime.now();
		this.description = description;
	}
	
	public MaterialDescription(LocalDateTime timeStamp, String description) {
		this.timeStamp = timeStamp;
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	
}
