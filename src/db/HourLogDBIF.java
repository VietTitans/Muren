package db;

import controller.DataAccessException;
import model.HourLog;

public interface HourLogDBIF {
	void saveHourLog(HourLog hourLog, int orderId) throws DataAccessException;

}
