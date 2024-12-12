package db;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.math.BigDecimal;


import controller.DataAccessException;
import model.Employee;
import model.HourLog;
import model.MaterialLog;

public class HourLogDB implements HourLogDBIF {

	private static final String INSERT_HOURLOG_INTO_LOG = " INSERT INTO Logs (OrderNo, EmployeeId, LogTimeStamp) VALUES (?, ?, ?);";
	private PreparedStatement insertHourLogIntoLogs;
	private static final String INSERT_HOURLOG_INTO_HOURLOG = "INSERT INTO HourLogs(HoursWorked, LogId) VALUES (?, ?);";
	private PreparedStatement insertHourLogIntoHourLogs;
	private static final String SELECT_HOURlOGS_FROM_ORDERNO = " SELECT * FROM Logs,HourLogs  WHERE Logs.OrderNo = ? AND HourLogs.LogId = Logs.LogId";
	private PreparedStatement selectHourLogsFromOrderNo;
	public HourLogDB() throws DataAccessException {
		try {
	insertHourLogIntoLogs = DBConnection.getInstance().getConnection()
			.prepareStatement(INSERT_HOURLOG_INTO_LOG,java.sql.Statement.RETURN_GENERATED_KEYS);
	insertHourLogIntoHourLogs = DBConnection.getInstance().getConnection()
			.prepareStatement(INSERT_HOURLOG_INTO_HOURLOG);
	selectHourLogsFromOrderNo = DBConnection.getInstance().getConnection().prepareStatement(SELECT_HOURlOGS_FROM_ORDERNO);
	}
	catch (SQLException e) {
		throw new DataAccessException("Statement Could Not Be Prepared", e);
	}
	}

	@Override
	public int saveHourLog(HourLog hourLog, int orderId) throws DataAccessException {
		int hourLogKey = -1;
		try {
			insertHourLogIntoLogs.setInt(1, orderId);
			int EmployeeId = hourLog.getEmployee().getEmployeeId();
			insertHourLogIntoLogs.setInt(2, EmployeeId);
			
			Timestamp hourLogTime = Timestamp.valueOf(hourLog.getTimeStamp());
			insertHourLogIntoLogs.setTimestamp(3, hourLogTime);
			insertHourLogIntoLogs.executeUpdate();
			
			ResultSet generatedLogId = insertHourLogIntoLogs.getGeneratedKeys();
				if (generatedLogId.next()) {
					hourLogKey = generatedLogId.getInt(1);
					insertHourLogIntoHourLogs.setBigDecimal(1, hourLog.getHoursWorked());
					insertHourLogIntoLogs.close();
					insertHourLogIntoHourLogs.setInt(2, hourLogKey);
					insertHourLogIntoHourLogs.executeUpdate();
				}
		
		} catch (SQLException e){
			throw new DataAccessException("HourLog Could Not Be Saved", e);
		}
		return hourLogKey;
	}
	public ArrayList<HourLog> findHourLogsByOrderNo(int orderNo) throws DataAccessException{
		
		ArrayList<HourLog> hourLogs = new ArrayList<HourLog>();
		try {
			selectHourLogsFromOrderNo.setInt(1, orderNo);
			ResultSet resultSet = selectHourLogsFromOrderNo.executeQuery();
			
			while(resultSet.next()) {
				HourLog foundHourLog = buildObject(resultSet);
				hourLogs.add(foundHourLog);
				
			}
			
		} catch(SQLException e) {
			throw new DataAccessException("MaterialLogs not found", e);
		}
		
		return hourLogs;
	}
public HourLog buildObject(ResultSet resultSet) throws SQLException {
	
	Employee employee = new Employee();
	employee.setEmployeeId(resultSet.getInt("EmployeeId"));
	BigDecimal hours = resultSet.getBigDecimal("HoursWorked");
	HourLog hourLog = new HourLog(employee, hours); 
	
	
	
	return hourLog;
	
}
}

