package model;

import java.time.LocalDateTime;

public abstract class Logs {

	private Employee employee;
	private LocalDateTime timeStamp;
	
	public Logs(Employee employee) {
		super();
		this.employee = employee;
		timeStamp = LocalDateTime.now();
	}

}
