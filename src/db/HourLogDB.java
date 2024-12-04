package db;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.math.BigDecimal;


import controller.DataAccessException;

import model.HourLog;

public class HourLogDB implements HourLogDBIF {

	private static final String INSERT_HOURLOG_INTO_LOG = " INSERT INTO Logs (OrderNo, EmployeeId, LogTimeStamp) VALUES (?, ?, ?);";
	private PreparedStatement insertHourLogIntoLogs;
	private static final String INSERT_HOURLOG_INTO_HOURLOG = "INSERT INTO HourLogs(HoursWorked, LogId) VALUES (?, ?);";
	private PreparedStatement insertHourLogIntoHourLogs;
	
	public HourLogDB() throws DataAccessException {
		try {
	insertHourLogIntoLogs = DBConnection.getInstance().getConnection()
			.prepareStatement(INSERT_HOURLOG_INTO_LOG,java.sql.Statement.RETURN_GENERATED_KEYS);
	insertHourLogIntoHourLogs = DBConnection.getInstance().getConnection()
			.prepareStatement(INSERT_HOURLOG_INTO_HOURLOG);
	}
	catch (SQLException e) {
		throw new DataAccessException("Statement Could Not Be Prepared", e);
	}
	}

	@Override
	public HourLog saveHourLog(HourLog hourLog, int orderId) throws DataAccessException {
		try {
			insertHourLogIntoLogs.setInt(1, orderId);
			int EmployeeId = hourLog.getEmployee().getEmployeeId();
			insertHourLogIntoLogs.setInt(2, EmployeeId);
			
			Timestamp hourLogTime = Timestamp.valueOf(hourLog.getTimeStamp());
			insertHourLogIntoLogs.setTimestamp(3, hourLogTime);
			insertHourLogIntoLogs.executeUpdate();
			
			ResultSet generatedLogId = insertHourLogIntoLogs.getGeneratedKeys();
				if (generatedLogId.next()) {
					int hourLogKey = generatedLogId.getInt(1);
					insertHourLogIntoHourLogs.setBigDecimal(1, hourLog.getHoursWorked());
					
					insertHourLogIntoLogs.close();
					insertHourLogIntoHourLogs.setInt(2, hourLogKey);

					insertHourLogIntoHourLogs.executeUpdate();
				}
		
		} catch (SQLException e){
			throw new DataAccessException("HourLog Could Not be Saved", e);
		}
		return hourLog;
	}

}
