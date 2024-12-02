package model;

import java.time.LocalDateTime;

public abstract class Logs {

	private Employee employee;
	private LocalDateTime timeStamp;
	
	public Logs(Employee employee) {
		this.employee = employee;
		timeStamp = LocalDateTime.now();
	}

	public Employee getEmployee() {
		return employee;
	}
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

}
