package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.DataAccessException;
import model.HourLog;
import model.MaterialLog;
import model.Order;

public class OrderDB implements OrderDBIF {
	private static final String INSERT_ORDER_INTO_DATABASE = " INSERT INTO ORDERS (StartDate, Deadline, EmployeeId, CustomerNo, IsFinished) VALUES (?, ?, ?, ?, ?);";
	private PreparedStatement insertIntoDatabase;
	private MaterialLogDB materialLogDB;
	private HourLogDB hourLogDB;

	public OrderDB() throws DataAccessException, SQLException {
		insertIntoDatabase = DBConnection.getInstance().getConnection().prepareStatement(INSERT_ORDER_INTO_DATABASE,
				java.sql.Statement.RETURN_GENERATED_KEYS);
		materialLogDB = new MaterialLogDB();
		hourLogDB = new HourLogDB();
	}

	@Override
	public int saveOrder(Order currentOrder) throws DataAccessException {
		DBConnection.getInstance().startTransaction();
		
		int orderIdReturned = -1;
		// Sets up values to be used in prepared statement
		java.sql.Date startDate = java.sql.Date.valueOf(currentOrder.getStartDate());
		java.sql.Date deadline = java.sql.Date.valueOf(currentOrder.getDeadLine());
		int employeeId = currentOrder.getOrderMadeBy().getEmployeeId();
		int customerNo = currentOrder.getCustomer().getCustomerId();
		boolean isFinished = currentOrder.isFinished();
		int isFinishedAsBit;
		if (isFinished) {
			isFinishedAsBit = 1;
		} else {
			isFinishedAsBit = 0;

		}
		try {
			DBConnection.getInstance().startTransaction();

			// Inserts values into prepared statement
			insertIntoDatabase.setDate(1, startDate);
			insertIntoDatabase.setDate(2, deadline);
			insertIntoDatabase.setInt(3, employeeId);
			insertIntoDatabase.setInt(4, customerNo);
			insertIntoDatabase.setInt(5, isFinishedAsBit);

			// Runs the prepared statement
			insertIntoDatabase.executeUpdate();

			// Gets the auto generated key from DB, So it can be used for Logs
			ResultSet generatedKeys = insertIntoDatabase.getGeneratedKeys();
			if (generatedKeys.next()) {
				orderIdReturned = generatedKeys.getInt(1);
			}
			insertIntoDatabase.close();
			// Saves the material and hour logs to DB by calling their DB classes
			for (MaterialLog materialLog : currentOrder.getMaterialLogs()) {
				materialLogDB.saveMaterialLog(materialLog, orderIdReturned);
			}
			for (HourLog hourLog : currentOrder.getHourLogs()) {
				hourLogDB.saveHourLog(hourLog, orderIdReturned);
			}

			DBConnection.getInstance().commitTransaction();
		} catch (DataAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DBConnection.getInstance().rollbackTransaction();
		}
		return orderIdReturned;
	}

}
