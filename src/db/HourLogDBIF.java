package db;

import controller.DataAccessException;
import model.HourLog;

public interface HourLogDBIF {
	int saveHourLog(HourLog hourLog, int orderId) throws DataAccessException;

}
