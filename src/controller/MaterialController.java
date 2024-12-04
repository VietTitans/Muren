package controller;

import model.Material;
import db.MaterialDBIF;
import db.MaterialDB;

public class MaterialController {
	private MaterialDBIF materialDBIF;
	
	public MaterialController() throws DataAccessException {
		materialDBIF = new MaterialDB();
	}
/*
 * Skal ændres
 * 
public Material findMaterialByMaterialNo(int materialNo) {
	return materialDBIF.findMaterialByMaterialNo(materialNo);
	}
*/ 
}
