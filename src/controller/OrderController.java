package controller;

import model.Customer;
import model.Employee;
import model.HourLog;
import model.Material;
import model.MaterialLog;
import model.Order;
import db.OrderDBIF;

import java.math.BigDecimal;
import java.sql.SQLException;

import db.OrderDB;

public class OrderController {
	private Order currentOrder;
	private LogController logController;
	private EmployeeController employeeController;
	private MaterialController materialController;
	private OrderDBIF orderInterface;

	public OrderController() throws DataAccessException, SQLException {
		logController = new LogController();
		employeeController = new EmployeeController();
		materialController = new MaterialController();
		currentOrder = new Order(null);
		orderInterface = new OrderDB();
	}
	
	
	public void registerOrder(Employee employee) {
		currentOrder = new Order(employee);	
	}
	
	public Customer findAndAddCustomerByPhoneNo(String phoneNo) throws DataAccessException {
		CustomerController customerController = new CustomerController();
		Customer foundCustomer =  customerController.findCustomerByPhoneNo(phoneNo, false);
		currentOrder.addCustomerToOrder(foundCustomer);
		return foundCustomer;
	}
	
	public Material findAndAddMaterialByMaterialNo(Employee employee, int materialNo, int quantity) throws DataAccessException {
		Material foundMaterial = materialController.findMaterialByMaterialNo(materialNo);
		if (employee != null && foundMaterial != null && quantity > 0 && quantity <= 99){
		MaterialLog newLog = logController.addMaterialToLog(employee, foundMaterial, quantity);
		currentOrder.addMaterialLogToOrder(newLog);
		}
		return foundMaterial;
	}
	
	public Employee findEmployeeByEmployeeId(int employeeId, boolean fullAssociation) throws GeneralException, DataAccessException {
		Employee employee = employeeController.findEmployeeByEmployeeId(employeeId, fullAssociation);
		return employee;
	}
	
	public void addWorkHours(Employee employee, BigDecimal hours) throws Exception {
		BigDecimal minBoundary = new BigDecimal(0.00);
		BigDecimal maxBoundary = new BigDecimal(99.00);
		if(employee != null && hours.compareTo(minBoundary) >= 0.00 && hours.compareTo(maxBoundary) <= 0) {
		HourLog newLog = logController.addEmployeeToHourLog(employee, hours);
		currentOrder.addHourLogToOrder(newLog);	
		}else {
			throw new Exception("Invalid amount chosen");
		}
		
	}
	public Order getCurrentOrder() {
		return currentOrder;
	}


	public void removeMaterialLog(int materialLogIndex) {
		currentOrder.removeMaterial(materialLogIndex);
	}
	public void removeHourLog(int hourLogIndex) {
		currentOrder.removeHourLog(hourLogIndex);
	}
	
	public void saveOrder() throws GeneralException, DataAccessException {
		orderInterface.saveOrder(currentOrder);
	}
	public void removeCustomer() {
		currentOrder.setCustomer(null);

	}
	public Customer getCustomer() {
		return currentOrder.getCustomer();


	}
}