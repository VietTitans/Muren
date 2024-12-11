package db;

import model.Material;
import model.Price;
import model.StockMaterial;
import model.GenericMaterial;
import model.StockReservation;
import model.MaterialDescription;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import controller.DataAccessException;

public class MaterialDB implements MaterialDBIF {

	private static final String PS_SELECT_FROM_MATERIAL = "SELECT StockMaterial.*, GenericMaterial.*, Material.ProductName\r\n"
			+ "FROM Material\r\n"
			+ "FULL JOIN StockMaterial ON Material.MaterialNo = StockMaterial.MaterialNo\r\n"
			+ "FULL JOIN GenericMaterial ON Material.MaterialNo = GenericMaterial.MaterialNo\r\n"
			+ "WHERE Material.MaterialNo = ? ;"; 
	private static final String PS_SELECT_FROM_MATERIAL_DESCRIPTION = " SELECT * FROM MaterialDescription WHERE MaterialNo = ?\r\n"
			+ "ORDER BY MaterialDescriptionTimeStamp DESC;";
	private static final String PS_SELECT_FROM_SALES_PRICE = " SELECT * FROM SalesPrice WHERE MaterialNo = ?\r\n"
			+ "ORDER BY SalesPriceTimeStamp DESC;";
	private static final String PS_SELECT_FROM_PURCHASE_PRICE = " SELECT * FROM PurchasePrice WHERE MaterialNo = ?\r\n"
			+ "ORDER BY PurchasePriceTimeStamp DESC;";
	private static final String PS_SELECT_FROM_STOCK_RESERVATION = "Select * FROM StockReservation WHERE StockMaterialId = ?;";
	private static final String PS_INSERT_INTO_MATERIAL_DESCRIPTION = "INSERT INTO MaterialDescription (Description, MaterialDescriptionTimeStamp, MaterialNo) "
			+ "VALUES (?, ?, ?);";
	private static final String PS_INSERT_INTO_SALES_PRICE ="INSERT INTO SalesPrice (Price, SalesPriceTimeStamp, MaterialNo) VALUES (?, ?, ?);";
	private static final String PS_INSERT_INTO_PURCHASE_PRICE ="INSERT INTO PurchasePrice (Price, PurchasePriceTimeStamp, MaterialNo) VALUES (?, ?, ?);";
	
	private PreparedStatement psSelectMaterialNoMaterial;
	private PreparedStatement psSelectMaterialNoMaterialDescription;
	private PreparedStatement psSelectMaterialNoSalesPrice;
	private PreparedStatement psSelectMaterialNoPurchasePrice;
	private PreparedStatement psSelectStockMaterialIdStockReservation;
	private PreparedStatement psInsertIntoMaterialDescription;
	private PreparedStatement psInsertIntoSalesPrice;
	private PreparedStatement psInsertIntoPurchasePrice;
	
	private Connection connection;
	public MaterialDB() throws DataAccessException {
		connection = DBConnection.getInstance().getConnection();
		try {
		initPreparedStatements();
		}
		catch (DataAccessException e){
			throw new DataAccessException("Statement could not be prepared", e);
		}
	} 
	
