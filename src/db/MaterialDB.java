package db;

import model.Material;
import model.Price;
import model.StockMaterial;
import model.StockReservation;
import model.MaterialDescription;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import controller.DataAccessException;

public class MaterialDB implements MaterialDBIF{

	private static final String PS_SELECT_FROM_MATERIAL = " SELECT Material.*, StockMaterial.Quantity, StockMaterial.MinStock, StockMaterial.MaxStock, StockMaterial.StockMaterialId\r\n"
			+ "FROM Material\r\n"
			+ "LEFT JOIN StockMaterial ON Material.ProductNo = StockMaterial.ProductNo\r\n"
			+ "WHERE Material.ProductNo = 1001;"; 
	private static final String PS_SELECT_FROM_MATERIAL_DESCRIPTION = " SELECT * FROM MaterialDescription WHERE ProductNo = ?;";
	private static final String PS_SELECT_FROM_SALES_PRICE = " SELECT * FROM SalesPrice WHERE ProductNo = ?;";
	private static final String PS_SELECT_FROM_PURCHASE_PRICE = " SELECT * FROM PurchasePrice WHERE ProductNo = ?;";
	private static final String PS_SELECT_FROM_Stock_Reservation = "Select * FROM StockReservation WHERE StockMaterialId = ?;";
	
	
	private PreparedStatement psSelectProductNoMaterial;
	private PreparedStatement psSelectProductNoMaterialDescription;
	private PreparedStatement psSelectProductNoSalesPrice;
	private PreparedStatement psSelectProductNoPurchasePrice;
	private PreparedStatement psSelectStockMaterialIdStockReservation;
	
