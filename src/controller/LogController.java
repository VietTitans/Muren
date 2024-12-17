package controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import db.DBConnection;
import db.HourLogDB;
import db.HourLogDBIF;
import db.MaterialLogDB;
import db.MaterialLogDBIF;
import model.Employee;
import model.HourLog;
import model.Material;
import model.MaterialLog;
import model.Order;
import model.StockMaterial;

public class LogController {
	private HourLogDBIF hourLogInterface;
	private MaterialLogDBIF materialLogInterface;

	public LogController() throws DataAccessException {
		this.hourLogInterface = new HourLogDB();
		this.materialLogInterface = new MaterialLogDB();
	}

	public MaterialLog addMaterialToLog(Employee employee, Material material, int quantity) {
		MaterialLog currentMaterialLog = null;
		if (quantity >= 1) {

			if (material instanceof StockMaterial) {
				// Typecast Material To access subclass methods
				StockMaterial stockMaterial = (StockMaterial) material;
				if (stockMaterial.calculateAvailableAmount() >= quantity) {
					currentMaterialLog = new MaterialLog(employee, stockMaterial, quantity);
				}
			} else {
				currentMaterialLog = new MaterialLog(employee, material, quantity);
			}
			return currentMaterialLog;
		} else {
			throw new IllegalArgumentException("Invalid amount chosen");
		}
	}

	public HourLog addEmployeeToHourLog(Employee employee, BigDecimal hours) {
		HourLog currentHourLog = new HourLog(employee, hours);
		return currentHourLog;
	}

	public int saveHourLog(HourLog hourLog, int orderId) throws DataAccessException {
		int hourLogKey = hourLogInterface.saveHourLog(hourLog, orderId);
		return hourLogKey;
	}

	public int saveMaterialLog(MaterialLog materialLog, int orderId) throws DataAccessException {
		int materialLogKey = materialLogInterface.saveMaterialLog(materialLog, orderId);
		return materialLogKey;
	}
	public void saveNewHourLogs(ArrayList<HourLog> logs,int orderId , LocalDateTime time ) throws DataAccessException {
		for(HourLog log : logs) {
			if(log.getTimeStamp().isAfter(time)) {
			hourLogInterface.saveHourLog(log , orderId);
			
		}
	}

}
	public void saveNewMaterialLogs(ArrayList<MaterialLog>  logs,int orderId, LocalDateTime time) throws DataAccessException {
		for(MaterialLog log : logs) {
			if(log.getTimeStamp().isAfter(time)) {
				materialLogInterface.saveMaterialLog(log, orderId);
				
			}
		}
	}
	public void saveNewLogs(Order order, int orderId, LocalDateTime windowMadeAt) throws DataAccessException {
	
		try {
			DBConnection.getInstance().startTransaction();
			
			saveNewMaterialLogs(order.getMaterialLogs(), orderId, windowMadeAt);
			saveNewHourLogs(order.getHourLogs(), orderId, windowMadeAt);
			
			DBConnection.getInstance().commitTransaction();
		} catch (DataAccessException e) {
			DBConnection.getInstance().rollbackTransaction();
			e.printStackTrace();
		}
	
	



	}
	}

