package controller;

import model.Material;
import db.MaterialDBIF;
import db.MaterialDB;

public class MaterialController {
	private MaterialDBIF materialDBIF;
	
	public MaterialController() throws DataAccessException {
		materialDBIF = new MaterialDB();
	}
 
public Material findMaterialByMaterialNo(int materialNo) throws DataAccessException {
	return materialDBIF.findMaterialByMaterialNo(materialNo,fullAssociation);
	}
}
