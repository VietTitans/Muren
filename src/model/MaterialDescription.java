package model;

import java.time.LocalDateTime;

public class MaterialDescription {

	private LocalDateTime materialDescriptionTimeStamp;
	private String description;
	
	
	public MaterialDescription(String description) {
		materialDescriptionTimeStamp = LocalDateTime.now();
		this.description = description;
	}
	
	/* 
	 Added a second constructor, with the purpose of being able to create a 
	 MaterialDescription object both at the current time and at a chosen time.
	 */
	
	public MaterialDescription(LocalDateTime materialDescriptionTimeStamp, String description) {
		this.materialDescriptionTimeStamp = materialDescriptionTimeStamp;
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public LocalDateTime getTimeStamp() {
		return materialDescriptionTimeStamp;
	}
	
}
