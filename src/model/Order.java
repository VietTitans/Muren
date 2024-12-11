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
	
	public void setOrderMadeBy(Employee orderMadeBy) {
		OrderMadeBy = orderMadeBy;
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
		try {
				for (HourLog hourLog : hourLogs) {
					EmployeeType type = hourLog.getEmployee().getEmployeeType();
					switch(type) {
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
						throw new IllegalArgumentException("Unknown employee type" + type);
					}
					
					sum = sum.add(hourrate.multiply(hourLog.getHoursWorked()));
				}
		} catch (IllegalArgumentException e) {
			e.getMessage();
			e.printStackTrace();
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
	
	public void removeMaterial(int materialIndex) {
		for (MaterialLog item : materialLogs) {
            System.out.println(item.getMaterial().getProductName());
        }
		materialLogs.remove(materialIndex);
		for (MaterialLog item : materialLogs) {
            System.out.println(item.getMaterial().getProductName());
        }
	}
	public void removeHourLog(int hourLogIndex) {
		for (HourLog item : hourLogs) {
			System.out.println(item.getHoursWorked());
		}
		hourLogs.remove(hourLogIndex);
		for (HourLog item : hourLogs) {
			System.out.println(item.getHoursWorked());
		}
	}

}
