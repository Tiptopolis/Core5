package com.uchump.prime._RAUM.RAUM.MAP;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.uchump.prime._PRIME.C_O.NIX.Rect;
import com.uchump.prime._PRIME.SYS.A_I.aProperty;
import com.uchump.prime._PRIME.SYS.NMP._Entity;
import com.uchump.prime._PRIME.SYS.Prototype.iGraph;

public class _Region extends _Layer  {

	public Vector3 Position = new Vector3();
	

	public _Region(String label) {
		super(label);
		this.addProperty(new aProperty("Position", Position));
		
	}


	public _Region(String label, int width, int height) {
		super(label);
	}



}
