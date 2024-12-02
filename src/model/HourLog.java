package model;

public class HourLog extends Logs {

	private double hoursWorked;

	public HourLog(Employee employee, int hours) {
		super(employee);
		hoursWorked = hours;
	}
	
	public double getHoursWorked() {
		return hoursWorked;
	}
	public void setHoursWorked(double hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

}
