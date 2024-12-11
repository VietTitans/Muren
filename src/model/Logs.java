package model;

import java.time.LocalDateTime;

public abstract class Logs {

	private Employee employee;
	private LocalDateTime LogtimeStamp;
	
	public Logs(Employee employee) {
		this.employee = employee;
		LogtimeStamp = LocalDateTime.now();
	}

	public abstract Employee getEmployee();
	
	protected Employee getEmployeeSubClass() {
		return employee;
	}
	
	public abstract LocalDateTime getTimeStamp();

	protected LocalDateTime getTimeStampSubClass() {
		return LogtimeStamp;
	}
	
}
