package db;
import controller.DataAccessException;
import model.MaterialLog;

public interface MaterialLogDBIF {
	void saveMaterialLog(MaterialLog materialLog, int orderId) throws DataAccessException;
}
