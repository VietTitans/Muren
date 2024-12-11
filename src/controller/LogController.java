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
import model.StockMaterial;

public class LogController {
	private HourLogDBIF hourLogInterface;
	private MaterialLogDBIF materialLogInterface;

	public LogController() throws DataAccessException {
		this.hourLogInterface = new HourLogDB();
	}

	public MaterialLog addMaterialToLog(Employee employee, Material material, int quantity) {
		MaterialLog currentMaterialLog = null;
		if (quantity >= 1) {

			if (material instanceof StockMaterial) {
				// Typecast Material To access subclass methods
				StockMaterial stockMaterial = (StockMaterial) material;
				if (stockMaterial.calculatedAvailableAmount() >= quantity) {
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

}
