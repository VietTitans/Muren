package model;

import java.math.BigDecimal;

public class HourLog extends Logs {

	private BigDecimal hoursWorked;

	public HourLog(Employee employee, BigDecimal hours) {
		super(employee);
		hoursWorked = hours;
	}
	
	public BigDecimal getHoursWorked() {
		return hoursWorked;
	}
	public void setHoursWorked(BigDecimal hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

}
