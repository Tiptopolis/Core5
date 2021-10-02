package com.uchump.prime._RAUM.RAUM.WORLD.UTIL;

import java.util.ArrayList;

import com.uchump.prime._PRIME.SYS.NIX._Shell;
import com.uchump.prime._PRIME.SYS.NMP._Object;
import com.uchump.prime._PRIME.UI._Camera.A_I.aCameraController;
import com.uchump.prime._RAUM.RAUM.GFX._BoundShape;

public class _SDF_SYS extends _Shell{

	//SDF ray-marching environment
	
	protected ArrayList<_BoundShape> Observables = new ArrayList<_BoundShape>();
	protected ArrayList<aCameraController> Observers = new ArrayList<aCameraController>();
	
	
	
	public _SDF_SYS()
	{
		super("SDF");
	}
	
	
	
	
}
