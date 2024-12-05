package db;

import model.Material;
import model.Price;
import model.StockMaterial;
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

	private static final String PS_SELECT_FROM_MATERIAL = " SELECT Material.*, StockMaterial.Quantity, StockMaterial.MinStock, StockMaterial.MaxStock\r\n"
			+ "FROM Material\r\n"
			+ "LEFT JOIN StockMaterial ON Material.ProductNo = StockMaterial.ProductNo\r\n"
			+ "WHERE Material.ProductNo = 1001;"; 
	private static final String PS_SELECT_FROM_MATERIAL_DESCRIPTION = " SELECT * FROM MaterialDescription WHERE ProductNo = ?;";
	private static final String PS_SELECT_FROM_SALES_PRICE = " SELECT * FROM SalesPrice WHERE ProductNo = ?;";
	private static final String PS_SELECT_FROM_PURCHASE_PRICE = " SELECT * FROM PurchasePrice WHERE ProductNo = ?;";
	
	private PreparedStatement psSelectProductNoMaterial;
	private PreparedStatement psSelectProductNoMaterialDescription;
	private PreparedStatement psSelectProductNoSalesPrice;
	private PreparedStatement psSelectProductNoPurchasePrice;
	
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
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("Could not prepare statement", e);
		}
	}
	
	
	public Material findMaterialByProductNo(int productNo, boolean fullAssertion) throws DataAccessException {
		Material material = null;
		Price salesPrice = null;
		Price purchasePrice = null;
		MaterialDescription materialDescription = null;
		try {
			psSelectProductNoMaterial.setInt(1,productNo);
			ResultSet rsMaterial = psSelectProductNoMaterial.executeQuery();
			
			psSelectProductNoMaterialDescription.setInt(1,productNo);
			ResultSet rsDescription = psSelectProductNoMaterialDescription.executeQuery();
			
			psSelectProductNoSalesPrice.setInt(1,productNo);
			ResultSet rsSalesPrice = psSelectProductNoSalesPrice.executeQuery();
			
			psSelectProductNoPurchasePrice.setInt(1,productNo);
			ResultSet rsPurchasePrice = psSelectProductNoPurchasePrice.executeQuery();
			
			if (rsDescription.next() && rsSalesPrice.next() && rsPurchasePrice.next()) {
				materialDescription = buildObjectMaterialDescription(rsDescription, fullAssertion);
				salesPrice = buildObjectSalesPrice(rsSalesPrice, fullAssertion);
				purchasePrice = buildObjectPurchasePrice(rsSalesPrice, fullAssertion);
				
				if(rsMaterial.next() || rsMaterial.wasNull()) {
					material = buildObjectMaterial(rsMaterial, fullAssertion, materialDescription, salesPrice, purchasePrice);
				} else if(rsMaterial.next() || rsMaterial.getInt("Quantity") > 0) {
					material = buildObjectStockMaterial(rsMaterial, fullAssertion, materialDescription, salesPrice, purchasePrice);
				}
			}
			}catch (SQLException e) {
				e.printStackTrace();
				throw new DataAccessException("Cant find product", e);
			}
			
			return material;
		}
	
	
	private Material buildObjectMaterial(ResultSet rs, boolean fullAssertion, MaterialDescription materialDescription, Price salesPrice, Price purchasePrice) throws DataAccessException, SQLException {
		Material material = null;
		
		try {
			String productName = rs.getString("ProductName");
			int productNo = rs.getInt("ProductNo");
			
			material = new Material(productNo, productName, materialDescription, salesPrice, purchasePrice);
			
		} catch (SQLException e) {
			throw new DataAccessException("Cannot convert from ResultSet", e);
		}
		
		return material;
	}
	
	
	private Material buildObjectStockMaterial(ResultSet rs, boolean fullAssertion, MaterialDescription materialDescription, Price salesPrice, Price purchasePrice) throws DataAccessException, SQLException {
		Material material = null;
		
		try {
			String productName = rs.getString("ProductName");
			int productNo = rs.getInt("ProductNo");
			int quantity = rs.getInt("Quantity");
			int minStock = rs.getInt("MinStock");
			int maxStock = rs.getInt("MaxStock");
			
			material = new StockMaterial(productNo, productName, materialDescription, salesPrice, purchasePrice, minStock, maxStock,quantity);
			
		} catch (SQLException e) {
			throw new DataAccessException("Cannot convert from ResultSet", e);
		}
		
		return material;
	}
	
	
	private MaterialDescription buildObjectMaterialDescription(ResultSet rs, boolean fullAssertion) throws DataAccessException, SQLException {
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
	
	private Price buildObjectSalesPrice(ResultSet rs, boolean fullAssertion) throws DataAccessException, SQLException {
		Price salesPrice = null;
		
		try {
		BigDecimal value = rs.getBigDecimal("price");
		Timestamp timeStamp = rs.getTimestamp("salesPriceTimeStamp");
		LocalDateTime date = timeStamp.toLocalDateTime();
		
		salesPrice = new Price(date, value);
		
		}catch (SQLException e) {
			throw new DataAccessException("Cannot convert from ResultSet", e);
		}
		return salesPrice;
	}
	
	
	private Price buildObjectPurchasePrice(ResultSet rs, boolean fullAssertion) throws DataAccessException, SQLException {
		Price purchasePrice = null;
		
		try {
		BigDecimal value = rs.getBigDecimal("price");
		Timestamp timeStamp = rs.getTimestamp("PurchasePriceTimeStamp");
		LocalDateTime date = timeStamp.toLocalDateTime();
		
		purchasePrice = new Price(date, value);
		
		}catch (SQLException e) {
			throw new DataAccessException("Cannot convert from ResultSet", e);
		}
		return purchasePrice;
	}
	
	
}
