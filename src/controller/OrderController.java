package controller;

import model.Customer;
import model.Employee;
import model.HourLog;
import model.Material;
import model.MaterialLog;
import model.Order;
import db.OrderDBIF;
import db.OrderDB;

public class OrderController {
	private Order currentOrder;
	private LogController logController;
	private EmployeeController employeeController;
	private MaterialController materialController;
	private OrderDBIF orderInterface;

	
	public OrderController() {
		logController = new LogController();
		employeeController = new EmployeeController();
		materialController = new MaterialController();
	}
	
	
	public void registerOrder(Employee employee) {
		currentOrder = new Order(employee);	
	}
	
	public Customer findAndAddCustomerByPhoneNo(String phoneNo) throws GeneralException {
		CustomerController customerController = new CustomerController();
		Customer foundCustomer =  customerController.findCustomerByPhoneNo(phoneNo);
		currentOrder.addCustomerToOrder(foundCustomer);
		return foundCustomer;
	}
	
	public Material findAndAddMaterialByPhoneNo(int materialNo, int quantity) {
		Material foundMaterial = materialController.findMaterialByMaterialNo(materialNo);
		MaterialLog newLog = logController.addMaterialToLog(foundMaterial, quantity);
		currentOrder.addMaterialLogToOrder(newLog);
		return foundMaterial;
	}
	
	public Employee findEmployeeByEmployeeId(int employeeId) throws GeneralException {
		Employee employee = employeeController.findEmployeeByEmployeeId(employeeId);
		return employee;
	}
	
	public void addWorkHours(Employee employee, double hours) {
		HourLog newLog = logController.AddEmployeeToHourLog(employee, hours);
		currentOrder.addHourLogToOrder(newLog);		
	}
	
	public void saveOrder() throws GeneralException {
		orderInterface.saveOrder(currentOrder);
		
		
	}

}