	private void initPreparedStatements() throws DataAccessException{
		try {
			psSelectMaterialNoMaterial = connection.prepareStatement(PS_SELECT_FROM_MATERIAL);
			psSelectMaterialNoMaterialDescription = connection.prepareStatement(PS_SELECT_FROM_MATERIAL_DESCRIPTION);
			psSelectMaterialNoSalesPrice = connection.prepareStatement(PS_SELECT_FROM_SALES_PRICE);
			psSelectMaterialNoPurchasePrice = connection.prepareStatement(PS_SELECT_FROM_PURCHASE_PRICE);
			psSelectStockMaterialIdStockReservation = connection.prepareStatement(PS_SELECT_FROM_STOCK_RESERVATION);
			psInsertIntoMaterialDescription = connection.prepareStatement(PS_INSERT_INTO_MATERIAL_DESCRIPTION);
			psInsertIntoSalesPrice = connection.prepareStatement(PS_INSERT_INTO_SALES_PRICE);
			psInsertIntoPurchasePrice = connection.prepareStatement(PS_INSERT_INTO_PURCHASE_PRICE);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("Could not prepare statement", e);
		}
	}
	@Override
	public Material findMaterialByMaterialNo(int materialNo) throws DataAccessException {
		Material foundMaterial = null;
		MaterialDescription materialDescription = null;
		ArrayList<Price> salesPrices = new ArrayList<>();
		ArrayList<Price> purchasePrices = new ArrayList<>();
		ArrayList<MaterialDescription> materialDescriptions = new ArrayList<>();
		try {
			psSelectMaterialNoMaterial.setInt(1,materialNo);
			ResultSet rsMaterial = psSelectMaterialNoMaterial.executeQuery();
			
			psSelectMaterialNoMaterialDescription.setInt(1,materialNo);
			ResultSet rsDescription = psSelectMaterialNoMaterialDescription.executeQuery();
			
			psSelectMaterialNoSalesPrice.setInt(1,materialNo);
			ResultSet rsSalesPrice = psSelectMaterialNoSalesPrice.executeQuery();
			
			psSelectMaterialNoPurchasePrice.setInt(1,materialNo);
			ResultSet rsPurchasePrice = psSelectMaterialNoPurchasePrice.executeQuery();
			
			if(rsMaterial.next()) {
				if(rsSalesPrice.isBeforeFirst()) {	
					while(rsSalesPrice.next()) {
						Price salesPrice = buildObjectSalesPrice(rsSalesPrice);
						salesPrices.add(salesPrice);					
					}
				}
				if(rsPurchasePrice.isBeforeFirst()) {
					while(rsPurchasePrice.next()) {
						Price purchasePrice = buildObjectPurchasePrice(rsPurchasePrice);
						purchasePrices.add(purchasePrice);					
					}
				} 
				
				if(rsDescription.isBeforeFirst()) {
					while(rsDescription.next()) {
					materialDescription = buildObjectMaterialDescription(rsDescription);
					materialDescriptions.add(materialDescription);
					}
				}
				if(rsMaterial.getString("GenericMaterialId") != null && salesPrices != null && purchasePrices != null) {
					foundMaterial = buildObjectGenericMaterial(rsMaterial, materialDescriptions, salesPrices, purchasePrices);
					
				} else if(rsMaterial.getString("StockMaterialId") != null && salesPrices != null && purchasePrices != null) {
					int stockMaterialId = rsMaterial.getInt("StockMaterialId");
					foundMaterial = buildObjectStockMaterial(rsMaterial, materialDescriptions, salesPrices, purchasePrices, stockMaterialId);
					
				} else {
					System.out.println("Cant run methods");
				}
			}	
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("Cant find material", e);
		}
		
		
		return foundMaterial;
	}
	
	@Override
	public void insertNewSalesPrice(int materialNo, Price newPrice) throws SQLException, DataAccessException {
		try {
			BigDecimal value = newPrice.getPreVATValue();
			psInsertIntoSalesPrice.setBigDecimal(1, value);
			Timestamp timeStamp = Timestamp.valueOf(newPrice.getTimeStamp());
			psInsertIntoSalesPrice.setTimestamp(2,timeStamp);
			psInsertIntoSalesPrice.setInt(3,materialNo);
			psInsertIntoSalesPrice.executeUpdate();
		} catch (SQLException e) {
	        throw new DataAccessException("Error inserting sales price", e);
	    }
	}
	
	@Override
	public void insertNewPurchasePrice(int materialNo, Price newPrice) throws SQLException, DataAccessException {
		try {
			if(newPrice != null) {
			BigDecimal adjustedValue = newPrice.getPreVATValue();
			psInsertIntoPurchasePrice.setBigDecimal(1, adjustedValue);
			Timestamp timeStamp = Timestamp.valueOf(newPrice.getTimeStamp());
			psInsertIntoPurchasePrice.setTimestamp(2,timeStamp);
			psInsertIntoPurchasePrice.setInt(3,materialNo);
			psInsertIntoPurchasePrice.executeUpdate();
			}
		} catch (SQLException e) {
	        throw new DataAccessException("Error inserting purchase price", e);
	    }
	}
	