	private Connection connection;
	
	
	public MaterialDB() throws DataAccessException {
		connection = DBConnection.getInstance().getConnection();
		initPreparedStatements();
	}
	
	
	private void initPreparedStatements() throws DataAccessException {
		try {
			psSelectProductNoMaterial = connection.prepareStatement(PS_SELECT_FROM_MATERIAL);
			psSelectProductNoMaterialDescription = connection.prepareStatement(PS_SELECT_FROM_MATERIAL_DESCRIPTION);
			psSelectProductNoSalesPrice = connection.prepareStatement(PS_SELECT_FROM_SALES_PRICE);
			psSelectProductNoPurchasePrice = connection.prepareStatement(PS_SELECT_FROM_PURCHASE_PRICE);
			psSelectStockMaterialIdStockReservation = connection.prepareStatement(PS_SELECT_FROM_Stock_Reservation);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("Could not prepare statement", e);
		}
	}
	
	
	public Material findMaterialByProductNo(int productNo, boolean fullAssociation) throws DataAccessException {
		Material material = null;
		try {
			psSelectProductNoMaterial.setInt(1,productNo);
			ResultSet rsMaterial = psSelectProductNoMaterial.executeQuery();
			
			psSelectProductNoMaterialDescription.setInt(1,productNo);
			ResultSet rsDescription = psSelectProductNoMaterialDescription.executeQuery();
			
			psSelectProductNoSalesPrice.setInt(1,productNo);
			ResultSet rsSalesPrice = psSelectProductNoSalesPrice.executeQuery();
			
			psSelectProductNoPurchasePrice.setInt(1,productNo);
			ResultSet rsPurchasePrice = psSelectProductNoPurchasePrice.executeQuery();
			
			if(rsDescription.next() && rsSalesPrice.next() && rsPurchasePrice.next()){
				MaterialDescription materialDescription = buildObjectMaterialDescription(rsDescription, fullAssociation);
				Price salesPrice = buildObjectSalesPrice(rsSalesPrice, fullAssociation);
				Price purchasePrice = buildObjectPurchasePrice(rsSalesPrice, fullAssociation);
				
				material = buildObjectStockMaterial(rsMaterial, fullAssociation, materialDescription, salesPrice, purchasePrice);
				
				StockMaterial stockMaterial = (StockMaterial) material; /*Casts material to a StockMaterial,
				 so the StockMaterial methods become available*/
					
				findAndAddStockReservationByStockMaterialId(rsMaterial.getInt("StockMaterialId"), stockMaterial);
			} else {
				material = buildObjectMaterial(rsMaterial, fullAssociation);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("Cant find material", e);
		}
			
		return material;
	}
	
	private void findAndAddStockReservationByStockMaterialId(int stockMaterialId, StockMaterial stockMaterial) throws SQLException, DataAccessException {
		StockReservation stockReservation = null;
		try {
		psSelectProductNoMaterial.setInt(1,stockMaterialId);
		ResultSet rsStockReservation = psSelectProductNoMaterial.executeQuery();
		
		while(rsStockReservation.next()) {
			stockReservation = buildObjectStockReservation(rsStockReservation, true);
			if(stockReservation != null) {
			stockMaterial.addStockReservation(stockReservation);
			}
		}
		} catch(SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("Cant find StockReservation", e);
		}
	}
	
	
	private Material buildObjectMaterial(ResultSet rs, boolean fullAssociation) throws DataAccessException, SQLException {
		Material material = null;
		
		try {
			String productName = rs.getString("ProductName");
			int productNo = rs.getInt("ProductNo");
			
			material = new Material(productNo, productName);
			
		} catch (SQLException e) {
			throw new DataAccessException("Cannot convert from ResultSet", e);
		}
		
		return material;
	}
	
	
	private Material buildObjectStockMaterial(ResultSet rs, boolean fullAssociation, MaterialDescription materialDescription, Price salesPrice, Price purchasePrice) throws DataAccessException, SQLException {
		Material material = null;
		
		try {
			String productName = rs.getString("ProductName");
			int productNo = rs.getInt("ProductNo");
			int minStock = rs.getInt("MinStock");
			int maxStock = rs.getInt("MaxStock");
			int quantity = rs.getInt("quantity");
			
			material = new StockMaterial(productNo, productName, materialDescription, salesPrice, purchasePrice, minStock, maxStock, quantity);
			
		} catch (SQLException e) {
			throw new DataAccessException("Cannot convert from ResultSet", e);
		}
		
		return material;
	}
	
	
	private MaterialDescription buildObjectMaterialDescription(ResultSet rs, boolean fullAssociation) throws DataAccessException, SQLException {
		MaterialDescription materialDescription = null;
		
		try {
		String description = rs.getString("Description");
		Timestamp timeStamp = rs.getTimestamp("MaterialDescriptionTimeStamp");
		LocalDateTime date = timeStamp.toLocalDateTime();
		
		materialDescription = new MaterialDescription(date, description);
		
		}catch (SQLException e) {
			throw new DataAccessException("Cannot convert from ResultSet", e);
		}
		return materialDescription;
	}
	
	
	
	private Price buildObjectSalesPrice(ResultSet rs, boolean fullAssociation) throws DataAccessException, SQLException {
		Price salesPrice = null;
		
		try {
		BigDecimal value = rs.getBigDecimal("Price");
		Timestamp timeStamp = rs.getTimestamp("SalesPriceTimeStamp");
		LocalDateTime date = timeStamp.toLocalDateTime();
		
		salesPrice = new Price(date, value);
		
		}catch (SQLException e) {
			throw new DataAccessException("Cannot convert from ResultSet", e);
		}
		return salesPrice;
	}
	
	
	private Price buildObjectPurchasePrice(ResultSet rs, boolean fullAssociation) throws DataAccessException, SQLException {
		Price purchasePrice = null;
		
		try {
		BigDecimal value = rs.getBigDecimal("Price");
		Timestamp timeStamp = rs.getTimestamp("PurchasePriceTimeStamp");
		LocalDateTime date = timeStamp.toLocalDateTime();
		
		purchasePrice = new Price(date, value);
		
		}catch (SQLException e) {
			throw new DataAccessException("Cannot convert from ResultSet", e);
		}
		return purchasePrice;
	}
	
	
	
	public StockReservation buildObjectStockReservation(ResultSet rs, boolean fullAssociation) throws SQLException {
		StockReservation stockReservation = null;
		
		int quantity = rs.getInt("Quantity");
		Timestamp timeStamp = rs.getTimestamp("ReservationDate");
		LocalDateTime date = timeStamp.toLocalDateTime();
		
		stockReservation = new StockReservation(quantity, date);
		return stockReservation;
	}
	
	
}
