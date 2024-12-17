package controller;

import db.MaterialDB;
import db.MaterialDBIF;
import model.Material;

public class MaterialController {
	private MaterialDBIF materialDBIF;
	
	public MaterialController() throws DataAccessException {
		materialDBIF = new MaterialDB();
	}
 
public Material findMaterialByMaterialNo(int materialNo) throws DataAccessException {
	return materialDBIF.findMaterialByMaterialNo(materialNo);
	}
}
