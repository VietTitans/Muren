package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import controller.DataAccessException;
import model.Customer;
import model.Employee;
import model.HourLog;
import model.MaterialLog;
import model.Order;

public class OrderDB implements OrderDBIF {
	private static final String INSERT_ORDER_INTO_DATABASE = " INSERT INTO ORDERS (StartDate, Deadline, EmployeeId, CustomerNo, IsFinished) VALUES (?, ?, ?, ?, ?);";
	private static final String SELECT_ORDER_BY_ORDERNO = "SELECT * FROM Orders WHERE OrderNo = ?";
			
	private PreparedStatement insertIntoDatabase;
	private PreparedStatement selectOrderByOrderNo;
	
	private MaterialLogDB materialLogDB;
	private HourLogDB hourLogDB;

	public OrderDB() throws DataAccessException, SQLException {
		materialLogDB = new MaterialLogDB();
		hourLogDB = new HourLogDB();
		
		insertIntoDatabase = DBConnection.getInstance().getConnection().prepareStatement(INSERT_ORDER_INTO_DATABASE,
				java.sql.Statement.RETURN_GENERATED_KEYS);
		selectOrderByOrderNo = DBConnection.getInstance().getConnection().prepareStatement(SELECT_ORDER_BY_ORDERNO);
				
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
			e.printStackTrace();
			DBConnection.getInstance().rollbackTransaction();
		}
		return orderIdReturned;
	}

	@Override
	public Order findOrderByOrderNo(int orderNo, boolean fullAssociation) throws DataAccessException {
		Order foundOrder = null;
		try {
			selectOrderByOrderNo.setString(1, String.valueOf(orderNo));
			ResultSet resultSet = selectOrderByOrderNo.executeQuery();
			
			if(resultSet.next()) {
				foundOrder = buildObject(resultSet, fullAssociation);
			}
		} catch(SQLException e) {
			throw new DataAccessException("Order not found", e);
		}
		
		return foundOrder;
	}

	private Order buildObject(ResultSet resultSet, boolean fullAssociation) throws DataAccessException{
		Order foundOrder = null;
		Employee employee = new Employee();
		Customer customer = new Customer();
		try {
			employee.setEmployeeId(resultSet.getInt("EmployeeId"));
			foundOrder.setOrderMadeBy(employee);
			customer.setCustomerId(resultSet.getInt("CustomerId"));
			foundOrder.setCustomer(customer);
			foundOrder.setFinished(resultSet.getBoolean("IsFinished"));
			LocalDate startDate = resultSet.getTimestamp("StartDate").toLocalDateTime().toLocalDate();
			foundOrder.setStartDate(startDate);
			LocalDate deadLine = resultSet.getTimestamp("DeadLine").toLocalDateTime().toLocalDate();
			foundOrder.setDeadLine(deadLine);
			
			if(fullAssociation == true) {
				
			}
			
		}catch(SQLException e) {
			throw new DataAccessException("Could Not Build", e);
		}
		return foundOrder;
	}

}
