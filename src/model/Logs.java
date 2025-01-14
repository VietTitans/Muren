package model;

import java.time.LocalDateTime;

public abstract class Logs {

	private Employee employee;
	private LocalDateTime logTimeStamp;
	
	public Logs(Employee employee) {
		this.employee = employee;
		logTimeStamp = LocalDateTime.now();
	}
	
	public Logs(Employee employee,LocalDateTime madeAtTime) {
		this.employee = employee;
		this.logTimeStamp = madeAtTime; 
	}

	public Employee getEmployee() {
		return employee;
	}
	
	public LocalDateTime getTimeStamp() {
		return logTimeStamp;
	}
	
}
