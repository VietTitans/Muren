package db;

import controller.DataAccessException;
import model.HourLog;

public interface HourLogDBIF {
	HourLog saveHourLog(HourLog hourLog, int orderId) throws DataAccessException;

}
