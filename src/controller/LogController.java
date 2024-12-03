package controller;
import db.HourLogDBIF;
import db.MaterialLogDBIF;
import model.Employee;
import model.HourLog;
import model.Material;
import model.MaterialLog;

public class LogController{
	private LogController logController;
	private OrderController orderController;
	private MaterialLog currentMaterialLog;
	private HourLog currentHourLog;
	private HourLogDBIF hourLogInterface;
	private MaterialLogDBIF materialLogInterface;
	
	public LogController() {
		this.orderController = new OrderController();
		this.hourLogInterface = new HourLogDB();
		this.materialLogInterface = new MaterialLogDB();
	}
	
	public HourLog createHourLog() {
		if (currentHourLog == null) {
			this.currentHourLog = new HourLog(null, 0);
		}
		return currentHourLog;
	}
	
	public MaterialLog createMaterialLog() {
		if (currentMaterialLog == null) {
			this.currentMaterialLog = new MaterialLog(null, null, 0);
		}
		return currentMaterialLog;
	}

	public MaterialLog addMaterialToLog(int employeeId, int productNo, int quantity) {
		EmployeeController employeeController = new EmployeeController(); 
		MaterialController materialController = new MaterialController();
		Employee foundEmployee = employeeController.findEmployeeByEmployeeId(employeeId);
		Material materialFound = materialController.findMaterialByMaterialNo(productNo);
		MaterialLog currentMaterialLog = new MaterialLog(foundEmployee, materialFound, quantity);
		return currentMaterialLog;
	}
	
	public HourLog addEmployeeToHourLog(int employeeId, double hours) {
		EmployeeController employeeController = new EmployeeController(); 
		Employee foundEmployee = employeeController.findEmployeeByEmployeeId(employeeId);
		currentHourLog.getHoursWorked();
		HourLog currentHourLog = new HourLog(foundEmployee, hours);
		return currentHourLog;
	}
	
	public HourLog saveHourLog(HourLog hourLog, int orderId) {
		currentHourLog = hourLogInterface.saveHourLog(hourLog, orderId);
		return currentHourLog;
	}

	public MaterialLog saveMaterialLog(MaterialLog materialLog, int orderId) {
		 currentMaterialLog = materialLogInterface.saveMaterialLog(materialLog, orderId);
		 return currentMaterialLog;
	}
	

}
