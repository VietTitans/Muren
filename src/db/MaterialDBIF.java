package db;

import controller.DataAccessException;
import model.Material;

public interface MaterialDBIF {

	Material findMaterialByProductNo(int productNo, boolean fullAssertion) throws DataAccessException;
}
