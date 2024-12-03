package db;

import model.Order;

public interface OrderDBIF {

	int saveOrder(Order currentOrder);

}
