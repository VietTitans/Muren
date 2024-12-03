package db;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import controller.DataAccessException;
import model.HourLog;
import model.MaterialLog;

public class MaterialLogDB implements MaterialLogDBIF {

	private static final String INSERT_MATERIALLOG_INTO_LOG = " INSERT INTO Logs (OrderNo, EmployeeId, LogTimeStamp) VALUES (?, ?, ?);";
	private PreparedStatement insertMaterialLogIntoLogs;
	private static final String INSERT_MATERIALLOG_INTO_MATERIALLOG = "INSERT INTO MaterialLogs(Quantity, ProductNo, LogId) VALUES (?, ?, ?);";
	private PreparedStatement insertMaterialLogIntoMaterialLogs;
	
	public MaterialLogDB() throws DataAccessException {
		// TODO Auto-generated constructor stub
		try {
		insertMaterialLogIntoLogs = DBConnection.getInstance().getConnection()
				.prepareStatement(INSERT_MATERIALLOG_INTO_LOG);
		insertMaterialLogIntoMaterialLogs = DBConnection.getInstance().getConnection()
				.prepareStatement(INSERT_MATERIALLOG_INTO_MATERIALLOG);
		} catch (SQLException e) {
			throw new DataAccessException("Could not prepare Statement", e);
		}
		
	}

	@Override
	public MaterialLog saveMaterialLog(MaterialLog materialLog, int orderId) throws DataAccessException {
		try {
			insertMaterialLogIntoLogs.setInt(1, orderId);
			int EmployeeId = materialLog.getEmployee().getEmployeeId();
			insertMaterialLogIntoLogs.setInt(2, EmployeeId);
			
			Timestamp hourLogTime = Timestamp.valueOf(materialLog.getTimeStamp());
			insertMaterialLogIntoLogs.setTimestamp(3, hourLogTime);
			insertMaterialLogIntoLogs.executeUpdate();
			
			ResultSet generatedLogId = insertMaterialLogIntoLogs.getGeneratedKeys();
				if (generatedLogId.next()) {
					int materialLogKey = generatedLogId.getInt("LogId");
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
		return materialLog;
	}

}

//}