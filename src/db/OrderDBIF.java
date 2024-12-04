package db;

import controller.DataAccessException;
import model.Order;

public interface OrderDBIF {

	int saveOrder(Order currentOrder) throws DataAccessException;

}
