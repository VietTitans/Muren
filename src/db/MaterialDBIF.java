package db;

import java.sql.SQLException;

import controller.DataAccessException;
import model.Material;
import model.MaterialDescription;
import model.Price;

public interface MaterialDBIF {

	Material findMaterialByMaterialNo(int materialNo) throws DataAccessException;
	
	void insertNewSalesPrice(int materialNo, Price newPrice) throws SQLException, DataAccessException;
	
	void insertNewPurchasePrice(int materialNo, Price newPrice) throws SQLException, DataAccessException;
	
	void insertNewMaterialDescription(int materialNo, MaterialDescription materialDescription) throws SQLException, DataAccessException;
	
}
