package controller;
import db.HourLogDBIF;
import db.MaterialLogDBIF;
import model.Employee;
import model.HourLog;
import model.Material;
import model.MaterialLog;

public class LogController implements MaterialLogDBIF, HourLogDBIF{
	private LogController logController;
	private OrderController orderController;
	private MaterialLog currentMaterialLog;
	private HourLog currentHourLog;
	private HourLogDBIF hourLogInterface;
	
	public LogController() {
		this.logController = new LogController();
		this.orderController = new OrderController();
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
		Employee foundEmployee = employeeController.findEmployeeByEmployeeId(employeeId);
		
		MaterialController materialController = new MaterialController();
		Material materialFound = materialController.findMaterialByMaterialNo(productNo);
		MaterialLog materialLog = new MaterialLog(foundEmployee, materialFound, quantity);
		return materialLog;
	}
	
	public HourLog addEmployeeToHourLog(int employeeId, double hours) {
		EmployeeController employeeController = new EmployeeController(); 
		Employee foundEmployee = employeeController.findEmployeeByEmployeeId(employeeId);
		currentHourLog.getHoursWorked();
		HourLog currentHourLog = new HourLog(foundEmployee, hours);
		return currentHourLog;
	}
	
	@Override
	public HourLog saveHourLog(HourLog hourLog, int orderId) {
		
		return null;
	}

	@Override
	public MaterialLog saveMaterialLog(MaterialLog materialLog, int orderId) {
		
		return null;
	}
	

}