	@Override
	public void insertNewMaterialDescription(int materialNo, MaterialDescription materialDescription) throws SQLException, DataAccessException {
		try {
			psInsertIntoMaterialDescription.setString(1, materialDescription.getDescription());
			psInsertIntoMaterialDescription.setTimestamp(2,Timestamp.valueOf(materialDescription.getTimeStamp()));
			psInsertIntoMaterialDescription.setInt(3,materialNo);
			psInsertIntoMaterialDescription.executeUpdate();
		} catch (SQLException e) {
	        throw new DataAccessException("Error inserting material description", e);
	    }
	}
	

	private MaterialDescription buildObjectMaterialDescription(ResultSet rs) throws DataAccessException {
		MaterialDescription materialDescription = null;
		try {
			Timestamp timeStamp = rs.getTimestamp("MaterialDescriptionTimeStamp");
			LocalDateTime date = timeStamp.toLocalDateTime();
			String description = rs.getString("Description");
			materialDescription = new MaterialDescription(date, description);
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("Cant create MaterialDescription", e);
		}
		return materialDescription;
	}

	
	private StockMaterial buildObjectStockMaterial(ResultSet rs, ArrayList<MaterialDescription> materialDescriptions, ArrayList<Price> salesPrices, 
															ArrayList<Price> purchasePrices, int stockMaterialId) throws DataAccessException {
		StockMaterial stockMaterial = null;
		try {
			stockMaterial = new StockMaterial(rs.getInt("MaterialNo"), rs.getString("ProductName"), materialDescriptions,
														salesPrices, purchasePrices, rs.getInt("MinStock"), rs.getInt("MaxStock"), rs.getInt("Quantity"));	
			
			psSelectStockMaterialIdStockReservation.setInt(1,stockMaterialId);
			ResultSet rsStockReservation = psSelectStockMaterialIdStockReservation.executeQuery();
			
			if(rsStockReservation.isBeforeFirst()) {
				while(rsStockReservation.next()) {
					StockReservation stockReservation = buildObjectStockReservation(rsStockReservation);
					stockMaterial.addStockReservation(stockReservation);
				}
			} else {
				System.out.println("Cant use method");
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("Cant create StockMaterial", e);
		}
		
		return stockMaterial;
	}
	

	private GenericMaterial buildObjectGenericMaterial(ResultSet rs, ArrayList<MaterialDescription> materialDescriptions, 
																ArrayList<Price> salesPrices, ArrayList<Price> purchasePrices) throws DataAccessException {
		GenericMaterial genericMaterial = null;
		try {
			genericMaterial = new GenericMaterial(rs.getInt(7), rs.getString("ProductName"), materialDescriptions, 
															salesPrices, purchasePrices, rs.getString("ProductType"));
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("Cant create GenericMaterial", e);
		}
		return genericMaterial;
	}

	private Price buildObjectPurchasePrice(ResultSet rs) throws DataAccessException, SQLException {
		Price price = null;
			try {
				Timestamp timeStamp = rs.getTimestamp("PurchasePriceTimeStamp");
				LocalDateTime date = timeStamp.toLocalDateTime();
				BigDecimal preVATValue = rs.getBigDecimal("Price");
				price = new Price(date, preVATValue);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DataAccessException("Cant find purchasePrice", e);
			}
		 
		return price;
	}

	private Price buildObjectSalesPrice(ResultSet rs) throws SQLException, DataAccessException {
		Price price = null;		
			try {
			Timestamp timeStamp = rs.getTimestamp("SalesPriceTimeStamp");
			LocalDateTime date = timeStamp.toLocalDateTime();
			BigDecimal preVATValue = rs.getBigDecimal("Price");
			price = new Price(date, preVATValue);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DataAccessException("Cant find salesPrice", e);
			}
		
		return price;
	}

	private StockReservation buildObjectStockReservation(ResultSet rs) throws DataAccessException {
		StockReservation stockReservation = null;
		try {
			Timestamp timeStamp = rs.getTimestamp("ReservationDate");
			LocalDateTime date = timeStamp.toLocalDateTime();
			stockReservation = new StockReservation(rs.getInt("Quantity"), date);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("Cant find StockReservationPrice", e);
		}
		return stockReservation;
	}

	
}
