package controller;
import db.HourLogDBIF;
import db.MaterialLogDBIF;
import model.Employee;
import model.HourLog;
import model.Material;
import model.MaterialLog;

public class LogController{
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

	public MaterialLog addMaterialToLog(Employee employee, int productNo, int quantity) {
		MaterialController materialController = new MaterialController();
		Material materialFound = materialController.findMaterialByMaterialNo(productNo);
		MaterialLog currentMaterialLog = new MaterialLog(employee, materialFound, quantity);
		return currentMaterialLog;
	}
	
	public HourLog addEmployeeToHourLog(Employee employee, double hours) {
		currentHourLog.getHoursWorked();
		HourLog currentHourLog = new HourLog(employee, hours);
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
