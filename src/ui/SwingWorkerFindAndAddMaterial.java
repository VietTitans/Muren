package ui;


import javax.swing.SwingWorker;

import controller.DataAccessException;
import controller.OrderController;
import model.Employee;
import model.Material;



public class SwingWorkerFindAndAddMaterial extends SwingWorker<Object, Object> {
	private OrderController currentOrderController;
	private int materialNo;
	private Employee placeHolderEmployee;
	private int quantity;



	public SwingWorkerFindAndAddMaterial(OrderController currentOrderController, int materialNo,
			Employee placeHolderEmployee, int quantity) {
		super();
		this.currentOrderController = currentOrderController;
		this.materialNo = materialNo;
		this.placeHolderEmployee = placeHolderEmployee;
		this.quantity = quantity;
	}
	@Override
	protected Material doInBackground() throws Exception {
		return findMaterial();
	}
	public Material findMaterial() throws DataAccessException {
	
	
		Material material = currentOrderController.findAndAddMaterialByMaterialNo(placeHolderEmployee,materialNo,quantity);
			

	return material;

}
}
