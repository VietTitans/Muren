package model;

import java.time.LocalDateTime;

public abstract class Logs {

	private Employee employee;
	private LocalDateTime timeStamp;
	
	public Logs(Employee employee) {
		this.employee = employee;
		timeStamp = LocalDateTime.now();
	}

	public abstract Employee getEmployee();
	
	protected Employee getEmployeeSubClass() {
		return employee;
	}
	
	public abstract LocalDateTime getTimeStamp();

	protected LocalDateTime getTimeStampSubClass() {
		return timeStamp;
	}
	
}
