package db;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import controller.DataAccessException;
import model.HourLog;
import model.MaterialLog;

public class MaterialLogDB  implements MaterialLogDBIF {

	private static final String INSERT_MATERIALLOG_INTO_LOG = " INSERT INTO Logs (OrderNo, EmployeeId, LogTimeStamp) VALUES (?, ?, ?); ";
	private PreparedStatement insertMaterialLogIntoLogs;
	private static final String INSERT_MATERIALLOG_INTO_MATERIALLOG = " INSERT INTO MaterialLogs(Quantity, ProductNo, LogId) VALUES (?, ?, ?); ";
	private PreparedStatement insertMaterialLogIntoMaterialLogs;
	
	public MaterialLogDB() throws DataAccessException {
		try {
		insertMaterialLogIntoLogs = DBConnection.getInstance().getConnection()
				.prepareStatement(INSERT_MATERIALLOG_INTO_LOG,java.sql.Statement.RETURN_GENERATED_KEYS);
		insertMaterialLogIntoMaterialLogs = DBConnection.getInstance().getConnection()
				.prepareStatement(INSERT_MATERIALLOG_INTO_MATERIALLOG);
		} catch (SQLException e) {
			throw new DataAccessException("Could not prepare Statement", e);
		}
		
	}

	@Override
	public void saveMaterialLog(MaterialLog materialLog, int orderId) throws DataAccessException {
		try {
			//TRANSACTION? 
			//Inserts data for the Logs table
			insertMaterialLogIntoLogs.setInt(1, orderId);
			int EmployeeId = materialLog.getEmployee().getEmployeeId();
			insertMaterialLogIntoLogs.setInt(2, EmployeeId);
			Timestamp hourLogTime = Timestamp.valueOf(materialLog.getTimeStamp());
			insertMaterialLogIntoLogs.setTimestamp(3, hourLogTime);
			
			//Runs the update and returns LogId from new Log, to be used in MaterialsLogs
			insertMaterialLogIntoLogs.executeUpdate();
			ResultSet generatedLogId = insertMaterialLogIntoLogs.getGeneratedKeys();
			
			//If the insert was successfull Insert MaterialLogs into MaterialLogs table, with generated LogId
				if (generatedLogId.next()) {
					int materialLogKey = generatedLogId.getInt(1);
					insertMaterialLogIntoLogs.close();
					
					int quantity = materialLog.getQuantity();
					insertMaterialLogIntoMaterialLogs.setInt(1, quantity);
					int productNo = materialLog.getMaterial().getProductNo();
					insertMaterialLogIntoMaterialLogs.setInt(2, productNo);
					insertMaterialLogIntoMaterialLogs.setInt(3, materialLogKey);
					insertMaterialLogIntoMaterialLogs.executeUpdate();
				}
		} catch (SQLException e) {
			throw new DataAccessException("MaterialLog could not be saved", e);
		}
	}

}

//}