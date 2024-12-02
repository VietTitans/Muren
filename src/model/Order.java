package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Order {

	private Employee OrderMadeBy;
	
	private Customer customer;
	private boolean isFinished;
	private LocalDate startDate;
	private LocalDate deadLine;
	private ArrayList<MaterialLog> materialLogs;
	private ArrayList<HourLog> hourLogs;
	
	public Order (Employee employee) {
		this.OrderMadeBy = employee;
		startDate = LocalDate.now();
		materialLogs = new ArrayList<>();
		hourLogs = new ArrayList<>();
	}
	
	public void addMaterialLogToOrder(MaterialLog materialLog) {
		materialLogs.add(materialLog);
	}
	
	public void addHourLogToOrder(HourLog hourlog) {
		hourLogs.add(hourlog);
	}
	
	public void addCustomerToOrder(Customer customer) {
		this.customer = customer;
	}
	
	public double calculateTotalOrderPrice() {
		return calculateTotalHoursPrice() + calculateTotalMaterialPrice();
	}

	
	public double calculateTotalHoursPrice() {
		double sum = 0;
		double hourrate = 0;
		for (HourLog hl : hourLogs) {
			EmployeeType Type = hl.getEmployee().getEmployeeType();
			switch(Type) {
			case APPRENTICE:
				hourrate = 250;
				break;
			case JOURNEYMAN:
				hourrate = 400;
				break;
			case OWNER:
				hourrate = 450;
				break;
			default:
				// TODO make an UnknownEmployeeTypeException
				break;
			}
			sum = sum + (hourrate * hl.getHoursWorked());
		}
		return sum;
	}
	
	public double calculateTotalMaterialPrice() {
		
		
		return 0;
	}
	
	public Employee getOrderMadeBy() {
		return OrderMadeBy;
	}

	public Customer getCustomer() {
		return customer;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(LocalDate deadLine) {
		this.deadLine = deadLine;
	}

	public ArrayList<MaterialLog> getMaterialLogs() {
		return materialLogs;
	}

	public ArrayList<HourLog> getHourLogs() {
		return hourLogs;
	}

}
