package controller;
import java.math.BigDecimal;

import db.HourLogDB;
import db.HourLogDBIF;
import db.MaterialLogDB;
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
	
	public LogController() throws DataAccessException {
		this.hourLogInterface = new HourLogDB();
	}

	public MaterialLog addMaterialToLog(Employee employee, Material material, int quantity) {
		MaterialLog currentMaterialLog = new MaterialLog(employee, material, quantity);
		return currentMaterialLog;
	}
	
	public HourLog addEmployeeToHourLog(Employee employee, BigDecimal hours) {
		HourLog currentHourLog = new HourLog(employee, hours);
		return currentHourLog;
	}
	
	public HourLog saveHourLog(HourLog hourLog, int orderId) throws DataAccessException {
		currentHourLog = hourLogInterface.saveHourLog(hourLog, orderId);
		return currentHourLog;
	}

	public MaterialLog saveMaterialLog(MaterialLog materialLog, int orderId) throws DataAccessException {
		 currentMaterialLog = materialLogInterface.saveMaterialLog(materialLog, orderId);
		 return currentMaterialLog;
	}
	

}
