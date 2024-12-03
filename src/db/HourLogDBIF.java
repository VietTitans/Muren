package db;

import model.HourLog;

public interface HourLogDBIF {
	HourLog saveHourLog(HourLog hourLog, int orderId);
}
