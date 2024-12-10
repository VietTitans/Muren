package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.math.BigDecimal;
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
	
	public BigDecimal calculateTotalOrderPrice() {
		return calculateTotalHoursPrice().add(calculateTotalMaterialPrice());
	}

	
	public BigDecimal calculateTotalHoursPrice() {
		BigDecimal sum = null;
		BigDecimal hourrate = null;
		for (HourLog hl : hourLogs) {
			EmployeeType Type = hl.getEmployee().getEmployeeType();
			switch(Type) {
			case APPRENTICE:
				hourrate = BigDecimal.valueOf(250);
				break;
			case JOURNEYMAN:
				hourrate = BigDecimal.valueOf(400);
				break;
			case OWNER:
				hourrate = BigDecimal.valueOf(450);
				break;
			default:
				// TODO make an UnknownEmployeeTypeException
				break;
			}
			sum = sum.add(hourrate.multiply(hl.getHoursWorked()));
		}
		return sum;
	}
	
	public BigDecimal calculateTotalMaterialPrice() {
		BigDecimal sum = null;
		for (MaterialLog ml : materialLogs) {
			sum = sum.add(ml.getMaterial().getCurrentSalesPrice().getPreVATValue().multiply(BigDecimal.valueOf(ml.getQuantity()))); 
		}
		return sum;
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
