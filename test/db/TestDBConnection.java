package db;


import static org.junit.jupiter.api.Assertions.*;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.DataAccessException;

class TestDBConnection {
	private DBConnection connection;

	
	
	@BeforeEach
	void setup() throws DataAccessException, SQLException {
		connection = DBConnection.getInstance();
		
	}
	@Test
	void testConnection() throws SQLException {
		DatabaseMetaData dma =connection.getConnection().getMetaData();
		assertTrue(dma != null);
	}

}
