package db;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.DataAccessException;
import model.Employee;
import model.Material;
import model.MaterialDescription;
import model.MaterialLog;
import model.Price;
import model.StockMaterial;

class TestMaterialLogDB {

	static MaterialLogDB materialLogDB;

	@BeforeEach
	void setUp() throws Exception {
	}


	
	@BeforeEach
	public void initEach() throws DataAccessException {
		ResetDB.main(null);
		materialLogDB = new MaterialLogDB();
	}
	
	
	@Test
	void testSaveMaterialLog() throws DataAccessException {
		//Arrange
		Employee employee = new Employee();
		employee.setEmployeeId(1);
		int expectedLogId = 6;
		ArrayList<MaterialDescription> materialDescriptions = new ArrayList<>();
		ArrayList<Price> salesPrices = new ArrayList<>();
		ArrayList<Price> purchasePrices = new ArrayList<>();

		StockMaterial stockMaterial = new StockMaterial(1001 , "Spand", materialDescriptions, salesPrices, purchasePrices, 1, 5, 2);
		int orderNo = 1; 
		MaterialLog expectedResult = new MaterialLog(employee, stockMaterial, orderNo);
		//Act
		int returnedKey = materialLogDB.saveMaterialLog(expectedResult, orderNo);
		//Assert		
		assertEquals(expectedLogId, returnedKey);
	}
	
	@Test
	void testUpdateQuantity() throws DataAccessException {
		//Arrange
		StockMaterial material = new StockMaterial(0, null, null, null, null, 0, 0, 0);
		material.setQuantity(50);
		Employee employee = new Employee();
		MaterialLog materialLog = new MaterialLog(employee, material, 20);
		//Act
		//Assert
		int expectedResult = 30;
		StockMaterial materialFromLog = (StockMaterial) material;
		int result = materialFromLog.getQuantity();
		assertEquals(expectedResult, result);
		
	}
	@Test
	void testSaveMaterialLogWithGenericMaterial() throws DataAccessException, InterruptedException{
		//Sleeps for 1000 milisekunder otherwise the MaterialLog is insert with dublicate key (Same timeStamp) as testData
		Thread.sleep(1000);
		MaterialDB materialDB = new MaterialDB();
		Employee employee = new Employee();
		employee.setEmployeeId(1);
		
		ArrayList<MaterialDescription> materialDescriptions = new ArrayList<>();
		MaterialDescription newMaterialDescription = new MaterialDescription(LocalDateTime.of(2024, 11, 10, 12, 0), "This is a test");
		materialDescriptions.add(newMaterialDescription);
		
		Material material = materialDB.findMaterialByMaterialNo(1003);
		material.setCurrentMaterialDescription(newMaterialDescription);
		Price salesPrice = new Price(BigDecimal.valueOf(100));
		Price purchasePrice = new Price(BigDecimal.valueOf(110));
		material.getPurchasePrices().add(purchasePrice);
		material.getSalesPrices().add(salesPrice);
		MaterialLog materialLog = new MaterialLog(employee, material ,1);
		materialLogDB.saveMaterialLog(materialLog, 2);		
	}

}
