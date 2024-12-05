package controller;

import model.Material;
import db.MaterialDBIF;
import db.MaterialDB;

public class MaterialController {
	private MaterialDBIF materialDBIF;
	
	public MaterialController() throws DataAccessException {
		materialDBIF = new MaterialDB();
	}
 
public Material findMaterialByMaterialNo(int materialNo, boolean fullAssociation) throws DataAccessException {
	return materialDBIF.findMaterialByProductNo(materialNo,fullAssociation);
	}
}
