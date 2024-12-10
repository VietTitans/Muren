package db;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import controller.DataAccessException;
import model.GenericMaterial;
import model.HourLog;
import model.Material;
import model.MaterialLog;
import model.StockMaterial;

public class MaterialLogDB  implements MaterialLogDBIF {

	private static final String INSERT_MATERIALLOG_INTO_LOG = " INSERT INTO Logs (OrderNo, EmployeeId, LogTimeStamp) VALUES (?, ?, ?); ";
	private PreparedStatement insertMaterialLogIntoLogs;
	private static final String INSERT_MATERIALLOG_INTO_MATERIALLOG = " INSERT INTO MaterialLogs(Quantity, MaterialNo, LogId) VALUES (?, ?, ?); ";
	private PreparedStatement insertMaterialLogIntoMaterialLogs;
	private static final String UPDATE_MATERIALQUANTITY = "UPDATE StockMaterial SET Quantity = ? WHERE MaterialNo = ? ";
	private PreparedStatement updateMaterialQuantity;
	private MaterialDB materialDB;
	public MaterialLogDB() throws DataAccessException {
		try {
			MaterialDB materialDB = new MaterialDB();
		insertMaterialLogIntoLogs = DBConnection.getInstance().getConnection()
				.prepareStatement(INSERT_MATERIALLOG_INTO_LOG,java.sql.Statement.RETURN_GENERATED_KEYS);
		insertMaterialLogIntoMaterialLogs = DBConnection.getInstance().getConnection()
				.prepareStatement(INSERT_MATERIALLOG_INTO_MATERIALLOG);
		updateMaterialQuantity = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_MATERIALQUANTITY);
		} catch (SQLException e) {
			throw new DataAccessException("Could not prepare Statement", e);
		}
		
	}

	@Override
	public int saveMaterialLog(MaterialLog materialLog, int orderId) throws DataAccessException {
		try {
			//TODO:TRANSACTION? 
			//Inserts data for the Logs table
			int materialLogKey = -1;
			insertMaterialLogIntoLogs.setInt(1, orderId);
			int EmployeeId = materialLog.getEmployee().getEmployeeId();
			insertMaterialLogIntoLogs.setInt(2, EmployeeId);
			Timestamp hourLogTime = Timestamp.valueOf(materialLog.getTimeStamp());
			insertMaterialLogIntoLogs.setTimestamp(3, hourLogTime);
			
			//Runs the update and returns LogId from new Log, to be used in MaterialsLogs
			insertMaterialLogIntoLogs.executeUpdate();
			ResultSet generatedLogId = insertMaterialLogIntoLogs.getGeneratedKeys();
			
			//If the insert was successfully Insert MaterialLogs into MaterialLogs table, with generated LogId
				if (generatedLogId.next()) {
					materialLogKey = generatedLogId.getInt(1);
					insertMaterialLogIntoLogs.close();
					
					int quantity = materialLog.getQuantity();
					insertMaterialLogIntoMaterialLogs.setInt(1, quantity);
					int productNo = materialLog.getMaterial().getMaterialNo();
					insertMaterialLogIntoMaterialLogs.setInt(2, productNo);
					insertMaterialLogIntoMaterialLogs.setInt(3, materialLogKey);
					insertMaterialLogIntoMaterialLogs.executeUpdate();
					
					updateMaterialQuantity(materialLog);
					addPricesFromGenericMaterial(materialLog);
				}
		return materialLogKey;
		} catch (SQLException e) {
			throw new DataAccessException("MaterialLog could not be saved", e);
		}
	}
	public void updateMaterialQuantity(MaterialLog materialLog) throws SQLException {
		if(materialLog.getMaterial() instanceof StockMaterial){
			StockMaterial stockMaterial = (StockMaterial) materialLog.getMaterial();
			updateMaterialQuantity.setInt(1, stockMaterial.getQuantity());
			updateMaterialQuantity.setInt(2, stockMaterial.getMaterialNo());
			updateMaterialQuantity.executeUpdate();
			
		}
		
	}
	public void addPricesFromGenericMaterial(MaterialLog materialLog) throws SQLException, DataAccessException {
		if(materialLog.getMaterial() instanceof GenericMaterial) {

			MaterialDB materialDB = new MaterialDB();
			materialDB.insertNewPurchasePrice(materialLog.getMaterial().getMaterialNo(),materialLog.getMaterial().getCurrentPurchasePrice());
			materialDB.insertNewSalesPrice(materialLog.getMaterial().getMaterialNo(),materialLog.getMaterial().getCurrentSalesPrice());
		}
		
	}

}

