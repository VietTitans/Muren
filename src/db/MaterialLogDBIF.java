package db;
import model.MaterialLog;

public interface MaterialLogDBIF {
	MaterialLog saveMaterialLog(MaterialLog materialLog, int orderId);
}
