package controller;

import model.Material;

public class MaterialController {
	private MaterialDBIF materialDBIF;
	
	public MaterialController() {
		materialDBIF = new MaterialDBIF;
	}

public Material findMaterialByMaterialNo(int materialNo) {
	return materialDBIF.findMaterialByMaterialNo(materialNo);
	}
}
