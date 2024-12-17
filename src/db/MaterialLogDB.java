package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import controller.DataAccessException;
import model.Employee;
import model.GenericMaterial;
import model.Material;
import model.MaterialDescription;
import model.MaterialLog;
import model.StockMaterial;

public class MaterialLogDB  implements MaterialLogDBIF {

	private static final String INSERT_MATERIALLOG_INTO_LOG = " INSERT INTO Logs (OrderNo, EmployeeId, LogTimeStamp) VALUES (?, ?, ?); ";
	private PreparedStatement insertMaterialLogIntoLogs;
	private static final String INSERT_MATERIALLOG_INTO_MATERIALLOG = " INSERT INTO MaterialLogs(Quantity, MaterialNo, LogId) VALUES (?, ?, ?); ";
	private PreparedStatement insertMaterialLogIntoMaterialLogs;
	private static final String UPDATE_MATERIALQUANTITY = "UPDATE StockMaterial SET Quantity = ? WHERE MaterialNo = ? ";
	private PreparedStatement updateMaterialQuantity;
	private static final String SELECT_MATERIALLOG_BY_ORDERNO = "SELECT * FROM Logs, MaterialLogs WHERE Logs.OrderNo = ? AND MaterialLogs.LogId = Logs.LogId";
	private PreparedStatement findMaterialLogByOrderNo;
	
	private MaterialDB materialDB;
	
	public MaterialLogDB() throws DataAccessException {
		try {
			MaterialDB materialDB = new MaterialDB();
		insertMaterialLogIntoLogs = DBConnection.getInstance().getConnection()
				.prepareStatement(INSERT_MATERIALLOG_INTO_LOG,java.sql.Statement.RETURN_GENERATED_KEYS);
		insertMaterialLogIntoMaterialLogs = DBConnection.getInstance().getConnection()
				.prepareStatement(INSERT_MATERIALLOG_INTO_MATERIALLOG);
		updateMaterialQuantity = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_MATERIALQUANTITY);
		findMaterialLogByOrderNo = DBConnection.getInstance().getConnection().prepareStatement(SELECT_MATERIALLOG_BY_ORDERNO);
		
		} catch (SQLException e) {
			throw new DataAccessException("Could not prepare Statement", e);
		}
		
	}

	@Override
	public int saveMaterialLog(MaterialLog materialLog, int orderNo) throws DataAccessException {
		try {
			//TODO:TRANSACTION? 
			//Inserts data for the Logs table
			int materialLogKey = -1;
			insertMaterialLogIntoLogs.setInt(1, orderNo);
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
					
					int quantity = materialLog.getQuantity();
					insertMaterialLogIntoMaterialLogs.setInt(1, quantity);
					int productNo = materialLog.getMaterial().getMaterialNo();
					insertMaterialLogIntoMaterialLogs.setInt(2, productNo);
					insertMaterialLogIntoMaterialLogs.setInt(3, materialLogKey);
					insertMaterialLogIntoMaterialLogs.executeUpdate();
					
					updateMaterialQuantity(materialLog);
					addPricesFromGenericMaterial(materialLog);
					addNewMaterialDescription(materialLog);
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
			
			materialDB.insertNewPurchasePrice(materialLog.getMaterial().getMaterialNo(),materialLog.getMaterial().getLastestPurchasePrice());
			materialDB.insertNewSalesPrice(materialLog.getMaterial().getMaterialNo(),materialLog.getMaterial().getLastestSalesPrice());
		}
		
	}
	
	public ArrayList<MaterialLog> findMaterialLogsByOrderNo(int orderNo) throws DataAccessException{
			ArrayList<MaterialLog> MaterialLogs = new ArrayList<>();
			
		try {
			findMaterialLogByOrderNo.setInt(1, orderNo);
			ResultSet resultSet = findMaterialLogByOrderNo.executeQuery();
			
			while(resultSet.next()) {
				MaterialLog foundMaterialLog = buildObject(resultSet, false);
				MaterialLogs.add(foundMaterialLog);
			}
			
		} catch(SQLException e) {
			throw new DataAccessException("MaterialLogs not found", e);
		}
		
		return MaterialLogs;
	}

	private MaterialLog buildObject(ResultSet resultSet, boolean fullAssociation) throws DataAccessException {
		MaterialLog materialLog = null; 
		MaterialDB materialDB = new MaterialDB();
		try {
			Material material = materialDB.findMaterialByMaterialNo(resultSet.getInt("MaterialNo"));
			Employee employee = new Employee();
			employee.setEmployeeId(resultSet.getInt("EmployeeId"));
			int quantity = resultSet.getInt("Quantity");
			LocalDateTime madeAtTime = resultSet.getTimestamp("LogTimeStamp").toLocalDateTime();;
			materialLog = new MaterialLog(employee, material, quantity,madeAtTime);
		
		} catch (SQLException e) {
			throw new DataAccessException("Could not build object", e);
		}
		return materialLog;
	}
public void addNewMaterialDescription(MaterialLog materialLog) throws DataAccessException, SQLException {
	if(materialLog.getMaterial() instanceof GenericMaterial) {
		MaterialDB materialDB = new MaterialDB();
		int materialNo = materialLog.getMaterial().getMaterialNo();
		MaterialDescription tempDescription = new MaterialDescription(materialLog.getMaterial().getMaterialDescription().getDescription(),materialLog.getTimeStamp());
		materialDB.insertNewMaterialDescription(materialNo, tempDescription);
	}
	
}
}

