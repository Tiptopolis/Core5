package com.uchump.prime._PRIME.UI._Camera;

import com.uchump.prime._PRIME.C_O.NIX.Transform;
import com.uchump.prime._PRIME.SYS.A_I.iMonad;
import com.uchump.prime._PRIME.UI._Camera.A_I.aCameraController;

public class uCameraController extends aCameraController{

	public uCameraController(iMonad root) {
		super(root);
		
	}
	
	public uCameraController(iMonad root,String name) {
		super(root, name);
	}

	public uCameraController setTransform(Transform t)
	{
		this.transform = t;
		return this;
	}


	
	
}
