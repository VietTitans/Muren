package controller;

import model.Material;
import db.MaterialDBIF;
import db.MaterialDB;

public class MaterialController {
	private MaterialDBIF materialDBIF;
	
	public MaterialController() {
		materialDBIF = new MaterialDB();
	}

public Material findMaterialByMaterialNo(int materialNo) {
	return materialDBIF.findMaterialByMaterialNo(materialNo);
	}
}
