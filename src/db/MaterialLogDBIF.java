package db;
import controller.DataAccessException;
import model.MaterialLog;

public interface MaterialLogDBIF {
	MaterialLog saveMaterialLog(MaterialLog materialLog, int orderId) throws DataAccessException;
}
