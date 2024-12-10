package db;
import controller.DataAccessException;
import model.MaterialLog;

public interface MaterialLogDBIF {
	int saveMaterialLog(MaterialLog materialLog, int orderId) throws DataAccessException;
}
