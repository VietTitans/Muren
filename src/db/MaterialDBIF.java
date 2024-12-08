package db;

import controller.DataAccessException;
import model.Material;

public interface MaterialDBIF {

	Material findMaterialByMaterialNo(int materialNo, boolean fullAssertion) throws DataAccessException;

}
