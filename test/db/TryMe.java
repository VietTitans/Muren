package db;

import java.math.BigDecimal;
import java.time.LocalDate;

import controller.DataAccessException;
import model.Employee;
import model.Material;
import model.MaterialDescription;
import model.MaterialLog;
import model.Price;

public class TryMe {

	public static void main(String[] args) throws DataAccessException {
		Employee emp = new Employee();
		emp.setEmployeeId(1);
		Price price = new Price(BigDecimal.valueOf(1.00));
		MaterialDescription matdes = new MaterialDescription("Ting");
		Material mat =  new Material (1, "ds", matdes , price, price);
		MaterialLog mlog = new MaterialLog(emp,mat,1);
		
		MaterialLogDB db = new MaterialLogDB();
		db.saveMaterialLog(mlog, 1);
		
	}

}
